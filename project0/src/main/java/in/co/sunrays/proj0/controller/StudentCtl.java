package in.co.sunrays.proj0.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.RoleForm;
import in.co.sunrays.proj0.form.StudentForm;
import in.co.sunrays.proj0.service.CollegeServiceInt;
import in.co.sunrays.proj0.service.StudentServiceInt;

@Controller
@RequestMapping(value = "/ctl/Student")
public class StudentCtl extends BaseCtl {

	@Autowired
	private StudentServiceInt service;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CollegeServiceInt collegeservice;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@Override
	public void preload(Model model) {
		List list = null;
		try {
			list = collegeservice.search(null);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("collegeList", list);
	}

	@ModelAttribute("genderList")
	public Map<String, String> getGenderList() {
		Map<String, String> genderList = new HashMap<String, String>();
		genderList.put("m", "Male");
		genderList.put("f", "FeMale");
		return genderList;
	}

	@RequestMapping(value = "/AddStudent", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") StudentForm form,
			Model model, Locale locale) {
		if (id != null && id > 0) {
			StudentDTO dto = null;
			try {
				dto = service.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			form.populate(dto);
		}

		return "StudentView";
	}

	@RequestMapping(value = "/AddStudent", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid StudentForm form, BindingResult result, Model model,
			Locale locale) throws DuplicateRecordException, ApplicationException {
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "StudentView";
			}
			StudentDTO dto = (StudentDTO) form.getDto();

			if (dto.getId() > 0) {
				service.update(dto);
				String msg = messageSource.getMessage("message.updatesuccess", null, locale);
				model.addAttribute("success", msg);
			} else {
				try {
					service.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messageSource.getMessage("message.email", null, locale);
					model.addAttribute("error", msg);
				}
			}
		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Student/AddStudent";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Student/StudentListCtl";
		}

		return "StudentView";
	}

	@RequestMapping(value = "/StudentListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") RoleForm form,
			Model model, Locale locale) {

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		List list = null;
		List next = null;
		try {
			list = service.search(null, pageNo, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		try {
			next = service.search(null, pageNo + 1, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		return "StudentListView";
	}

	@RequestMapping(value = "/StudentListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") StudentForm form,
			Model model, Locale locale) {

		int pageNo = (form.getPageNo() == 0) ? 1 : form.getPageNo();
		int pageSize = form.getPageSize();
		List list = null;
		List next = null;

		if (OP_SEARCH.equalsIgnoreCase(operation)) {
			pageNo = 1;
		}
		if (OP_NEXT.equalsIgnoreCase(operation)) {
			pageNo++;
		}
		if (OP_PREVIOUS.equalsIgnoreCase(operation)) {
			pageNo--;
		}
		if (OP_NEW.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Student/AddStudent";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Student/StudentListCtl";
		}

		if (OP_DELETE.equalsIgnoreCase(operation)) {
			if (form.getChk_1() != null) {
				for (long id : form.getChk_1()) {
					try {
						service.delete(id);
					} catch (DataBaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String msg = messageSource.getMessage("message.deleterecord", null, locale);
					model.addAttribute("success", msg);
				}
			} else {
				String msg = messageSource.getMessage("message.atleastone", null, locale);
				model.addAttribute("error", msg);
			}

		}
		form.setPageNo(pageNo);
		StudentDTO dto = (StudentDTO) form.getDto();

		try {
			list = service.search(dto, pageNo, pageSize);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);
		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			String msg = messageSource.getMessage("message.norecord", null, locale);
			model.addAttribute("error", msg);
		}
		try {
			next = service.search(dto, pageNo + 1, pageSize);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		return "StudentListView";
	}

}

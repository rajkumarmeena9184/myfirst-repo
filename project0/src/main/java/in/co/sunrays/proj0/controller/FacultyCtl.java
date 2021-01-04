package in.co.sunrays.proj0.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

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

import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.FacultyForm;
import in.co.sunrays.proj0.service.CollegeServiceInt;
import in.co.sunrays.proj0.service.CourseServiceInt;
import in.co.sunrays.proj0.service.FacultyServiceInt;
import in.co.sunrays.proj0.service.SubjectServiceInt;

@Controller
@RequestMapping(value = "/ctl/Faculty")
public class FacultyCtl extends BaseCtl {

	@Autowired
	private FacultyServiceInt service;

	@Autowired
	private CollegeServiceInt collegeservice;

	@Autowired
	private SubjectServiceInt subjectservice;

	@Autowired
	private CourseServiceInt courseservice;

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@Override
	public void preload(Model model) {
		List<CollegeDTO> list = null;
		List list1 = null;
		List list2 = null;
		try {
			list = collegeservice.search(null);
			list1 = courseservice.search(null);
			list2 = subjectservice.search(null);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("collegeList", list);
		model.addAttribute("courseList", list1);
		model.addAttribute("subjectList", list2);

	}

	@ModelAttribute("genderList")
	public Map<String, String> getGenderList() {
		Map<String, String> genderList = new HashMap<String, String>();
		genderList.put("m", "Male");
		genderList.put("f", "FeMale");
		return genderList;
	}

	@RequestMapping(value = "/AddFaculty", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") FacultyForm form,
			Model model, Locale locale) {
		System.out.println("hello");

		if (id != null && id > 0) {
			FacultyDTO dto = null;
			try {
				dto = service.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			form.populate(dto);
		}

		return "FacultyView";
	}

	@RequestMapping(value = "/AddFaculty", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid FacultyForm form, BindingResult result, Model model,
			Locale locale) {

		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "FacultyView";
			}

			FacultyDTO dto = (FacultyDTO) form.getDto();

			if (dto.getId() > 0) {
				try {
					service.update(dto);
					String msg = messageSource.getMessage("message.updatesuccess", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messageSource.getMessage("error.loginid", null, locale);
					model.addAttribute("error", msg);
					e.printStackTrace();
				}

			} else {
				try {
					service.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messageSource.getMessage("error.loginid", null, locale);
					model.addAttribute("error", msg);
				}
			}
		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Faculty/AddFaculty";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Faculty/FacultyListCtl";
		}

		return "FacultyView";
	}

	@RequestMapping(value = "/FacultyListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") FacultyForm form,
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
		return "FacultyListView";
	}

	@RequestMapping(value = "/FacultyListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") FacultyForm form,
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
			return "redirect:/ctl/Faculty/AddFaculty";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Faculty/FacultyListCtl";
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
		FacultyDTO dto = (FacultyDTO) form.getDto();

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
		return "FacultyListView";
	}

}

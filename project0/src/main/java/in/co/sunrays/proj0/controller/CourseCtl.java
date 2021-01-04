package in.co.sunrays.proj0.controller;

import java.util.LinkedHashMap;
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
import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.CollegeForm;
import in.co.sunrays.proj0.form.CourseForm;
import in.co.sunrays.proj0.service.CourseServiceInt;

@Controller
@RequestMapping(value = "/ctl/Course")
public class CourseCtl extends BaseCtl {

	@Autowired
	private CourseServiceInt service;

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@ModelAttribute("durationList")
	public Map<String, String> getDurationList(Model model) {
		Map<String, String> durationList = new LinkedHashMap();

		durationList.put("1 Year", "1 Year");
		durationList.put("2 Years", "2 Years");
		durationList.put("3 Years", "3 Years");
		durationList.put("4 Years", "4 Years");
		durationList.put("5 Years", "5 Years");

		return durationList;
	}

	@RequestMapping(value = "/AddCourse", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") CourseForm form,
			BindingResult result, Model model, Locale locale) {

		if (id != null && id > 0) {
			CourseDTO dto = null;
			try {
				dto = service.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			form.populate(dto);
		}

		return "CourseView";
	}

	@RequestMapping(value = "/AddCourse", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid CourseForm form, BindingResult result, Model model,
			Locale locale) throws DuplicateRecordException {

		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "CourseView";
			}

			CourseDTO dto = (CourseDTO) form.getdDto();

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
					String msg = messageSource.getMessage("error.coursename", null, locale);
					model.addAttribute("error", msg);
				}
			}
		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Course/AddCourse";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Course/CourseListCtl";
		}

		return "CourseView";
	}

	@RequestMapping(value = "/CourseListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") CourseForm form,
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
		return "CourseListView";
	}

	@RequestMapping(value = "/CourseListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") CourseForm form,
			Model model, BindingResult result, Locale locale) {

		if (result.hasErrors()) {
			return "CourseListView";
		}
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
			return "redirect:/ctl/Course/AddCourse";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Course/CourseListCtl";
		}

		if (OP_DELETE.equalsIgnoreCase(operation)) {
			pageNo = 1;
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
		CourseDTO dto = (CourseDTO) form.getDto();
		try {
			list = service.search(dto, pageNo, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			String msg = messageSource.getMessage("message.norecord", null, locale);
			model.addAttribute("error", msg);

		}
		try {
			next = service.search(dto, pageNo + 1, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		return "CourseListView";
	}
}

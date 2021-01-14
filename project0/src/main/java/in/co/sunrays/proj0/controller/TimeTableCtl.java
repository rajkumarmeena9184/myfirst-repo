package in.co.sunrays.proj0.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.SubjectForm;
import in.co.sunrays.proj0.form.TimeTableForm;
import in.co.sunrays.proj0.service.CourseServiceInt;
import in.co.sunrays.proj0.service.SubjectServiceInt;
import in.co.sunrays.proj0.service.TimeTableServiceInt;

/**
 * Contains navigation logics for TimeTable and TimeTable List Usecases.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/TimeTable")
public class TimeTableCtl extends BaseCtl {
	Logger log = Logger.getLogger(TimeTableCtl.class);
	@Autowired
	private TimeTableServiceInt service;

	@Autowired
	private CourseServiceInt courseservice;

	@Autowired
	private SubjectServiceInt subjectservice;

	@Autowired
	private MessageSource messagesource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@Override
	public void preload(Model model) {
		log.debug("TimeTableCtl Method Preload Start");
		List list1 = null;
		List list2 = null;
		try {
			list1 = courseservice.search(null);
			list2 = subjectservice.search(null);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("courseList", list1);
		model.addAttribute("subjectList", list2);
		log.debug("TimeTableCtl Method Preload End");
	}

	@RequestMapping(value = "/AddTimeTable", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") TimeTableForm form,
			Model model, Locale locale) {
		log.debug("TimeTableCtl Method  display AddTimeTable  Start");
		if (id != null && id > 0) {
			TimeTableDTO dto = null;
			try {
				dto = service.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			form.populate(dto);
		}
		log.debug("TimeTableCtl Method  display AddTimeTable  End");
		return "TimeTableView";
	}

	@RequestMapping(value = "/AddTimeTable", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid TimeTableForm form, BindingResult result, Model model,
			Locale locale) {
		log.debug("TimeTableCtl Method  submit AddTimeTable  Start");
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "TimeTableView";
			}

			TimeTableDTO dto = (TimeTableDTO) form.getDto();

			if (dto.getId() > 0) {
				try {
					service.update(dto);
					String msg = messagesource.getMessage("message.updatesuccess", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messagesource.getMessage("error.record", null, locale);
					model.addAttribute("error", msg);
				}
			} else {
				try {
					service.add(dto);
					String msg = messagesource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messagesource.getMessage("error.record", null, locale);
					model.addAttribute("error", msg);
				}
			}

		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/TimeTable/AddTimeTable";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/TimeTable/TimeTableListCtl";
		}
		log.debug("TimeTableCtl Method  submit AddTimeTable  Start");
		return "TimeTableView";
	}

	@RequestMapping(value = "/TimeTableListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") TimeTableForm form,
			Model model, Locale locale) {
		log.debug("TimeTableCtl Method  display TimeTableListCtl  Start");
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
		log.debug("TimeTableCtl Method  display TimeTableListCtl  End");
		return "TimeTableListView";
	}

	@RequestMapping(value = "/TimeTableListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") TimeTableForm form,
			Model model, Locale locale) {
		log.debug("TimeTableCtl Method  submit TimeTableListCtl  Start");
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
			return "redirect:/ctl/TimeTable/AddTimeTable";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/TimeTable/TimeTableListCtl";
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

					String msg = messagesource.getMessage("message.deleterecord", null, locale);
					model.addAttribute("success", msg);
				}
			} else {
				String msg = messagesource.getMessage("message.atleastone", null, locale);
				model.addAttribute("error", msg);
			}

		}
		form.setPageNo(pageNo);
		TimeTableDTO dto = (TimeTableDTO) form.getDto();

		try {
			list = service.search(dto, pageNo, pageSize);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);
		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			String msg = messagesource.getMessage("message.norecord", null, locale);
			model.addAttribute("error", msg);
		}
		try {
			next = service.search(dto, pageNo + 1, pageSize);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		log.debug("TimeTableCtl Method  submit TimeTableListCtl  End");
		return "TimeTableListView";
	}

}

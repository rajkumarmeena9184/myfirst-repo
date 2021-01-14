package in.co.sunrays.proj0.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.RoleForm;
import in.co.sunrays.proj0.form.StudentForm;
import in.co.sunrays.proj0.form.SubjectForm;
import in.co.sunrays.proj0.service.CourseServiceInt;
import in.co.sunrays.proj0.service.SubjectServiceInt;
/**
 * Contains navigation logics for Subject and Subject List Usecases.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/Subject")
public class SubjectCtl extends BaseCtl {
	Logger log = Logger.getLogger(SubjectCtl.class);
	@Autowired
	private SubjectServiceInt service;

	@Autowired
	private CourseServiceInt cservice;

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@ModelAttribute("semesterList")
	public Map<String, String> getDurationList(Model model) {
		log.debug("SubjectCtl method  getDurationList Start");
		Map<String, String> semesterList = new LinkedHashMap();

		semesterList.put("I", "I");
		semesterList.put("II", "II");
		semesterList.put("III", "III");
		semesterList.put("IV", "IV");
		semesterList.put("V", "V");
		semesterList.put("VI", "VI");
		semesterList.put("VII", "VII");
		semesterList.put("VIII", "VIII");
		log.debug("SubjectCtl method  getDurationList End");
		return semesterList;
	}

	@Override
	public void preload(Model model) {
		log.debug("SubjectCtl method  preload Start");
		List list = null;
		;
		try {
			list = (List) cservice.search(null);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("SubjectCtl method  preload End");
		model.addAttribute("courseList", list);
	}

	@RequestMapping(value = "/AddSubject", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") SubjectForm form,
			Model model, Locale locale) {
		log.debug("SubjectCtl method  display AddSubject Start");
		if (id != null && id > 0) {
			SubjectDTO dto = null;
			;
			try {
				dto = service.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			form.populate(dto);
		}
		log.debug("SubjectCtl method  display AddSubject Start");
		return "SubjectView";
	}

	@RequestMapping(value = "/AddSubject", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid SubjectForm form, BindingResult result, Model model,
			Locale locale) throws DuplicateRecordException {
		log.debug("SubjectCtl method  submit AddSubject Start");
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "SubjectView";
			}
			SubjectDTO dto = (SubjectDTO) form.getDto();
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
					String msg = messageSource.getMessage("error.subjectname", null, locale);
					model.addAttribute("error", msg);
				}
			}
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Subject/SubjectListCt";
		}
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Subject/AddSubject";
		}
		log.debug("SubjectCtl method  submit AddSubject End");
		return "SubjectView";
	}

	@RequestMapping(value = "/SubjectListCt", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") SubjectForm form,
			Model model, Locale locale) {
		log.debug("SubjectCtl method  display SubjectListCt Start");
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
		log.debug("SubjectCtl method  display SubjectListCt End");
		return "SubjectListView";
	}

	@RequestMapping(value = "/SubjectListCt", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") SubjectForm form,
			Model model, Locale locale) {
		log.debug("SubjectCtl method  submit SubjectListCt Start");
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
			return "redirect:/ctl/Subject/AddSubject";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Subject/SubjectListCt";
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
				}
				String msg = messageSource.getMessage("message.deleterecord", null, locale);
				model.addAttribute("success", msg);
			} else {
				String msg = messageSource.getMessage("message.atleastone", null, locale);
				model.addAttribute("error", msg);
			}

		}
		form.setPageNo(pageNo);
		SubjectDTO dto = (SubjectDTO) form.getDto();

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
		log.debug("SubjectCtl method  submit SubjectListCt End");
		return "SubjectListView";
	}

}

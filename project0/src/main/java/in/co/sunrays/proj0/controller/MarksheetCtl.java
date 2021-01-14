package in.co.sunrays.proj0.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.MarksheetForm;
import in.co.sunrays.proj0.service.MarksheetServiceInt;
import in.co.sunrays.proj0.service.StudentServiceInt;

/**
 * Contains navigation logics for MarkSheet and MarkSheet List Usecases.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/Marksheet")
public class MarksheetCtl extends BaseCtl {
	Logger log = Logger.getLogger(MarksheetCtl.class);
	@Autowired
	private StudentServiceInt studentservice;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MarksheetServiceInt mservice;

	@Override
	public void preload(Model model) {
		log.debug("MarksheetCtl Method Prelaod Start");
		List list = null;
		try {
			list = studentservice.search(null);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("studentList", list);
		log.debug("MarksheetCtl Method Prelaod End");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@RequestMapping(value = "/AddMarksheet", method = RequestMethod.GET)
	public String Display(@RequestParam(required = false) Long id, @ModelAttribute("form") MarksheetForm form,
			Model model, Locale locale) {
		log.debug("MarksheetCtl Method Display AddMarksheet Start");
		if (id != null && id > 0) {

			MarksheetDTO dto = null;
			try {
				dto = mservice.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			form.populate(dto);

		}
		log.debug("MarksheetCtl Method Display AddMarksheet End");
		return "MarksheetView";
	}

	@RequestMapping(value = "/AddMarksheet", method = RequestMethod.POST)
	public String submit(@RequestParam String operation, @ModelAttribute("form") @Valid MarksheetForm form,
			BindingResult result, Model model, Locale locale) {
		log.debug("MarksheetCtl Method submit AddMarksheet Start");
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

			if (result.hasErrors()) {

				return "MarksheetView";
			}

			MarksheetDTO dto = (MarksheetDTO) form.getDto();
			if (dto.getId() > 0) {
				try {
					mservice.update(dto);
					String msg = messageSource.getMessage("message.updatesuccess", null, locale);
					model.addAttribute("success", msg);
					model.addAttribute("id", dto.getId());
				} catch (DuplicateRecordException e) {
					e.printStackTrace();
				}
			} else {

				try {

					mservice.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messageSource.getMessage("message.rollno", null, locale);
					model.addAttribute("error", msg);
				}
			}
		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {

			return "redirect:/ctl/Marksheet/AddMarksheet";
		}

		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Marksheet/MarksheetListCtl";
		}
		log.debug("MarksheetCtl Method submit AddMarksheet End");
		return "MarksheetView";
	}

	@RequestMapping(value = "/GetMarksheet", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") MarksheetForm form, Model model, Locale locale) {
		log.debug("MarksheetCtl Method display GetMarksheet Start");
		log.debug("MarksheetCtl Method display GetMarksheet End");
		return "GetMarksheetView";
	}

	@RequestMapping(value = "/GetMarksheet", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid MarksheetForm form, BindingResult result,
			@RequestParam(required = false) String operation, Model model, Locale locale) {
		log.debug("MarksheetCtl Method submit GetMarksheet Start");
		if (OP_SEARCH.equalsIgnoreCase(operation)) {
			if (form.getRollNo() != "") {
				MarksheetDTO dto = null;
				try {
					dto = mservice.findByRollNumber(form.getRollNo());
				} catch (DataBaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (dto != null) {
					System.out.println(dto.getId());
					form.populate(dto);
				} else {
					String msg1 = messageSource.getMessage("error.roll", null, locale);
					model.addAttribute("error", msg1);
				}
			} else {
				String msg1 = messageSource.getMessage("NotEmpty", null, locale);
				model.addAttribute("error", msg1);
			}
		}

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Marksheet/GetMarksheet";
		}
		log.debug("MarksheetCtl Method submit GetMarksheet End");
		return "GetMarksheetView";
	}

	@RequestMapping(value = "/MarksheetListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") MarksheetForm form,
			Model model, Locale locale) {
		log.debug("MarksheetCtl Method display MarksheetListCtl Start");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		List list = null;
		try {
			list = mservice.search(null, pageNo, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		// new Button disabled
		List next = null;
		try {
			next = mservice.search(null, pageNo + 1, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		System.out.println(" MarksheetList Ctl doget Method");
		log.debug("MarksheetCtl Method display MarksheetListCtl End");
		return "MarksheetListView";
	}

	@RequestMapping(value = "/MarksheetListCtl", method = RequestMethod.POST)
	public String Submit(@RequestParam(required = false) String operation, @ModelAttribute("form") MarksheetForm form,
			BindingResult result, Model model, Locale locale) {
		log.debug("MarksheetCtl Method Submit MarksheetListCtl Start");
		if (result.hasErrors()) {
			return "MarksheetListView";
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
			return "redirect:/ctl/Marksheet/AddMarksheet";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Marksheet/MarksheetListCtl";
		}
		if (OP_DELETE.equalsIgnoreCase(operation)) {
			pageNo = 1;
			if (form.getChk_1() != null) {
				for (long id : form.getChk_1()) {
					try {
						mservice.delete(id);
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
		MarksheetDTO dto = (MarksheetDTO) form.getDto();
		try {
			list = mservice.search(dto, pageNo, pageSize);
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
			next = mservice.search(dto, pageNo + 1, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		log.debug("MarksheetCtl Method Submit MarksheetListCtl End");
		return "MarksheetListView";
	}

	@RequestMapping(value = "/MarksheetMeritListCtl", method = RequestMethod.GET)
	public String Display(@ModelAttribute("form") MarksheetForm form, Model model, Locale locale) {
		log.debug("MarksheetCtl Method Display MarksheetMeritListCtl Start");
		int pageNo = form.getPageNo();
		int pageSize = 10;
		List list = null;
		try {
			list = mservice.meritList(pageNo, pageSize);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		/*
		 * // new Button disabled List next = null; try { next = mservice.search(null,
		 * pageNo + 1, pageSize); } catch (DataBaseException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } model.addAttribute("nextlist",
		 * next.size());
		 */
		log.debug("MarksheetCtl Method Display MarksheetMeritListCtl End");
		return "MarksheetMeritList";
	}

}

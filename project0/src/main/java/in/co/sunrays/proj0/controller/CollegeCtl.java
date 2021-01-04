package in.co.sunrays.proj0.controller;

import java.util.List;
import java.util.Locale;

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
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.CollegeForm;
import in.co.sunrays.proj0.service.CollegeServiceInt;

@Controller
@RequestMapping(value = "/ctl/College")
public class CollegeCtl extends BaseCtl {

	@Autowired
	private CollegeServiceInt service;

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@RequestMapping(value = "/AddCollege", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") CollegeForm form,
			Model model, Locale locale) {
		if (id != null && id > 0) {
			CollegeDTO dto = null;
			try {
				dto = (CollegeDTO) service.findByPK(id);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			form.populate(dto);
		}

		return "CollegeView";
	}

	@RequestMapping(value = "/AddCollege", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) Long id, @ModelAttribute("form") @Valid CollegeForm form,
			BindingResult result, Model model, Locale locale) throws DuplicateRecordException {

		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "CollegeView";
			}

			CollegeDTO dto = (CollegeDTO) form.getDto();
			try {

				if (id > 0) {
					service.update(dto);
					String msg = messageSource.getMessage("message.updatesuccess", null, locale);
					model.addAttribute("success", msg);
				} else {
					service.add(dto);
					System.out.println("save 2");
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				}
			} catch (DuplicateRecordException e) {
				String msg = messageSource.getMessage("error.collegename", null, locale);
				model.addAttribute("error", msg);
			}
		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/College/AddCollege";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/College/CollegeListCtl";
		}

		return "CollegeView";
	}

	@RequestMapping(value = "/CollegeListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") CollegeForm form,
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
		return "CollegeListView";
	}

	@RequestMapping(value = "/CollegeListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") CollegeForm form,
			Model model, BindingResult result, Locale locale) {

		if (result.hasErrors()) {
			return "CollegeListView";
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
			return "redirect:/ctl/College/AddCollege";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/College/CollegeListCtl";
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
		CollegeDTO dto = (CollegeDTO) form.getDto();
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
		return "CollegeListView";
	}
}

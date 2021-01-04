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

import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.FacultyForm;
import in.co.sunrays.proj0.form.RoleForm;
import in.co.sunrays.proj0.service.RoleServiceInt;

@Controller
@RequestMapping(value = "/ctl/Role")
public class RoleCtl extends BaseCtl {

	@Autowired
	private RoleServiceInt roleService;

	@Autowired
	private MessageSource messageSource = null;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@RequestMapping(value = "/AddRole", method = RequestMethod.GET)
	public String Display(@RequestParam(required = false) Long id, @ModelAttribute("form") RoleForm form, Model model,
			Locale locale) {
		if (id != null && id > 0) {
			RoleDTO dto = null;
			try {
				dto = roleService.findByPK(id);
				form.populate(dto);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "RoleView";
	}

	@RequestMapping(value = "/AddRole", method = RequestMethod.POST)
	public String Submit(@RequestParam(required = false) Long id, @ModelAttribute("form") @Valid RoleForm form,
			BindingResult result, Model model, Locale locale) {

		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

			if (result.hasErrors()) {
				return "RoleView";
			}
			RoleDTO dto = (RoleDTO) form.getDto();
			if (id > 0) {
				try {
					roleService.update(dto);
					String msg = messageSource.getMessage("message.updatesuccess", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				try {
					roleService.add(dto);

					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					String msg = messageSource.getMessage("error.rolename", null, locale);
					model.addAttribute("error", msg);
					e.printStackTrace();
				}
			}
		}
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Role/AddRole";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Role/RoleListCtl";

		}
		return "RoleView";
	}

	@RequestMapping(value = "/RoleListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") RoleForm form,
			Model model, Locale locale) {

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		List list = null;
		List next = null;
		try {
			list = roleService.search(null, pageNo, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		try {
			next = roleService.search(null, pageNo + 1, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		return "RoleListView";
	}

	@RequestMapping(value = "/RoleListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") RoleForm form,
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
			return "redirect:/ctl/Role/AddRole";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Role/RoleListCtl";
		}

		if (OP_DELETE.equalsIgnoreCase(operation)) {
			if (form.getChk_1() != null) {
				for (long id : form.getChk_1()) {
					try {
						roleService.delete(id);
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
		RoleDTO dto = (RoleDTO) form.getDto();

		try {
			list = roleService.search(dto, pageNo, pageSize);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);
		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			String msg = messageSource.getMessage("message.norecord", null, locale);
			model.addAttribute("error", msg);
		}
		try {
			next = roleService.search(dto, pageNo + 1, pageSize);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlist", next.size());
		return "RoleListView";
	}

}

package in.co.sunrays.proj0.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.ChangePasswordForm;
import in.co.sunrays.proj0.form.MyProfileForm;
import in.co.sunrays.proj0.form.UserForm;
import in.co.sunrays.proj0.service.RoleServiceInt;
import in.co.sunrays.proj0.service.UserServiceInt;
import in.co.sunrays.proj0.util.Util;

/**
 * Contains navigation logics for User, UserList, MyProfile,
 * ChangePassword,RegisterUser,Authentication usecases.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/User")
public class UserCtl extends BaseCtl {
	Logger log = Logger.getLogger(UserCtl.class);
	@Autowired
	private UserServiceInt userService;

	@Autowired
	private RoleServiceInt roleService;

	@Autowired
	private MessageSource messageSource;

	public void preload(Model model) {
		log.debug("UserCtl Method preload Start");
		List list = null;
		try {
			list = (List) roleService.list();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("roleList", list);
		log.debug("UserCtl Method preloadt End");
	}

	@ModelAttribute("genderList")
	public Map<String, String> getGenderList() {
		log.debug("UserCtl Method getGenderList Start");
		Map<String, String> genderList = new HashMap<String, String>();
		genderList.put("m", "Male");
		genderList.put("f", "FeMale");
		log.debug("UserCtl Method getGenderList End");
		return genderList;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@RequestMapping(value = "/AddUser", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") UserForm form, Model model,
			Locale locale) {
		log.debug("UserCtl Method display AddUser Start");
		if (id != null && id > 0) {
			UserDTO dto = null;
			try {
				dto = userService.findByPK(id);
				form.populate(dto);
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.debug("UserCtl Method display AddUser End");
		return "UserView";
	}

	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) Long id, @ModelAttribute("form") @Valid UserForm form,
			BindingResult result, Model model, Locale locale) {
		log.debug("UserCtl Method submit AddUser Start");
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

			if (result.hasErrors()) {
				return "UserView";
			}
			UserDTO dto = (UserDTO) form.getDto();
			if (!dto.getPassword().equalsIgnoreCase(dto.getConfirmPassword())) {
				String msg = messageSource.getMessage("error.confirmPassword", null, locale);
				model.addAttribute("error", msg);
				return "UserView";
			}
			if (id > 0) {
				try {
					userService.update(dto);
					String msg = messageSource.getMessage("message.updatesuccess", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				try {
					userService.add(dto);

					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					String msg = messageSource.getMessage("message.email", null, locale);
					model.addAttribute("error", msg);
					e.printStackTrace();
				}
			}
		}
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/User/AddUser";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/User/UserListCtl";
		}
		log.debug("UserCtl Method submit AddUser End");
		return "UserView";
	}

	@RequestMapping(value = "/MyProfileCtl", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") MyProfileForm form, HttpSession session, Model model, Locale locale) {
		log.debug("UserCtl Method display MyProfileCtl Start");
		UserDTO dto = (UserDTO) session.getAttribute("user");

		try {
			dto = userService.findByPK(dto.getId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		form.populate(dto);
		log.debug("UserCtl Method display MyProfileCtl End");
		return "MyProfileView";

	}

	@RequestMapping(value = "/MyProfileCtl", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid MyProfileForm form, BindingResult result, Model model,
			Locale locale, HttpSession session) throws DuplicateRecordException {
		log.debug("UserCtl Method submit MyProfileCtl Start");
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "MyProfileView";
			}
			UserDTO dto = null;
			try {
				dto = userService.findByPK(form.getId());
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setDob(Util.getDate(form.getDob()));
			dto.setMobileNo(form.getMobileNo());
			dto.setGender(form.getGender());
			dto.setModifiedBy(form.getEmailId());
			dto.setModifiedDateTime(new Timestamp(new Date().getTime()));

			session.setAttribute("user", dto);

			userService.update(dto);
			String msg = messageSource.getMessage("message.success", null, locale);
			model.addAttribute("success", msg);

		}

		if (OP_CHANGEPASSWORD.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/User/ChangePasswordCtl";
		}
		log.debug("UserCtl Method submit MyProfileCtl End");
		return "MyProfileView";
	}

	@RequestMapping(value = "/ChangePasswordCtl", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ChangePasswordForm form, Model model, Locale locale) {
		return "ChangePasswordView";
	}

	@RequestMapping(value = "/ChangePasswordCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) Long id,
			@ModelAttribute("form") @Valid ChangePasswordForm form, BindingResult result, Model model,
			HttpSession session, Locale locale) {
		log.debug("UserCtl Method submit ChangePasswordCtl Start");
		if (OP_MYPROFILE.equalsIgnoreCase(form.getOperation())) {

			return "redirect:/ctl/User/MyProfileCtl";
		}
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "ChangePasswordView";
			}

			boolean flag = false;
			UserDTO dto = (UserDTO) session.getAttribute("user");

			if (!form.getOldPassword().equals(form.getNewPassword())) {
				if (form.getConfirmPassword().equals(form.getNewPassword())) {
					try {
						flag = userService.changePassword(dto.getId(), form.getOldPassword(), form.getNewPassword());
						if (flag) {
							String msg = messageSource.getMessage("message.success", null, locale);
							model.addAttribute("success", msg);
						} else {
							String msg = messageSource.getMessage("error.oldrightpassword", null, locale);
							model.addAttribute("error", msg);
						}
					} catch (DuplicateRecordException e) {

					}
				} else {
					String msg = messageSource.getMessage("error.newconpassword", null, locale);
					model.addAttribute("error", msg);
				}
			} else {
				String msg = messageSource.getMessage("error.oldnewpassword", null, locale);
				model.addAttribute("error", msg);
			}
		}
		log.debug("UserCtl Method submit ChangePasswordCtl End");
		return "ChangePasswordView";
	}

	@RequestMapping(value = "/UserListCtl", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) String operation, @ModelAttribute("form") UserForm form,
			Model model, Locale locale) {
		/*
		 * String enterfirstName = messageSource.getMessage("label.enterfname", null,
		 * locale); model.addAttribute("enterfFirstName", enterfirstName); String
		 * enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		 * model.addAttribute("enterLastName", enterLastName);
		 */
		log.debug("UserCtl Method display UserListCtl Start");
		int pageNo = form.getPageNo();

		List list = null;
		try {
			list = userService.search(null, pageNo, form.getPageSize());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		List next = null;
		try {
			next = userService.search(null, pageNo + 1, form.getPageSize());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlistsize", next.size());

		System.out.println("user List Ctl get Method");
		log.debug("UserCtl Method display UserListCtl End");
		return "UserListView";
	}

	@RequestMapping(value = "/UserListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") UserForm form,
			BindingResult result, Model model, Locale locale) {

		/*
		 * String enterfirstName = messageSource.getMessage("label.enterfname", null,
		 * locale); model.addAttribute("enterfFirstName", enterfirstName); String
		 * enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		 * model.addAttribute("enterLastName", enterLastName);
		 */
		/*
		 * if (result.hasErrors()) { return "UserListView"; }
		 */
		log.debug("UserCtl Method Submit UserListCtl Start");
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
		if (OP_DELETE.equalsIgnoreCase(operation)) {
			pageNo = 1;
			if (form.getChk_1() != null) {
				for (long id : form.getChk_1()) {
					try {
						userService.delete(id);
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
		if (OP_NEW.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/User/AddUser";
		}
		if (OP_RESET.equalsIgnoreCase(operation) || OP_BACK.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/User/UserListCtl";
		}
		form.setPageNo(pageNo);

		UserDTO dto = (UserDTO) form.getDto();

		try {
			list = userService.search(dto, pageNo, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", messageSource.getMessage("message.norecord", null, locale));
		}

		try {
			next = userService.search(dto, pageNo + 1, pageSize);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nextlistsize", next.size());
		log.debug("UserCtl Method Submit UserListCtl End");
		return "UserListView";

	}

}

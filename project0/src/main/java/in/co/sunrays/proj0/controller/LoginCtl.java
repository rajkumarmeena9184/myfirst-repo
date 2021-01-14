package in.co.sunrays.proj0.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.ApplicatioException;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.form.ForgetPasswordForm;
import in.co.sunrays.proj0.form.LoginForm;
import in.co.sunrays.proj0.form.UserRegistrationForm;
import in.co.sunrays.proj0.service.RoleServiceInt;
import in.co.sunrays.proj0.service.UserServiceInt;

@Controller
@SessionAttributes("user")
public class LoginCtl extends BaseCtl {
	Logger log = Logger.getLogger(LoginCtl.class);
	@Autowired
	private UserServiceInt userService = null;

	@Autowired
	private RoleServiceInt roleService = null;

	/**
	 * i18n MessageSource
	 */
	@Autowired
	private MessageSource messageSource = null;
	protected static final String OP_SIGNIN = "SignIn";

	protected static final String OP_SIGNUP = "SignUp";

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		StringTrimmerEditor stringTrimmmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringTrimmmer);
	}

	/**
	 * GetGenderList of LoginCtl
	 * 
	 * @return genderList
	 */
	@ModelAttribute("genderList")
	public Map<String, String> getGenderList() {
		log.debug("LoginCtl method getGenderList started");
		Map<String, String> genderList = new HashMap<String, String>();
		genderList.put("m", "Male");
		genderList.put("f", "Female");
		log.debug("LoginCtl method getGenderList End");
		return genderList;
	}

	@RequestMapping(value = "/LoginCtl", method = RequestMethod.GET)
	public String Display(@RequestParam(required = false, defaultValue = "null") String signout,
			@ModelAttribute("form") LoginForm form, Model model, HttpSession session, Locale locale) {
		log.debug("LoginCtl Method Display LoginCtl Start");
		String email = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("email", email);

		String password = messageSource.getMessage("label.enterpassword", null, locale);
		model.addAttribute("password", password);

		if (session.getAttribute("user") != null && !signout.equalsIgnoreCase("Logout")) {
			return "redirect:WelcomeView";
		}

		if (session.getAttribute("user") != null && signout.equalsIgnoreCase("Logout")) {

			session.invalidate();
			model.asMap().remove("user");// remove session Object
			String successLogout = messageSource.getMessage("message.logout", null, locale);
			model.addAttribute("success", successLogout);
		}
		log.debug("LoginCtl Method Display LoginCtl End");
		return "LoginView";
	}

	@RequestMapping(value = "/LoginCtl", method = RequestMethod.POST)
	public String Submit(@ModelAttribute("form") @Valid LoginForm form, BindingResult result, HttpSession session,
			Model model, Locale locale) throws DataBaseException {
		log.debug("LoginCtl Method Submit LoginCtl Start");
		String email = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("email", email);

		String password = messageSource.getMessage("label.enterpassword", null, locale);
		model.addAttribute("password", password);

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/Registration";
		}

		if (OP_SIGNIN.equalsIgnoreCase(form.getOperation())) {

			if (result.hasErrors()) {
				return "LoginView";
			}

			UserDTO dto = new UserDTO();
			dto.setEmailId(form.getEmailId());
			dto.setPassword(form.getPassword());
			dto = userService.authenticate(dto);
			if (dto != null) {
				UserDTO edto = userService.findByPK(dto.getId());
				RoleDTO roleDto = roleService.findByPK(edto.getRoleId());

				if (roleDto != null) {
					edto.setRoleName(roleDto.getRoleName());
				}

				model.addAttribute("user", edto);
				// URI Concept

				if (form.getUri() == null || form.getUri().toString().trim() == "") {
					return "WelcomeView";
				} else {
					return "redirect:" + form.getUri().replace("/Project0", "".toString().trim());
				}

			} else {

				String error = messageSource.getMessage("login.error", null, locale);
				model.addAttribute("error", error);

				return "LoginView";
			}
		}
		log.debug("LoginCtl Method Submit LoginCtl End");
		return "redirect:/Welcome";
	}

	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model, Locale locale) {
		log.debug("LoginCtl Method display Registration Start");
		/*
		 * String firstName = messageSource.getMessage("label.firstName", null, locale);
		 * model.addAttribute("firstName", firstName);
		 * 
		 * String lastName = messageSource.getMessage("label.lastName", null, locale);
		 * model.addAttribute("lastName", lastName);
		 * 
		 * String emailId = messageSource.getMessage("label.emailId", null, locale);
		 * model.addAttribute("emailId", emailId);
		 * 
		 * String password = messageSource.getMessage("label.password", null, locale);
		 * model.addAttribute("password", password);
		 * 
		 * String confirmPassword = messageSource.getMessage("label.confirmpassword",
		 * null, locale); model.addAttribute("confirmPassword", confirmPassword);
		 * 
		 * String gender = messageSource.getMessage("label.gender", null, locale);
		 * model.addAttribute("gender", gender);
		 * 
		 * String dob = messageSource.getMessage("label.dob", null, locale);
		 * model.addAttribute("date", dob);
		 * 
		 * String mobileNo = messageSource.getMessage("label.mobileNo", null, locale);
		 * model.addAttribute("mobileNo", mobileNo);
		 */
		/*
		 * String mobileNo = messageSource.getMessage("", null, locale);
		 * model.addAttribute("mobileNo", mobileNo);
		 * 
		 * String mobileNo = messageSource.getMessage("", null, locale);
		 * model.addAttribute("mobileNo", mobileNo);
		 * 
		 * String mobileNo = messageSource.getMessage("", null, locale);
		 * model.addAttribute("mobileNo", mobileNo);
		 */
		log.debug("LoginCtl Method display Registration End");
		return "UserRegistrationView";
	}

	@RequestMapping(value = "/Registration", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid UserRegistrationForm form, BindingResult result, Model model,
			Locale locale) {
		log.debug("LoginCtl Method submit Registration Start");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/Registration";
		}

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			if (result.hasErrors()) {
				return "UserRegistrationView";
			}
			UserDTO dto = (UserDTO) form.getDto();

			try {

				if (!form.getPassword().equalsIgnoreCase(form.getConfirmPassword())) {

					String msg = messageSource.getMessage("error.confirmPassword", null, locale);
					model.addAttribute("error", msg);
					return "UserRegistrationView";
				}
				dto.setRoleId(RoleDTO.STUDENT);
				userService.registerUser(dto);
				String msg = messageSource.getMessage("message.success", null, locale);
				model.addAttribute("success", msg);
				form.populate(new UserDTO());
				return "UserRegistrationView";
			} catch (DuplicateRecordException e) {
				String msg = messageSource.getMessage("error.loginid", null, locale);
				model.addAttribute("error", msg);

			} catch (ApplicatioException e) {
				String msg = messageSource.getMessage("error.loginid", null, locale);
				model.addAttribute("error", msg);

			}
			// return "redirect:/Registration";
		}
		log.debug("LoginCtl Method submit Registration End");
		return "UserRegistrationView";
	}

	@RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form, Model model, Locale locale) {
		log.debug("LoginCtl Method display ForgotPassword Start");
		String email = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("email", email);
		log.debug("LoginCtl Method display ForgotPassword End");
		return "ForgetPasswordView";
	}

	@RequestMapping(value = "/ForgotPassword", method = RequestMethod.POST)
	public String submit(@ModelAttribute("form") @Valid ForgetPasswordForm form, BindingResult results, Model model,
			Locale locale) {
		log.debug("LoginCtl Method submit ForgotPassword Start");
		String email = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("email", email);
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/LoginCtl";
		}

		if (OP_GO.equalsIgnoreCase(form.getOperation())) {
			if (results.hasErrors()) {
				return "ForgetPasswordView";
			}
			try {
				boolean flag = false;
				flag = userService.forgetPassword(form.getEmailId());
				if (flag) {
					String msg = messageSource.getMessage("message.successForgetPassword", null, locale);
					model.addAttribute("success", msg);
				} else {
					String msg = messageSource.getMessage("forgetpass.error", null, locale);
					model.addAttribute("error", msg);
				}
			} catch (ApplicatioException e) {
				e.printStackTrace();
			}
		}
		log.debug("LoginCtl Method submit ForgotPassword End");
		return "ForgetPasswordView";
	}
}

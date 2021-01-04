package in.co.sunrays.proj0.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.co.sunrays.proj0.form.UserForm;

@Controller
public class WelcomeCtl extends BaseCtl {
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String display(Model model) {
		String msg = "This is Welcome Page";

		model.addAttribute("message", msg);
		return "WelcomeView";
	}
}

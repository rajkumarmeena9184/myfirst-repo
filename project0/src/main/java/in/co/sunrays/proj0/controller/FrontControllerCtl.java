package in.co.sunrays.proj0.controller;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrontControllerCtl extends HandlerInterceptorAdapter {

	private MessageSource messageSource = null;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			Locale locale = null;
			String msg = messageSource.getMessage("message.session", null, locale);
			request.setAttribute("error", msg);
			String uri = request.getRequestURI();
			request.setAttribute("URI", uri);
			RequestDispatcher rd = request.getRequestDispatcher("/LoginView");
			rd.forward(request, response);
			return false;
		}

		return true;
	}

}

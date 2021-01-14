package in.co.sunrays.proj0.controller;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * @author Facade
 * @version 1.0 Copyright (c) Facade
 */
public class FrontControllerCtl extends HandlerInterceptorAdapter {

	Logger log = Logger.getLogger(FrontControllerCtl.class);
	/**
	 * i18n MessageSource
	 */
	@Autowired
	private MessageSource messageSource = null;

	/**
	 * preHandle
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("FrontControllerCtl method preHandle");
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			Locale locale = null;
			String msg = messageSource.getMessage("message.session", null, locale);
			request.setAttribute("error", msg);
			String uri = request.getRequestURI();
			request.setAttribute("URI", uri);
			RequestDispatcher rd = request.getRequestDispatcher("/LoginCtl");
			rd.forward(request, response);
			log.debug("FrontControllerCtl method preHandle end return false");
			return false;
		}

		log.debug("FrontControllerCtl method preHandle end return true");
		return true;
	}

}

package in.co.sunrays.proj0.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.co.sunrays.proj0.dto.UserDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Contains navigation logics for Jasper Report
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
@Transactional
@Controller
@RequestMapping(value = "/ctl/Jasper")
public class JasperCtl {

	Logger log = Logger.getLogger(JasperCtl.class);
	/**
	 * SessionFactory Autowired
	 */
	@Autowired
	private SessionFactory sessionFactory = null;

	@Autowired
	ServletContext context;

	@RequestMapping(method = RequestMethod.GET)
	public void display(HttpServletRequest request, HttpServletResponse response)
			throws JRException, SQLException, IOException {
		log.debug("JasperCtl method display started");
		String path = context.getRealPath("/resources/Jasper/MarksheetMeritList.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		Connection con = null;
		con = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class)
				.getConnection();

		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", dto.getFirstName() + "  " + dto.getLastName());
		map.put("emailid", dto.getEmailId());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, con);
		byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
		response.setContentType("application/pdf");
		response.getOutputStream().write(pdf);
		response.getOutputStream().flush();
		log.debug("JasperCtl method display End");
	}
}

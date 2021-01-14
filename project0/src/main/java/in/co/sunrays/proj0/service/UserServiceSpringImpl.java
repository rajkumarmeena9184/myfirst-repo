package in.co.sunrays.proj0.service;

import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.UserDAOInt;
import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.exception.ApplicatioException;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
import in.co.sunrays.proj0.util.EmailBuilder;

/**
 * Session facade of User Service. It is transactional, apply delcarative
 * transactions with help of Spring AOP.
 *
 * If unchecked exception is propagated from a method then transaction will be
 * rolled back.
 *
 * Default propogation value is Propagation.REQUIRED and readOnly = false
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service(value = "userService")
public class UserServiceSpringImpl implements UserServiceInt {
	Logger log = Logger.getLogger(UserServiceSpringImpl.class);
	@Autowired
	private UserDAOInt userDao = null;

	@Autowired
	private RoleServiceInt roleService = null;
	@Autowired
	private JavaMailSender mailSender = null;

	/**
	 * Add method a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		log.debug(" UserServiceSpringImpl method Add start");
		long pk = 0;

		UserDTO existDto = null;
		try {
			existDto = findByLogin(dto.getEmailId());
			if (existDto != null) {
				throw new DuplicateRecordException("Login Id AlReady Exist");
			}
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoleDTO rdto = null;
		try {
			rdto = roleService.findByPK(dto.getRoleId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setRoleName(rdto.getRoleName());
		pk = userDao.add(dto);
		log.debug(" UserServiceSpringImpl method Add End");
		return pk;
	}

	/**
	 * Update method a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		log.debug(" UserServiceSpringImpl method Update start");
		RoleDTO rdto = null;
		try {
			rdto = roleService.findByPK(dto.getRoleId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setRoleName(rdto.getRoleName());
		userDao.update(dto);
		log.debug(" UserServiceSpringImpl method Update start");
	}

	/**
	 * Delete method a User
	 * 
	 * @param id
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug(" UserServiceSpringImpl method Delete start");
		// TODO Auto-generated method stub
		userDao.delete(id);
		log.debug(" UserServiceSpringImpl method Delete End");
	}

	/**
	 * findByLogin method a User
	 * 
	 * @param login
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		log.debug(" UserServiceSpringImpl method findByLogin start");
		UserDTO dto = null;
		dto = userDao.findByLogin(login);
		log.debug(" UserServiceSpringImpl method findByLogin End");
		return dto;
	}

	/**
	 * findByPK method a User
	 * 
	 * @param pk
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public UserDTO findByPK(long pk) throws DataBaseException {
		// TODO Auto-generated method stub
		log.debug(" UserServiceSpringImpl method findByPk start");
		UserDTO dto = null;
		dto = userDao.findByPK(pk);
		log.debug(" UserServiceSpringImpl method findByPk End");
		return dto;
	}

	/**
	 * search with pagination method a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.debug(" UserServiceSpringImpl method search With Pagination start");
		List list = userDao.search(dto, pageNo, pageSize);
		log.debug(" UserServiceSpringImpl method search With Pagination End");
		return list;
	}

	/**
	 * search method a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(UserDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/**
	 * registerUser method a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long registerUser(UserDTO dto) throws DuplicateRecordException, ApplicatioException {
		log.debug(" UserServiceSpringImpl method registerUser start");
		RoleDTO roleDto = null;
		long pk = 0;

		try {
			roleDto = roleService.findByPK(dto.getRoleId());
			dto.setRoleName(roleDto.getRoleName());
			pk = add(dto);
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("login",
		 * dto.getEmailId()); map.put("password", dto.getPassword()); String message =
		 * EmailBuilder.getUserRegistrationMessage(map); MimeMessage msg =
		 * mailSender.createMimeMessage(); try {
		 * 
		 * MimeMessageHelper helper = new MimeMessageHelper(msg);
		 * helper.setTo(dto.getEmailId()); helper.
		 * setSubject("Registration is successful for ORS Project SUNRAYS Technologies."
		 * ); helper.setText(message, true); mailSender.send(msg);
		 * 
		 * } catch (MessagingException e) { }
		 */
		log.debug(" UserServiceSpringImpl method registerUser End");
		return pk;
	}

	/**
	 * changePassword method a User
	 * 
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean changePassword(long id, String oldPassword, String newPassword) throws DuplicateRecordException {
		log.debug(" UserServiceSpringImpl method changePassword start");
		UserDTO dto = null;
		boolean flag = false;
		try {
			dto = userDao.findByPK(id);
			if (dto != null && oldPassword.equals(dto.getPassword())) {
				dto.setPassword(newPassword);
				userDao.update(dto);
				flag = true;
				/*
				 * HashMap<String, String> map = new HashMap<String, String>(); map.put("login",
				 * dto.getEmailId()); map.put("password", dto.getPassword());
				 * map.put("firstName", dto.getFirstName()); map.put("lastName",
				 * dto.getLastName()); String message =
				 * EmailBuilder.getForgetPasswordMessage(map);
				 * 
				 * MimeMessage msg = mailSender.createMimeMessage();
				 * 
				 * // use the true flag to indicate you need a multipart message
				 * MimeMessageHelper helper; try { helper = new MimeMessageHelper(msg, true);
				 * helper.setTo(dto.getEmailId());
				 * helper.setSubject("Password has been reset."); // use the true flag to
				 * indicate the text included is HTML helper.setText(message, true); } catch
				 * (MessagingException e) { System.out.println("Mail Sending Failed");
				 * e.printStackTrace(); } mailSender.send(msg);
				 * 
				 * flag = true;
				 */
			}
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug(" UserServiceSpringImpl method changePassword End");
		return flag;
	}

	/**
	 * forgetPassword method a User
	 * 
	 * @param login
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean forgetPassword(String login) throws ApplicatioException {
		log.debug(" UserServiceSpringImpl method forgetPassword start");
		try {
			UserDTO dto = userDao.findByLogin(login);
			if (dto != null) {
				/*
				 * HashMap<String, String> map = new HashMap<String, String>();
				 * map.put("firstName", dto.getFirstName()); map.put("lastName",
				 * dto.getLastName()); map.put("login", dto.getEmailId()); map.put("Password",
				 * dto.getPassword());
				 * 
				 * String message = EmailBuilder.getForgetPasswordMessage(map);
				 * 
				 * MimeMessage msg = mailSender.createMimeMessage();
				 * 
				 * try { MimeMessageHelper helper = new MimeMessageHelper(msg);
				 * helper.setTo(login); helper.setSubject("SunilOS ORS Password reset"); // use
				 * the true flag to indicate the text included is HTML helper.setText(message,
				 * true); mailSender.send(msg); } catch (MessagingException e) {
				 * e.printStackTrace(); return false; }
				 */
			} else {
				return false;
			}

		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		log.debug(" UserServiceSpringImpl method forgetPassword End");
		return true;
	}

	/**
	 * authenticate method a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public UserDTO authenticate(UserDTO dto) throws DataBaseException {
		log.debug(" UserServiceSpringImpl method authenticate start");
		UserDTO dtoExit = userDao.findByLogin(dto.getEmailId());
		if (dtoExit != null && dtoExit.getPassword().equals(dto.getPassword())) {
			System.out.println("exist dto return");
			return dtoExit;
		}
		log.debug(" UserServiceSpringImpl method authenticate End");
		return null;
	}

}

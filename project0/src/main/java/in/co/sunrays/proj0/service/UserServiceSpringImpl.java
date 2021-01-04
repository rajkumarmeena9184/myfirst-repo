package in.co.sunrays.proj0.service;

import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

@Service(value = "userService")
public class UserServiceSpringImpl implements UserServiceInt {
	@Autowired
	private UserDAOInt userDao = null;

	@Autowired
	private RoleServiceInt roleService = null;
	@Autowired
	private JavaMailSender mailSender = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub

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
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		userDao.update(dto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		// TODO Auto-generated method stub
		userDao.delete(id);

	}

	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		UserDTO dto = null;
		dto = userDao.findByLogin(login);
		return dto;
	}

	@Transactional(readOnly = true)
	public UserDTO findByPK(long pk) throws DataBaseException {
		// TODO Auto-generated method stub
		UserDTO dto = null;
		dto = userDao.findByPK(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List list = userDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(UserDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long registerUser(UserDTO dto) throws DuplicateRecordException, ApplicatioException {
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
		 */ return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean changePassword(long id, String oldPassword, String newPassword) throws DuplicateRecordException {
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
		return flag;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean forgetPassword(String login) throws ApplicatioException {
		try {
			UserDTO dto = userDao.findByLogin(login);
			System.out.println("firstName   " + dto.getFirstName());
			System.out.println("lastName   " + dto.getLastName());
			System.out.println("login   " + dto.getEmailId());
			System.out.println("Password  " + dto.getPassword());
			if (dto != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("firstName", dto.getFirstName());
				map.put("lastName", dto.getLastName());
				map.put("login", dto.getEmailId());
				map.put("Password", dto.getPassword());

				String message = EmailBuilder.getForgetPasswordMessage(map);

				MimeMessage msg = mailSender.createMimeMessage();

				try {
					MimeMessageHelper helper = new MimeMessageHelper(msg);
					helper.setTo(login);
					helper.setSubject("SunilOS ORS Password reset");
					// use the true flag to indicate the text included is HTML
					helper.setText(message, true);
					mailSender.send(msg);
				} catch (MessagingException e) {
					e.printStackTrace();
					return false;
				}

			} else {
				return false;
			}

		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional(readOnly = true)
	public UserDTO authenticate(UserDTO dto) throws DataBaseException {
		UserDTO dtoExit = userDao.findByLogin(dto.getEmailId());
		if (dtoExit != null && dtoExit.getPassword().equals(dto.getPassword())) {
			System.out.println("exist dto return");
			return dtoExit;
		}
		return null;
	}

}

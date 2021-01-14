package in.co.sunrays.proj0.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.UserDTO;

/**
 * Contains ForgetPassword form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class ForgetPasswordForm extends BaseForm {
	/**
	 * EmailId Of ForgetPassword
	 */
	@NotEmpty
	@Email
	private String emailId;

	/**
	 * accesssor
	 *
	 */
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setEmailId(emailId);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		UserDTO dto = (UserDTO) bdto;
		emailId = dto.getEmailId();
	}
}

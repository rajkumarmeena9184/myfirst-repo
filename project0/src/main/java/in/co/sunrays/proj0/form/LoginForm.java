package in.co.sunrays.proj0.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.UserDTO;

public class LoginForm extends BaseForm {
	/**
	 * Email Id Of LoginForm
	 */
	@NotEmpty
	@Email
	private String emailId;

	/**
	 * Email Id Of Password
	 */
	@NotEmpty
	private String password;

	private String uri = null;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setEmailId(emailId);
		dto.setPassword(password);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {

		UserDTO dto = (UserDTO) bdto;
		emailId = dto.getEmailId();
		password = dto.getPassword();
	}
}

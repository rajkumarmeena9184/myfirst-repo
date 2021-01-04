package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.util.Util;

public class UserRegistrationForm extends BaseForm {

	/**
	 * FirstName of UserRegistrationForm
	 */
	@NotEmpty
	private String firstName;

	/**
	 * lastName of UserRegistrationForm
	 */
	@NotEmpty
	private String lastName;

	/**
	 * Gender of UserRegistrationForm
	 */
	@NotEmpty
	private String gender;

	/**
	 * EmailID of UserRegistrationForm
	 */
	@NotEmpty
	@Email
	private String emailId;

	/**
	 * Password of UserRegistrationForm
	 */
	@NotEmpty
	private String password;

	/**
	 * ConfirmPassword of UserRegistrationForm
	 */
	@NotEmpty
	private String confirmPassword;

	/**
	 * Date of UserRegistrationForm
	 */
	@NotEmpty
	private String dob;

	/**
	 * MobileNo of UserRegistrationForm
	 */
	@NotEmpty
	private String mobileNo;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public BaseDTO getDto() {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setGender(gender);
		dto.setMobileNo(mobileNo);
		if (dob != null) {
			dto.setDob(Util.getDate(dob));
		}
		dto.setPassword(password);
		dto.setConfirmPassword(confirmPassword);
		dto.setEmailId(emailId);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		UserDTO dto = (UserDTO) bdto;
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		emailId = dto.getEmailId();
		password = dto.getPassword();
		confirmPassword = dto.getConfirmPassword();
		mobileNo = dto.getMobileNo();
		gender = dto.getGender();
		dob = Util.getDate(dto.getDob());
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		if (dto.getCreatedDateTime() != null) {
			createdDateTime = dto.getCreatedDateTime().getTime();
		}
		if (dto.getModifiedDateTime() != null) {
			modifiedDateTime = dto.getModifiedDateTime().getTime();
		}

	}
}

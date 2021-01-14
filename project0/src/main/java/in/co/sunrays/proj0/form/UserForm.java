package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.util.Util;

/**
 * Contains User form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class UserForm extends BaseForm {
	/**
	 * FirstName of userForm
	 */
	@NotEmpty(message = "{NotEmpty.form.firstName}")
	private String firstName;
	/**
	 * LastName of userForm
	 */
	@NotEmpty
	private String lastName;
	/**
	 * Login of userForm
	 */
	@NotEmpty
	@Email
	private String login;
	/**
	 * Password of userForm
	 */
	@NotEmpty
	private String password;
	/**
	 * ConfirmPassword of userForm
	 */
	@NotEmpty
	private String confirmPassword;
	/**
	 * Gender of userForm
	 */
	@NotEmpty
	private String gender;
	/**
	 * Date Of Birth of userForm
	 */
	@NotEmpty
	private String dob;
	/**
	 * MobileNo of userForm
	 */
	@NotEmpty
	private String mobileNo;
	// @NotEmpty
	private String roleName;
	@Min(value = 1)
	private long roleId;

	/**
	 * accessor
	 */
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmailId(login);
		dto.setPassword(password);
		dto.setConfirmPassword(confirmPassword);
		if (dob != " ") {
			dto.setDob(Util.getDate(dob));
		}
		dto.setMobileNo(mobileNo);
		dto.setRoleId(roleId);
		dto.setGender(gender);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		login = dto.getEmailId();
		mobileNo = dto.getMobileNo();
		gender = dto.getGender();
		password = dto.getPassword();
		confirmPassword = dto.getConfirmPassword();
		dob = Util.getDate(dto.getDob());
		roleId = dto.getRoleId();
		roleName = dto.getRoleName();

	}
}

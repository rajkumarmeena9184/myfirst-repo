package in.co.sunrays.proj0.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * UserDTO DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_USER")
public class UserDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * FirstName of User
	 */
	@Column(name = "FIRST_NAME", length = 255)
	private String firstName;

	/**
	 * LastName of User
	 */
	@Column(name = "LAST_NAME", length = 255)
	private String lastName;

	/**
	 * Gender of User
	 */
	@Column(name = "GENDER", length = 255)
	private String gender;

	/**
	 * EmailId of User
	 */
	@Column(name = "EMAIL_ID")
	private String emailId;
	/**
	 * Password of User
	 */
	@Column(name = "PASSWOD", length = 255)
	private String password;
	/**
	 * ConfirmPassword of User
	 */
	@Transient
	private String confirmPassword;
	/**
	 * RoleId of User
	 */
	@Column(name = "ROLE_ID")
	private long roleId;
	/**
	 * RoleName of User
	 */
	@Column(name = "ROLE_NAME", length = 255)
	private String roleName;
	/**
	 * Dob of User
	 */
	@Column(name = "DOB")
	private Date dob;
	/**
	 * MobileNo of User
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;

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

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}

}

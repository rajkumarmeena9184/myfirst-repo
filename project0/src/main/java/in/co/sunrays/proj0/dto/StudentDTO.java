package in.co.sunrays.proj0.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Student DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_STUDENT")
public class StudentDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID Of College
	 */
	@Column(name = "COLLEGE_ID")
	private long collegeId;

	/**
	 * Name Of College
	 */
	@Column(name = "COLLEGE_NAME")
	private String collegeName;

	/**
	 * FirstName Of Student
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;
	/**
	 * LastName Of Student
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * Gender Of Student
	 */
	@Column(name = "GENDER")
	private String gender;

	/**
	 * Dob Of Student
	 */
	@Column(name = "DOB")
	private Date dob;

	/**
	 * MobileNo Of Student
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;

	/**
	 * EmailId Of Student
	 */
	@Column(name = "EMAIL_ID")
	private String emailId;

	/**
	 * accessor
	 */

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

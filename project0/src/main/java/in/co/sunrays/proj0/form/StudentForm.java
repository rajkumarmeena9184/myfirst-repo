package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.util.Util;
/**
 * Contains Student form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class StudentForm extends BaseForm {
	/**
	 * FirstName of StudentForm
	 */
	@NotEmpty
	private String firstName;

	/**
	 * LastName of StudentForm
	 */
	@NotEmpty
	private String lastName;

	/**
	 * EmalID of StudentForm
	 */
	@NotEmpty
	@Email
	private String emailId;

	/**
	 * Gender of StudentForm
	 */
	@NotEmpty
	private String gender;

	/**
	 * Dob of StudentForm
	 */
	@NotEmpty
	private String dob;

	/**
	 * MobileNo of StudentForm
	 */
	@NotEmpty
	private String mobileNo;

	/**
	 * CollegeName of StudentForm
	 */
	// @NotEmpty
	private String collegeName;

	/**
	 * CollegeIdName of StudentForm
	 */
	@Min(value = 1)
	private long collegeId;

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public BaseDTO getDto() {
		StudentDTO dto = new StudentDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setCollegeName(collegeName);
		dto.setCollegeId(collegeId);
		dto.setEmailId(emailId);
		dto.setGender(gender);
		if (dob != " ") {
			dto.setDob(Util.getDate(dob));
		}
		dto.setMobileNo(mobileNo);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		StudentDTO dto = (StudentDTO) bdto;
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		collegeId = dto.getCollegeId();
		collegeName = dto.getCollegeName();
		emailId = dto.getEmailId();
		gender = dto.getGender();
		dob = Util.getDate(dto.getDob());
		mobileNo = dto.getMobileNo();
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

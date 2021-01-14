package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.util.Util;
/**
 * Contains Faculty form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class FacultyForm extends BaseForm {

	/**
	 * SubjectId of Faculty
	 */
	@Min(value = 1)
	private long subjectId;

	/**
	 * CourseID of Faculty
	 */
	@Min(value = 1)
	private long courseId;

	/**
	 * CollegeId of Faculty
	 */
	@Min(value = 1)
	private long collegeId;

	/**
	 * Subject of Faculty
	 */
	// @NotEmpty
	private String subjectName;

	/**
	 * Course of Faculty
	 */
	// @NotEmpty
	private String courseName;

	/**
	 * College of Faculty
	 */
	// @NotEmpty
	private String collegeName;

	/**
	 * FirstName of Faculty
	 */
	@NotEmpty
	private String firstName;

	/**
	 * LastName of Faculty
	 */
	@NotEmpty
	private String lastName;

	/**
	 * EmailID of Faculty
	 */
	@NotEmpty
	@Email
	private String emailId;

	/**
	 * DOB of Faculty
	 */
	@NotEmpty
	private String dob;

	/**
	 * MobileNo of Faculty
	 */
	@NotEmpty
	private String mobileNo;

	/**
	 * Gender of Faculty
	 */
	@NotEmpty
	private String gender;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public BaseDTO getDto() {
		FacultyDTO dto = new FacultyDTO();
		dto.setId(id);
		dto.setCollegeId(collegeId);
		dto.setCourseId(courseId);
		dto.setSubjectId(subjectId);
		dto.setCollegeName(collegeName);
		dto.setSubjectName(subjectName);
		dto.setCourseName(courseName);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		if (dob != " ") {
			dto.setDob(Util.getDate(dob));
		}
		dto.setEmailID(emailId);
		dto.setGender(gender);
		dto.setMobileNo(mobileNo);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));

		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		FacultyDTO dto = (FacultyDTO) bdto;
		collegeId = dto.getCollegeId();
		subjectId = dto.getSubjectId();
		courseId = dto.getCollegeId();
		courseName = dto.getCourseName();
		subjectName = dto.getSubjectName();
		collegeName = dto.getCollegeName();
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		dob = Util.getDate(dto.getDob());
		emailId = dto.getEmailID();
		gender = dto.getGender();
		mobileNo = dto.getMobileNo();
		if (dto.getCreatedDateTime() != null) {
			createdDateTime = dto.getCreatedDateTime().getTime();
		}
		if (dto.getModifiedDateTime() != null) {
			modifiedDateTime = dto.getModifiedDateTime().getTime();
		}

	}
}

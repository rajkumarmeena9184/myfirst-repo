package in.co.sunrays.proj0.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * FacultyDTO DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_FACULTY")
public class FacultyDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * CollegeId
	 */
	@Column(name = "COLLEGE_ID")
	private long collegeId;

	/**
	 * CollegeName
	 */
	@Transient
	private String collegeName;
	/**
	 * SubjectId
	 */
	@Column(name = "SUBJECT_ID")
	private long subjectId;
	/**
	 * SubjectName
	 */
	@Transient
	private String subjectName;
	/**
	 * CourseId
	 */
	@Column(name = "COURSE_ID")
	private long courseId;
	/**
	 * CourseName
	 */
	@Transient
	private String courseName;

	/**
	 * FirstName Of Faculty
	 */
	@Column(name = "FIRST_NAME", length = 255)
	private String firstName;

	/**
	 * LastName Of Faculty
	 */
	@Column(name = "LAST_NAME", length = 255)
	private String lastName;

	/**
	 * Dob Of Faculty
	 */
	@Column(name = "DOB")
	private Date dob;

	/**
	 * MobileNo Of Faculty
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;

	/**
	 * Gender Of Faculty
	 */
	@Column(name = "Gender")
	private String gender;

	/**
	 * EmailId Of Faculty
	 */
	@Column(name = "EMAIL_ID", length = 255)
	private String emailID;

	/**
	 * accessors
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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
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

package in.co.sunrays.proj0.dto;

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
@Table(name = "ST_SUBJECT")
public class SubjectDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * Id OF Course
	 */
	@Column(name = "COURSE_ID")
	private long courseId;

	/**
	 * Name OF Course
	 */
	@Column(name = "COURSE_NAME", length = 255)
	private String courseName;
	/**
	 * Name Of Subject
	 */
	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	/**
	 * Semester Of Subject
	 */
	@Column(name = "SEMESTER")
	private String semester;

	/**
	 * Description Of Subject
	 */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * accessor
	 */

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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}

}

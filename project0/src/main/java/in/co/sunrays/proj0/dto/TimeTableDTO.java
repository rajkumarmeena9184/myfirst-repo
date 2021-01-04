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
@Table(name = "ST_TIMETABLE")
public class TimeTableDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * Id Of Subject
	 */
	@Column(name = "SUBJECT_ID")
	private long subjectId;

	/**
	 * Name Of Subject
	 */
	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	/**
	 * Id Of Course
	 */
	@Column(name = "COURSE_ID")
	private long courseId;

	/**
	 * Name Of Course
	 */
	@Column(name = "COURSE_NAME")
	private String courseName;
	/**
	 * ExamDate OF TimeTable
	 */
	@Column(name = "EXAM_DATE")
	private Date date;

	/**
	 * ExamTime OF TimeTable
	 */
	@Column(name = "EXAM_TIME")
	private String exameTime;

	/**
	 * Semester OF TimeTable
	 */
	@Column(name = "SEMESTER")
	private String semester;

	/**
	 * accessor
	 */
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExameTime() {
		return exameTime;
	}

	public void setExameTime(String exameTime) {
		this.exameTime = exameTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return "";
	}

}

package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.util.Util;

public class TimeTableForm extends BaseForm {

	/**
	 * CourseId Of TimeTableForm
	 */
	@Min(value = 1)
	private long courseId;

	/**
	 * SubjectId Of TimeTableForm
	 */
	@Min(value = 1)
	private long subjectId;

	/**
	 * CourseName OF TimeTableForm
	 */
	//@NotEmpty
	private String courseName;

	/**
	 * SubjectName OF TimeTableForm
	 */
	//@NotEmpty
	private String subjectName;

	/**
	 * Name OF TimeTableForm
	 */
	//@NotEmpty
	private String name;

	/**
	 * Semester OF TimeTableForm
	 */
	@NotEmpty
	private String semester;

	/**
	 * ExameTime OF TimeTableForm
	 */
	@NotEmpty
	private String exameTime;

	/**
	 * ExameDate OF TimeTableForm
	 */
	@NotEmpty
	private String exameDate;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getExameTime() {
		return exameTime;
	}

	public void setExameTime(String exameTime) {
		this.exameTime = exameTime;
	}

	public String getExameDate() {
		return exameDate;
	}

	public void setExameDate(String exameDate) {
		this.exameDate = exameDate;
	}

	@Override
	public BaseDTO getDto() {
		TimeTableDTO dto = new TimeTableDTO();
		dto.setId(id);
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setSubjectId(subjectId);
		dto.setSubjectName(subjectName);
		dto.setSemester(semester);
		dto.setExameTime(exameTime);
		if (exameTime != null) {
			dto.setDate(Util.getDate(exameDate));
		}
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		TimeTableDTO dto = (TimeTableDTO) bdto;
		id = dto.getId();
		courseId = dto.getCourseId();
		courseName = dto.getCourseName();
		subjectId = dto.getSubjectId();
		subjectName = dto.getSubjectName();
		semester = dto.getExameTime();
		exameDate = Util.getDate(dto.getDate());
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

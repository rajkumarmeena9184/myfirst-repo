package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.SubjectDTO;
/**
 * Contains Subject form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class SubjectForm extends BaseForm {

	/**
	 * Name of SubjectForm
	 */
	@NotEmpty
	private String name;

	/**
	 * CollegeId of SubjectForm
	 */
	@Min(value = 1)
	private long courseId;

	/**
	 * CollegeName of SubjectForm
	 */
	// @NotEmpty
	private String courseName;

	/**
	 * Semester of SubjectForm
	 */
	@NotEmpty
	private String semester;
	/**
	 * Description of SubjectForm
	 */
	@NotEmpty
	private String description;

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BaseDTO getDto() {
		SubjectDTO dto = new SubjectDTO();
		dto.setId(id);
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setDescription(description);
		dto.setSubjectName(name);
		dto.setSemester(semester);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		SubjectDTO dto = (SubjectDTO) bdto;
		id = dto.getId();
		courseId = dto.getCourseId();
		courseName = dto.getCourseName();
		name = dto.getSubjectName();
		semester = dto.getSemester();
		description = dto.getDescription();
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

package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.CourseDTO;
/**
 * Contains Course form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class CourseForm extends BaseForm {

	/**
	 * Name Of Course
	 */
	@NotEmpty
	private String name;

	/**
	 * Duration Of Course
	 */
	@NotEmpty
	private String duration;

	/**
	 * Duration Of Course
	 */
	@NotEmpty
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseDTO getDto() {
		CourseDTO dto = new CourseDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setDuration(duration);
		dto.setDescription(description);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	public void populate(BaseDTO bdto) {
		CourseDTO dto = (CourseDTO) bdto;
		id = dto.getId();
		name = dto.getName();
		duration = dto.getDuration();
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

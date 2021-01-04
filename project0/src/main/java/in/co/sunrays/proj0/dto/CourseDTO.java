package in.co.sunrays.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CourseDTO DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_COURSE")
public class CourseDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * Name Of Course
	 */
	@Column(name = "NAME", length = 255)
	private String name;

	/**
	 * Description Of Course
	 */
	@Column(name = "DESCRIPTION", length = 255)
	private String description;;

	/**
	 * Duration Of Course
	 */
	@Column(name = "DURATION", length = 255)
	private String duration;

	/**
	 * 
	 * accessors
	 */

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	public String getName() {
		return name;
	}

}

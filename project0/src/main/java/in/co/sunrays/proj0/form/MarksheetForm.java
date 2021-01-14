package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.MarksheetDTO;
/**
 * Contains Marksheet form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class MarksheetForm extends BaseForm {
	/**
	 * RolleNo of Marksheet
	 */
	@NotEmpty
	private String rollNo;

	/**
	 * StudentId of Marksheet
	 */
	@Min(value = 1)
	private long studentId;

	/**
	 * Name of Marksheet
	 */
	// @NotEmpty
	private String name;

	/**
	 * Physics of Marksheet
	 */
	//@NotNull
	@Min(0)
	@Max(100)
	private String physics;

	/**
	 * Chemistry of Marksheet
	 */
	//@NotNull
	@Min(0)
	@Max(100)
	private String chemistry;
	/**
	 * Maths of Marksheet
	 */
	//@NotNull
	@Min(0)
	@Max(100)
	private String maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhysics() {
		return physics;
	}

	public void setPhysics(String physics) {
		this.physics = physics;
	}

	public String getChemistry() {
		return chemistry;
	}

	public void setChemistry(String chemistry) {
		this.chemistry = chemistry;
	}

	public String getMaths() {
		return maths;
	}

	public void setMaths(String maths) {
		this.maths = maths;
	}

	@Override
	public BaseDTO getDto() {

		MarksheetDTO dto = new MarksheetDTO();
		dto.setId(id);
		dto.setStudentId(studentId);
		dto.setStudentName(name);
		dto.setRollnumber(rollNo);
		dto.setPhysics(physics);
		dto.setChemistry(chemistry);
		dto.setMaths(maths);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		MarksheetDTO dto = (MarksheetDTO) bdto;
		id = dto.getId();
		studentId = dto.getStudentId();
		name = dto.getStudentName();
		rollNo = dto.getRollnumber();
		
		physics = dto.getPhysics();
		chemistry = dto.getChemistry();
		maths = dto.getMaths();
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

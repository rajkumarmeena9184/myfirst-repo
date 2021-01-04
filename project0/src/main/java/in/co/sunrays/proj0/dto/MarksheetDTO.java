package in.co.sunrays.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Marksheet DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_MARKSHEET")
public class MarksheetDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID Of Student
	 */
	@Column(name = "STUDENT_ID")
	private long studentId;

	/**
	 * Name Of Student
	 */
	@Column(name = "STUDENT_NAME", length = 255)
	private String studentName;

	/**
	 * RollNumber Marks Of Student
	 */
	@Column(name = "ROLL_NUMBER", length = 255)
	private String rollnumber;

	/**
	 * Phisics Marks Of Student
	 */
	@Column(name = "PHYSICS", length = 255)
	private String physics;

	/**
	 * Chemistry Marks Of Student
	 */
	@Column(name = "CHEMISTRY", length = 255)
	private String chemistry;

	/**
	 * Mathematics Marks Of Student
	 */
	@Column(name = "MATHS", length = 255)
	private String maths;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
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

	public String getKey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return rollnumber;
	}

}

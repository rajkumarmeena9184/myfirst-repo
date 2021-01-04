package in.co.sunrays.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Role DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_ROLE")
public class RoleDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	public final static int ADMIN = 1;
	public final static int STUDENT = 2;
	public final static int FACULTY = 3;
	public final static int KIOSKO = 4;
	public final static int COLLEGE = 5;

	/**
	 * Name Of Role
	 */
	@Column(name = "ROLE_NAME", length = 255)
	private String roleName;

	/**
	 * Description Of Role
	 */
	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	/**
	 * accessor
	 */
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		return roleName;
	}

}

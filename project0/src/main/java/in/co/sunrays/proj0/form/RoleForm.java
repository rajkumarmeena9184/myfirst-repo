package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.RoleDTO;

public class RoleForm extends BaseForm {

	/**
	 * Name of Role Form
	 */
	@NotEmpty
	private String name;

	/**
	 * Description of Role Form
	 */
	@NotEmpty
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BaseDTO getDto() {
		RoleDTO dto = new RoleDTO();
		dto.setId(id);
		dto.setRoleName(name);
		dto.setDescription(description);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {

		RoleDTO dto = (RoleDTO) bdto;
		id = dto.getId();
		name = dto.getRoleName();
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

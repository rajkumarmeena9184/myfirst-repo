package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.CollegeDTO;

/**
 * Contains College form elements and their declarative input validations.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 */
public class CollegeForm extends BaseForm {
	/**
	 * Name of College
	 */
	@NotEmpty
	private String name;

	/**
	 * Address of College
	 */
	@NotEmpty
	private String address;

	/**
	 * State of College
	 */
	@NotEmpty
	private String state;

	/**
	 * City of College
	 */
	@NotEmpty
	private String city;

	/**
	 * PhoneNo of College
	 */
	@NotEmpty
	private String phoneNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Populate dto from form
	 * 
	 * @return dto
	 */
	public BaseDTO getDto() {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		dto.setState(state);
		dto.setCity(city);
		dto.setPhonNo(phoneNo);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));

		return dto;
	}

	/**
	 * Populate dto from form
	 */
	public void populate(BaseDTO bdto) {
		CollegeDTO dto = (CollegeDTO) bdto;
		id = dto.getId();
		name = dto.getName();
		address = dto.getAddress();
		city = dto.getCity();
		state = dto.getState();
		phoneNo = dto.getPhonNo();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		if (dto.getCreatedDateTime() != null)
			;
		createdDateTime = dto.getCreatedDateTime().getTime();
		if (dto.getModifiedDateTime() != null)
			;
		modifiedDateTime = dto.getModifiedDateTime().getTime();

	}
}

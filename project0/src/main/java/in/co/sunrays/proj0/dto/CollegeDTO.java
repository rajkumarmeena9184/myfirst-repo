package in.co.sunrays.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Collage DTO classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
@Entity
@Table(name = "ST_COLLEGE")
public class CollegeDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * Name Of College
	 */
	@Column(name = "NAME", length = 255)
	private String name;

	/**
	 * Address Of College
	 */
	@Column(name = "ADDRESS", length = 255)
	private String address;

	/**
	 * City Of College
	 */
	@Column(name = "CITY", length = 255)
	private String city;

	/**
	 * PhonNo Of College
	 */
	@Column(name = "PHON_NO", length = 255)
	private String phonNo;

	/**
	 * State Of College
	 */
	@Column(name = "STATE", length = 255)
	private String state;

	 /**
     * accessor
     */
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhonNo() {
		return phonNo;
	}

	public void setPhonNo(String phonNo) {
		this.phonNo = phonNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}

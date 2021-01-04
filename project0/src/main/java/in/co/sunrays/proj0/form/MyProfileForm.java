package in.co.sunrays.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import in.co.sunrays.proj0.dto.BaseDTO;
import in.co.sunrays.proj0.dto.UserDTO;
import in.co.sunrays.proj0.util.Util;

public class MyProfileForm extends BaseForm {
	/**
	 * FirstName of MyProfileForm
	 */
	@NotEmpty
	private String firstName;

	/**
	 * LastName of MyProfileForm
	 */
	@NotEmpty
	private String lastName;

	/**
	 * Gender of MyProfileForm
	 */
	@NotEmpty
	private String gender;

	/**
	 * EmailId of MyProfileForm
	 */
	@NotEmpty
	@Email
	private String emailId;

	/**
	 * MobileNo of MyProfileForm
	 */
	@NotEmpty
	private String mobileNo;

	/**
	 * Dob of MyProfileForm
	 */
	@NotEmpty
	private String dob;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public BaseDTO getDto() {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmailId(emailId);
		dto.setLastName(lastName);
		if (dob != " ") {
			dto.setDob(Util.getDate(dob));
		}
		dto.setEmailId(emailId);
		dto.setGender(gender);
		dto.setMobileNo(mobileNo);
		/*
		 * dto.setCreatedBy("root"); dto.setModifiedBy("root");
		 * dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		 * dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		 */

		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {

		UserDTO dto = (UserDTO) bdto;

		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		emailId = dto.getEmailId();
		gender = dto.getGender();
		dob = Util.getDate(dto.getDob());
		mobileNo = dto.getMobileNo();

		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		if (dto.getCreatedDateTime() != null) {
			createdDateTime = dto.getCreatedDateTime().getTime();
		}
		if (dto.getModifiedDateTime() != null) {
			createdDateTime = dto.getModifiedDateTime().getTime();
		}

	}
}

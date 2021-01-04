package in.co.sunrays.proj0.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseDTO implements DropDownList, Serializable, Comparable<BaseDTO> {

	private static final long serialVersionUID = 1L;
	@Column(name = "ID", unique = true, nullable = false)
	@Id
	@GenericGenerator(name = "hiIncrement", strategy = "increment")
	@GeneratedValue(generator = "hiIncrement")
	protected long id;

	@Column(name = "CREATED_BY", length = 255)
	protected String createdBy;

	@Column(name = "MODIFIED_BY", length = 255)
	protected String modifiedBy;

	@Column(name = "CREATED_DateTime")
	protected Timestamp createdDateTime;

	@Column(name = "MODIFIED_DateTime")
	protected Timestamp modifiedDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public int compareTo(BaseDTO next) {
		// TODO Auto-generated method stub
		return next.getValue().compareTo(next.getValue());
	}

}

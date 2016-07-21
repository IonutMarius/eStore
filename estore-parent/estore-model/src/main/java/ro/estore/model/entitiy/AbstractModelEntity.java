package ro.estore.model.entitiy;

import java.time.LocalDateTime;

import javax.persistence.Column;

public abstract class AbstractModelEntity {

	@Column(name = "created_by", nullable = true)
	private String createdBy;
	@Column(name = "created_date", nullable = true)
	private LocalDateTime createdDate;
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;
	@Column(name = "modified_date", nullable = true)
	private LocalDateTime modifiedDate;

	public abstract Long getId();

	public abstract void setId(Long id);

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}

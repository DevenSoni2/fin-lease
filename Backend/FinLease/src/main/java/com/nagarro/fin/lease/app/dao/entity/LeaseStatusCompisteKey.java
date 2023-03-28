package com.nagarro.fin.lease.app.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * The Class LeaseStatusCompisteKey.
 */
@Embeddable
public class LeaseStatusCompisteKey implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The reference id. */
	@OneToOne
	@JoinColumn(name = "referenceId", referencedColumnName = "referenceId")
	private LeaseDetail referenceId;
	
	/** The updated by. */
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User updatedBy;

	/**
	 * Gets the reference id.
	 *
	 * @return the reference id
	 */
	public LeaseDetail getReferenceId() {
		return referenceId;
	}

	/**
	 * Sets the reference id.
	 *
	 * @param referenceId the new reference id
	 */
	public void setReferenceId(LeaseDetail referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public User getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

}

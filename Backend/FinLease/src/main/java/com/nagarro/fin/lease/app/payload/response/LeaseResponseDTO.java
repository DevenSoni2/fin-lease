package com.nagarro.fin.lease.app.payload.response;

import java.time.LocalDateTime;

import com.nagarro.fin.lease.app.payload.request.LeaseRequestDTO;

/**
 * The Class LeaseResponseDTO.
 */
public class LeaseResponseDTO extends LeaseRequestDTO {
	
	/** The reference id. */
	private String referenceId;

	/** The buisness unit. */
	private String buisnessUnit;
	
	/** The propser user name. */
	private String propserUserName;
	
	/** The proposed customer. */
	private String proposedCustomer;
	
	/** The approval user id. */
	private String approvalUserId;
	
	/** The approval date. */
	private LocalDateTime approvalDate;
	
	/** The approval comment. */
	private String approvalComment;

	public String getProposedCustomer() {
		return proposedCustomer;
	}

	public void setProposedCustomer(String proposedCustomer) {
		this.proposedCustomer = proposedCustomer;
	}

	/**
	 * Gets the approval user id.
	 *
	 * @return the approval user id
	 */
	public String getApprovalUserId() {
		return approvalUserId;
	}

	/**
	 * Sets the approval user id.
	 *
	 * @param approvalUserId the new approval user id
	 */
	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	/**
	 * Gets the approval date.
	 *
	 * @return the approval date
	 */
	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}

	/**
	 * Sets the approval date.
	 *
	 * @param approvalDate the new approval date
	 */
	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * Gets the approval comment.
	 *
	 * @return the approval comment
	 */
	public String getApprovalComment() {
		return approvalComment;
	}

	/**
	 * Sets the approval comment.
	 *
	 * @param approvalComment the new approval comment
	 */
	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	/**
	 * Gets the propser user name.
	 *
	 * @return the propser user name
	 */
	public String getPropserUserName() {
		return propserUserName;
	}

	/**
	 * Sets the propser user name.
	 *
	 * @param propserUserName the new propser user name
	 */
	public void setPropserUserName(String propserUserName) {
		this.propserUserName = propserUserName;
	}

	/**
	 * Gets the reference id.
	 *
	 * @return the reference id
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * Sets the reference id.
	 *
	 * @param referenceId the new reference id
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * Gets the buisness unit.
	 *
	 * @return the buisness unit
	 */
	public String getBuisnessUnit() {
		return buisnessUnit;
	}

	/**
	 * Sets the buisness unit.
	 *
	 * @param buisnessUnit the new buisness unit
	 */
	public void setBuisnessUnit(String buisnessUnit) {
		this.buisnessUnit = buisnessUnit;
	}

}

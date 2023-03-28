package com.nagarro.fin.lease.app.payload.request;

import java.time.LocalDateTime;

/**
 * The Class UpdateLeaseStatusReq.
 */
public class UpdateLeaseStatusReq {
	
	/** The lease reference id. */
	private String leaseReferenceId;
	
	/** The status. */
	private String status;
	
	/** The comment. */
	private String comment;
	
	/** The account officer comment. */
	private String accountOfficerComment;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The approval date. */
	private LocalDateTime approvalDate;
	
	/** The approval amount. */
	private String approvalAmount;
	
	/** The interest rate. */
	private String interestRate;
	
	/** The monthly repayment. */
	private String monthlyRepayment;
	
	/** The residual value. */
	private String residualValue;

	/**
	 * Gets the approval amount.
	 *
	 * @return the approval amount
	 */
	public String getApprovalAmount() {
		return approvalAmount;
	}

	/**
	 * Sets the approval amount.
	 *
	 * @param approvalAmount the new approval amount
	 */
	public void setApprovalAmount(String approvalAmount) {
		this.approvalAmount = approvalAmount;
	}

	/**
	 * Gets the interest rate.
	 *
	 * @return the interest rate
	 */
	public String getInterestRate() {
		return interestRate;
	}

	/**
	 * Sets the interest rate.
	 *
	 * @param interestRate the new interest rate
	 */
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * Gets the monthly repayment.
	 *
	 * @return the monthly repayment
	 */
	public String getMonthlyRepayment() {
		return monthlyRepayment;
	}

	/**
	 * Sets the monthly repayment.
	 *
	 * @param monthlyRepayment the new monthly repayment
	 */
	public void setMonthlyRepayment(String monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	/**
	 * Gets the residual value.
	 *
	 * @return the residual value
	 */
	public String getResidualValue() {
		return residualValue;
	}

	/**
	 * Sets the residual value.
	 *
	 * @param residualValue the new residual value
	 */
	public void setResidualValue(String residualValue) {
		this.residualValue = residualValue;
	}

	/**
	 * Gets the lease reference id.
	 *
	 * @return the lease reference id
	 */
	public String getLeaseReferenceId() {
		return leaseReferenceId;
	}

	/**
	 * Sets the lease reference id.
	 *
	 * @param leaseReferenceId the new lease reference id
	 */
	public void setLeaseReferenceId(String leaseReferenceId) {
		this.leaseReferenceId = leaseReferenceId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the account officer comment.
	 *
	 * @return the account officer comment
	 */
	public String getAccountOfficerComment() {
		return accountOfficerComment;
	}

	/**
	 * Sets the account officer comment.
	 *
	 * @param accountOfficerComment the new account officer comment
	 */
	public void setAccountOfficerComment(String accountOfficerComment) {
		this.accountOfficerComment = accountOfficerComment;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

}

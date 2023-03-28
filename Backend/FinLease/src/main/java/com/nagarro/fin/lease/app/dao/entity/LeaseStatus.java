package com.nagarro.fin.lease.app.dao.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class LeaseStatus.
 */
@Entity
@Table(name = "lease_status_detail")
public class LeaseStatus {
	
	/** The lease status compiste id. */
	@EmbeddedId
	private LeaseStatusCompisteKey leaseStatusCompisteId;

	/** The comment. */
	@Column(name = "comment", length = 1000)
	private String comment;

	/** The account officer comment. */
	@Column(name = "account_officer_comment", length = 1000)
	private String accountOfficerComment;
	
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
	 * Gets the lease status compiste id.
	 *
	 * @return the lease status compiste id
	 */
	public LeaseStatusCompisteKey getLeaseStatusCompisteId() {
		return leaseStatusCompisteId;
	}

	/**
	 * Sets the lease status compiste id.
	 *
	 * @param leaseStatusCompisteId the new lease status compiste id
	 */
	public void setLeaseStatusCompisteId(LeaseStatusCompisteKey leaseStatusCompisteId) {
		this.leaseStatusCompisteId = leaseStatusCompisteId;
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

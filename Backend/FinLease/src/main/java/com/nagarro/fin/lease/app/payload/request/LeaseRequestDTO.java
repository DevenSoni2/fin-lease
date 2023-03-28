package com.nagarro.fin.lease.app.payload.request;

import java.time.LocalDateTime;

/**
 * The Class LeaseRequestDTO.
 */
public class LeaseRequestDTO {
	
	/** The created time. */
	private LocalDateTime createdTime;
	
	/** The customer number. */
	private String customerNumber;
	
	/** The customer account number. */
	private String customerAccountNumber;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The asset make. */
	private String assetMake;
	
	/** The asset model. */
	private String assetModel;
	
	/** The asset delivery date. */
	private LocalDateTime assetDeliveryDate;
	
	/** The asset purpose. */
	private String assetPurpose;
	
	/** The monthly income. */
	private String monthlyIncome;
	
	/** The lease type. */
	private String leaseType;
	
	/** The required amount. */
	private String requiredAmount;
	
	/** The lease period in month. */
	private Integer leasePeriodInMonth;
	
	/** The status. */
	private String status;
	
	/** The created by. */
	private String createdBy;
	
	/** The proposer user id. */
	private String proposerUserId;
	
	/** The customer name. */
	private String customerName;
	
	/** The other income. */
	private String otherIncome;
	
	/** The total income. */
	private String totalIncome;
	
	/** The total commitment MCB. */
	private String totalCommitmentMCB;
	
	/** The total commitment other. */
	private String totalCommitmentOther;
	
	/** The credit turnover. */
	private String creditTurnover;
	
	/** The debit turnover. */
	private String debitTurnover;
	
	/** The set up parameter. */
	private String setUpParameter;
	
	/** The comment recommendation. */
	private String commentRecommendation;

	/**
	 * Gets the total commitment MCB.
	 *
	 * @return the total commitment MCB
	 */
	public String getTotalCommitmentMCB() {
		return totalCommitmentMCB;
	}

	/**
	 * Sets the total commitment MCB.
	 *
	 * @param totalCommitmentMCB the new total commitment MCB
	 */
	public void setTotalCommitmentMCB(String totalCommitmentMCB) {
		this.totalCommitmentMCB = totalCommitmentMCB;
	}

	/**
	 * Gets the total commitment other.
	 *
	 * @return the total commitment other
	 */
	public String getTotalCommitmentOther() {
		return totalCommitmentOther;
	}

	/**
	 * Sets the total commitment other.
	 *
	 * @param totalCommitmentOther the new total commitment other
	 */
	public void setTotalCommitmentOther(String totalCommitmentOther) {
		this.totalCommitmentOther = totalCommitmentOther;
	}

	/**
	 * Gets the credit turnover.
	 *
	 * @return the credit turnover
	 */
	public String getCreditTurnover() {
		return creditTurnover;
	}

	/**
	 * Sets the credit turnover.
	 *
	 * @param creditTurnover the new credit turnover
	 */
	public void setCreditTurnover(String creditTurnover) {
		this.creditTurnover = creditTurnover;
	}

	/**
	 * Gets the debit turnover.
	 *
	 * @return the debit turnover
	 */
	public String getDebitTurnover() {
		return debitTurnover;
	}

	/**
	 * Sets the debit turnover.
	 *
	 * @param debitTurnover the new debit turnover
	 */
	public void setDebitTurnover(String debitTurnover) {
		this.debitTurnover = debitTurnover;
	}

	/**
	 * Gets the sets the up parameter.
	 *
	 * @return the sets the up parameter
	 */
	public String getSetUpParameter() {
		return setUpParameter;
	}

	/**
	 * Sets the sets the up parameter.
	 *
	 * @param setUpParameter the new sets the up parameter
	 */
	public void setSetUpParameter(String setUpParameter) {
		this.setUpParameter = setUpParameter;
	}

	/**
	 * Gets the comment recommendation.
	 *
	 * @return the comment recommendation
	 */
	public String getCommentRecommendation() {
		return commentRecommendation;
	}

	/**
	 * Sets the comment recommendation.
	 *
	 * @param commentRecommendation the new comment recommendation
	 */
	public void setCommentRecommendation(String commentRecommendation) {
		this.commentRecommendation = commentRecommendation;
	}

	/**
	 * Gets the total income.
	 *
	 * @return the total income
	 */
	public String getTotalIncome() {
		return totalIncome;
	}

	/**
	 * Sets the total income.
	 *
	 * @param totalIncome the new total income
	 */
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	/**
	 * Gets the monthly income.
	 *
	 * @return the monthly income
	 */
	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	/**
	 * Sets the monthly income.
	 *
	 * @param monthlyIncome the new monthly income
	 */
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	/**
	 * Gets the other income.
	 *
	 * @return the other income
	 */
	public String getOtherIncome() {
		return otherIncome;
	}

	/**
	 * Sets the other income.
	 *
	 * @param otherIncome the new other income
	 */
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the created time.
	 *
	 * @return the created time
	 */
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	/**
	 * Sets the created time.
	 *
	 * @param createdTime the new created time
	 */
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * Gets the customer number.
	 *
	 * @return the customer number
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * Sets the customer number.
	 *
	 * @param customerNumber the new customer number
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * Gets the customer account number.
	 *
	 * @return the customer account number
	 */
	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	/**
	 * Sets the customer account number.
	 *
	 * @param customerAccountNumber the new customer account number
	 */
	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the asset make.
	 *
	 * @return the asset make
	 */
	public String getAssetMake() {
		return assetMake;
	}

	/**
	 * Sets the asset make.
	 *
	 * @param assetMake the new asset make
	 */
	public void setAssetMake(String assetMake) {
		this.assetMake = assetMake;
	}

	/**
	 * Gets the asset model.
	 *
	 * @return the asset model
	 */
	public String getAssetModel() {
		return assetModel;
	}

	/**
	 * Sets the asset model.
	 *
	 * @param assetModel the new asset model
	 */
	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	/**
	 * Gets the asset delivery date.
	 *
	 * @return the asset delivery date
	 */
	public LocalDateTime getAssetDeliveryDate() {
		return assetDeliveryDate;
	}

	/**
	 * Sets the asset delivery date.
	 *
	 * @param assetDeliveryDate the new asset delivery date
	 */
	public void setAssetDeliveryDate(LocalDateTime assetDeliveryDate) {
		this.assetDeliveryDate = assetDeliveryDate;
	}

	/**
	 * Gets the asset purpose.
	 *
	 * @return the asset purpose
	 */
	public String getAssetPurpose() {
		return assetPurpose;
	}

	/**
	 * Sets the asset purpose.
	 *
	 * @param assetPurpose the new asset purpose
	 */
	public void setAssetPurpose(String assetPurpose) {
		this.assetPurpose = assetPurpose;
	}

	/**
	 * Gets the lease type.
	 *
	 * @return the lease type
	 */
	public String getLeaseType() {
		return leaseType;
	}

	/**
	 * Sets the lease type.
	 *
	 * @param leaseType the new lease type
	 */
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	/**
	 * Gets the required amount.
	 *
	 * @return the required amount
	 */
	public String getRequiredAmount() {
		return requiredAmount;
	}

	/**
	 * Sets the required amount.
	 *
	 * @param requiredAmount the new required amount
	 */
	public void setRequiredAmount(String requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	/**
	 * Gets the lease period in month.
	 *
	 * @return the lease period in month
	 */
	public Integer getLeasePeriodInMonth() {
		return leasePeriodInMonth;
	}

	/**
	 * Sets the lease period in month.
	 *
	 * @param leasePeriodInMonth the new lease period in month
	 */
	public void setLeasePeriodInMonth(Integer leasePeriodInMonth) {
		this.leasePeriodInMonth = leasePeriodInMonth;
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
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the proposer user id.
	 *
	 * @return the proposer user id
	 */
	public String getProposerUserId() {
		return proposerUserId;
	}

	/**
	 * Sets the proposer user id.
	 *
	 * @param proposerUserId the new proposer user id
	 */
	public void setProposerUserId(String proposerUserId) {
		this.proposerUserId = proposerUserId;
	}

}

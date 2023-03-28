package com.nagarro.fin.lease.app.payload.response;

/**
 * The Class CustomResponse.
 */
public class CustomResponse {
	
	/** The message. */
	private String message;
	
	/** The reference id. */
	private String referenceId;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * Instantiates a new custom response.
	 *
	 * @param message the message
	 * @param referenceId the reference id
	 */
	public CustomResponse(String message, String referenceId) {
		super();
		this.message = message;
		this.referenceId = referenceId;
	}

}

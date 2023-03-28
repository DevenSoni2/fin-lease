package com.nagarro.fin.lease.app.payload.response;

/**
 * The Class UserDetail.
 */
public class UserDetail {
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The buisness unit. */
	private String buisnessUnit;
	
	/** The role id. */
	private Integer roleId;

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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

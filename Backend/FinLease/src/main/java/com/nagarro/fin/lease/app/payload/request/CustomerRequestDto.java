package com.nagarro.fin.lease.app.payload.request;

public class CustomerRequestDto {
	private String name;
	private String userName;
	private String buisnessUnit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBuisnessUnit() {
		return buisnessUnit;
	}

	public void setBuisnessUnit(String buisnessUnit) {
		this.buisnessUnit = buisnessUnit;
	}

}

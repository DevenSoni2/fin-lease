package com.nagarro.fin.lease.app.payload.response;

import com.nagarro.fin.lease.app.payload.request.CustomerRequestDto;

public class CustomerResponseDTO extends CustomerRequestDto {

	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}

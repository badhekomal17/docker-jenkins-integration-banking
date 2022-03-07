package com.zensar.banking.model;

import org.springframework.stereotype.Component;

@Component
public class BaseResponse {

	private StatusData status;
	private Object data;

	public BaseResponse() {
		super();
	}

	public BaseResponse(StatusData status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public StatusData getStatus() {
		return status;
	}

	public void setStatus(StatusData status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

package com.zensar.banking.model;

import org.springframework.stereotype.Component;

@Component
public class StatusData {
	private String statusMessage;
	private int statusCode;

	public StatusData(String statusMessage, int statusCode) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
	}

	public StatusData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}

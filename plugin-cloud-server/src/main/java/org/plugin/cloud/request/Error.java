package org.plugin.cloud.request;

public class Error {
	String errorMessage;

	public Error(String error) {
		this.errorMessage = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

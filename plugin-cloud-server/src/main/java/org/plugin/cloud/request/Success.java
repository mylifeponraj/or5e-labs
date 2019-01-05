package org.plugin.cloud.request;

public class Success {
	String sucessMessage;
	String statusCode;
	
	public Success(String success, String statusCode) {
		this.sucessMessage = success;
		this.statusCode = statusCode;
	}
	public String getSucessMessage() {
		return sucessMessage;
	}
	public void setSucessMessage(String sucessMessage) {
		this.sucessMessage = sucessMessage;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}

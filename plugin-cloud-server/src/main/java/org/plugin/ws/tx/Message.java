package org.plugin.ws.tx;

public class Message {
    private String messageFrom;
    private String messageTo;
    private String messageType;
    private String masterUnitLicense;
    private String message;
	public String getMessageFrom() {
		return messageFrom;
	}
	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}
	public String getMessageTo() {
		return messageTo;
	}
	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMasterUnitLicense() {
		return masterUnitLicense;
	}
	public void setMasterUnitLicense(String masterUnitLicense) {
		this.masterUnitLicense = masterUnitLicense;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

package org.or5e.core.plugin.intent;

public class Intent {
	public String intentName;
	public Object intentMessage;
	public Intent(String intentName, Object intentMessage) {
		this.intentName = intentName;
		this.intentMessage = intentMessage;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public Object getIntentMessage() {
		return intentMessage;
	}
	public void setIntentMessage(Object intentMessage) {
		this.intentMessage = intentMessage;
	}
}
package org.or5e.plugin.manage;

public class EventData {
	public String eventName;
	public String eventListenerCount;
	public String eventListeningPluginName;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventListenerCount() {
		return eventListenerCount;
	}
	public void setEventListenerCount(String eventListenerCount) {
		this.eventListenerCount = eventListenerCount;
	}
	public String getEventListeningPluginName() {
		return eventListeningPluginName;
	}
	public void setEventListeningPluginName(String eventListeningPluginName) {
		this.eventListeningPluginName = eventListeningPluginName;
	}
}

package org.or5e.core.plugin.event;

public class EventMessage {
	public String eventSource = null;
	public String eventName = null;
	public Object eventData = null;
	public String getEventSource() {
		return eventSource;
	}
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Object getEventData() {
		return eventData;
	}
	public void setEventData(Object eventData) {
		this.eventData = eventData;
	}

	@Override public String toString() {
		return "Event Source: "+this.eventSource+", Name: "+this.eventName;
	}
}

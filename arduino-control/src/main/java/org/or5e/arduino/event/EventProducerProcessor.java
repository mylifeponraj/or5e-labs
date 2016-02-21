package org.or5e.arduino.event;

import java.util.ArrayList;

import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.EventQueue;
import org.or5e.core.plugin.event.PluginEventQueueSPI;
import org.or5e.core.plugin.event.io.EventProducer;

public class EventProducerProcessor extends ArduinoIO implements EventProducer{
	private EventQueue _queue = null;
	private Plugin _plugin;
	public EventProducerProcessor(Plugin plugin, EventQueue queue) {
		_queue = queue;
		_plugin = plugin;
	}

	@Override public void raiseEventMessage(String pluginID, String eventName, Object eventData) {
		PluginEventQueueSPI.getDefaultQueue().injectEventIntoQueue(_plugin, eventName, eventData);
	}

	@Override public void initilize() {
		eventToBeRaised = new ArrayList<String>();

		eventToBeRaised.add("SliderSensorValue");
		eventToBeRaised.add("LeftBtn");
		eventToBeRaised.add("RightBtn");
		eventToBeRaised.add("TopBtn");
		eventToBeRaised.add("BottomBtn");
		eventToBeRaised.add("TempSensorValue");
		eventToBeRaised.add("LightSensorValue");
		eventToBeRaised.add("SoundSensorValue");
		eventToBeRaised.add("JoystickValue");
		eventToBeRaised.add("3AxisValueValue");

		_queue.publishEventToQueue(_plugin, eventToBeRaised.toArray(new String[this.eventToBeRaised.size()]));
	}
}

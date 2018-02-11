package org.or5e.arduino;

import java.io.BufferedReader;
import java.util.StringTokenizer;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.intent.IntentQueue;
import org.or5e.core.plugin.intent.IntentQueueSPI;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ArduinoPortRequestHandler extends BaseObject implements SerialPortEventListener{
	private BufferedReader _requestReader;
	private IntentQueue _queue;
	public ArduinoPortRequestHandler(BufferedReader input) {
		this._requestReader = input;
		this._queue = IntentQueueSPI.getIntentQueue();
	}
	@Override public void serialEvent(SerialPortEvent portEvent) {
		info("Recived Message from COM Port :"+portEvent.getEventType());
		if (portEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = _requestReader.readLine();
				System.out.println(inputLine);
				StringTokenizer token = new StringTokenizer(inputLine, "|");
				if(token.hasMoreTokens())
					this._queue.raiseIntentToDefaultQueue(token.nextToken(), token.nextToken());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override public String getName() {
		return "ArduinoPortRequestHandler";
	}
}
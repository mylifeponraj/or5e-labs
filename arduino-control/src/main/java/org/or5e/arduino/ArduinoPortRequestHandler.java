package org.or5e.arduino;

import java.io.BufferedReader;

import org.or5e.core.BaseObject;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ArduinoPortRequestHandler extends BaseObject implements SerialPortEventListener{
	private BufferedReader _requestReader;
	private ArduinoEsploraControlerAPI _controller;
	public ArduinoPortRequestHandler(BufferedReader input, ArduinoEsploraControlerAPI controller) {
		this._requestReader = input;
		this._controller = controller;
	}
	@Override public void serialEvent(SerialPortEvent portEvent) {
		info("Recived Message from COM Port :"+portEvent.getEventType());
		if (portEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = _requestReader.readLine();
				System.out.println(inputLine);
				_controller.raiseEvent("Arduino", inputLine);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override public String getName() {
		return "ArduinoPortRequestHandler";
	}
}
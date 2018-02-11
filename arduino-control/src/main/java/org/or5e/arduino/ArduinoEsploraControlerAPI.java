package org.or5e.arduino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;
import org.or5e.core.plugin.intent.IntentQueue;
import org.or5e.core.plugin.intent.IntentQueueSPI;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class ArduinoEsploraControlerAPI extends PluginLifecycleAdaptor {
	private SerialPort _serialPort;
	private static final String PORT_NAME;
	private BufferedReader input;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	private ArduinoPortRequestHandler _requestHandler;
	private Boolean stopFlag = Boolean.FALSE;
	private IntentQueue _queue;
	private List<String> eventIWillRaise;
	static {
		PORT_NAME = getProperties("comPort");
	}
	public ArduinoEsploraControlerAPI() {
		_queue = IntentQueueSPI.getIntentQueue();
		eventIWillRaise = new ArrayList<String>();
	}
	
	@Override public String getPluginID() {
		return "ArduinoEsplora";
	}

	@Override public void initilize() throws PluginException { 
		eventThatWillBeRaised();
	}
	public void eventThatWillBeRaised() {
		debug("Registring Event that will be raised by Arduino Esplora.");
		eventIWillRaise.add("SliderSensorValue");
		eventIWillRaise.add("LeftBtn");
		eventIWillRaise.add("RightBtn");
		eventIWillRaise.add("TopBtn");
		eventIWillRaise.add("BottomBtn");
		eventIWillRaise.add("TempSensorValue");
		eventIWillRaise.add("LightSensorValue");
		eventIWillRaise.add("SoundSensorValue");
		eventIWillRaise.add("JoystickValue");
		eventIWillRaise.add("3AxisValueValue");
		_queue.registerForIntent(eventIWillRaise.toArray(new String[this.eventIWillRaise.size()]));
	}
	@SuppressWarnings("null")
	@Override public void doProcess() throws PluginException {
		try {
			String message = "Waiting for Arduino Esplora to connec to Port: ["+PORT_NAME+"]";
			debug("About to create COM Port Identifier...");
			CommPortIdentifier portID = null;
			while(portID == null && !this.stopFlag) {
				debug(message);
				try {
					portID = CommPortIdentifier.getPortIdentifier(PORT_NAME);
				}
				catch(NoSuchPortException exception) {
					portID = null;
					debug("Waiting for 5 Seconds");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
			debug("About to Open the COM Port...");
			this._serialPort = (SerialPort)portID.open(getName(), TIME_OUT);

			debug("Setting up COM Port Parameters...");
			this._serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			debug("Creating Input Stream to Read data from COM Port...");
			this.input = new BufferedReader(new InputStreamReader(this._serialPort.getInputStream()));

			debug("Creating PORT Handler...");
			_requestHandler = new ArduinoPortRequestHandler(this.input);

			debug("Adding PORT Event Listeners...");
			this._serialPort.addEventListener(_requestHandler);
			this._serialPort.notifyOnDataAvailable(true);
			debug("Started Listening to Event on COM Port...");
		} catch (PortInUseException | UnsupportedCommOperationException | IOException | TooManyListenersException e) {
			e.printStackTrace();
		}
	}
	@Override public String getName() {
		return "org.or5e.arduino.ArduinoEsploraControlerAPI";
	}
	@Override public void destroy() {
		super.destroy();
		debug("Destroying Arduino Esplora.");
		this.stopFlag = Boolean.TRUE;
		if (this._serialPort != null) {
			this._serialPort.removeEventListener();
			this._serialPort.close();
		}
	}
	public static void main(String[] args) {
		Plugin plugin = new ArduinoEsploraControlerAPI();
		plugin.startPlugin();
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Plugin is about to Destory");
		plugin.destroy();
		System.out.println("Main Exiting...");
	}
}

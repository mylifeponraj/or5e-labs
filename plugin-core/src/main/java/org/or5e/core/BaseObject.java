package org.or5e.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class BaseObject {
	protected static Properties _props = null;
	private static FileOutputStream outStream = null;
	private static String rootDrive = null;
	private static String configFileName = null;
	private Logger _logger;
	static {
		_props = new Properties();
		try {
			rootDrive = Paths.get(".").toAbsolutePath().getRoot().toString()+"000-Application\\09-AppData\\";
			configFileName = rootDrive+"03-Config\\application.xml";
			System.out.println("Loading config file: "+configFileName);
			_props.loadFromXML(new FileInputStream(configFileName));
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override public void run() {
					System.out.println("Writting Properties Files...");
					writeProperties();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public BaseObject() {
		this._logger = Logger.getLogger(getName());
	}

	public void info(Object message) {
		this._logger.info(message);
	}

	public void debug(Object message) {
		this._logger.debug(message);
	}

	public void warn(Object message) {
		this._logger.warn(message);
	}

	public void error(Object message) {
		this._logger.error(message);
	}

	public static String getProperties(String key) {
		return _props.getProperty(key);
	}
	public Properties getAllPropertiesFromFile() {
		return _props;
	}
	public static void writeProperties() {
		try {
			outStream = new FileOutputStream(configFileName);
			_props.storeToXML(outStream, "Plugin Core Configurations");
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setProperties(String key, String value) {
		_props.setProperty(key, value);
	}

	public abstract String getName();
	public static void main(String[] args) {
		new BaseObject() {
			@Override public String getName() {
				return "org.or5e.core.BaseObject";
			}
		};
	}
}
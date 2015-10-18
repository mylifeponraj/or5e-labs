package org.or5e.core;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class BaseObject {
	private static Properties _props = null;
	private Logger _logger;

	static {
		_props = new Properties();
		try {
			_props.loadFromXML(ClassLoader.getSystemResourceAsStream("application.xml"));
			System.out.println("XML File loaded: " + _props.getProperty("pluginFolder"));
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

	public static void setProperties(String key, String value) {
		_props.setProperty(key, value);
	}

	public abstract String getName();
}
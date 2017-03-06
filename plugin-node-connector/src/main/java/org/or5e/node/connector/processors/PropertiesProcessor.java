package org.or5e.node.connector.processors;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.or5e.core.BaseObject;
import org.or5e.node.connector.Processors;

public class PropertiesProcessor extends BaseObject implements Processors {
	private final static PropertiesProcessor _processor;
	private final Map<String, String> propertiesMap;
	static {
		_processor = new PropertiesProcessor();
	}
	public PropertiesProcessor() {
		propertiesMap = new HashMap<>();
	}
	public static PropertiesProcessor getPropertiesProcessor () {
		return _processor;
	}

	public String getAllProperties () {
		propertiesMap.clear();
		Set<Entry<Object,Object>> entrySet = _props.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			propertiesMap.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return mapToString();
	}
	public String modifyAndGetAppProperties (String key, String value) {
		propertiesMap.put(key, value);
		setProperties(key, value);
		return getAllProperties ();
	}
	private String mapToString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("{'properties':[");

		Set<String> keySet = propertiesMap.keySet();
		for (String key : keySet) {
			_builder.append("{");
			_builder.append("'"+key+"':'"+propertiesMap.get(key)+"'");
			_builder.append("},");
		}

		_builder.append("]}");
		return null;
	}
	@Override public String getName() {
		return "PropertiesProcessor";
	}

}

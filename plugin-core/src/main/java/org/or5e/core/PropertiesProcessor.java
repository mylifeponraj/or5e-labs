package org.or5e.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class PropertiesProcessor extends BaseObject{
//	private final JSONParser _parser;
//	private JSONArray jsonProperties;
	private Map<String, String> mapPropreties;
	public PropertiesProcessor() {
		mapPropreties = new HashMap<>();
//		_parser = new JSONParser();
		//loadJSONProperties();
	}

//	public void loadJSONProperties() {
//		String jsonPropFileName = getProperties("json-settings-file");
//		try {
//			Object rootObject = _parser.parse(new FileReader(jsonPropFileName));
//			JSONObject jsonRootObject = (JSONObject) rootObject;
//			jsonProperties = (JSONArray) jsonRootObject.get("properties");
//			for (Object object : jsonProperties) {
//				JSONObject obj = (JSONObject) object;
//				System.out.println(obj.entrySet());
//			}
//			
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
//	}
	public void addOrModifyProperties(String key, String value) {
		setProperties(key, value);
	}
	public Map<String, String> getAllProperties() {
		mapPropreties.clear();
		Set<Entry<Object,Object>> entrySet = _props.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			mapPropreties.put((String)entry.getKey(), (String)entry.getValue());
		}
		return mapPropreties;
	}
}

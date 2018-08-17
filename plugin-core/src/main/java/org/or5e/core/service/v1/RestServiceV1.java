package org.or5e.core.service.v1;

import java.util.Map;

public interface RestServiceV1 {
	public Map<String, String> getAllProperties();
	public Boolean addProperty(String key, String value);
	public Boolean updateProperty(String key, String value);
}

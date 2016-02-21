package org.plugin.core.server.rest.v1;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.or5e.core.BaseObject;

@Path("/props")
public class PropertiesController extends BaseObject{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	public Map<String, String> getAllProperties() {
		Map<String, String> response = new HashMap<String, String>();
		Properties allProperties = getAllPropertiesFromFile();
		Set<Entry<Object,Object>> entrySet = allProperties.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			response.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return response;
	}

	@Override public String getName() {
		return "PropertiesController";
	}
}

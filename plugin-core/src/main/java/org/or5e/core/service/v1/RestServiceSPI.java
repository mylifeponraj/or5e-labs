package org.or5e.core.service.v1;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.or5e.core.ds.ExpenceManager;
import org.or5e.core.ds.vo.ExpenceType;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginWithRestServiceAdaptor;

import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import spark.Route;

public class RestServiceSPI extends PluginWithRestServiceAdaptor implements RestServiceV1 {
	private static RestServiceSPI _service;
	static {
		_service = new RestServiceSPI();
	}
	private ResponseTransformer transformer = new JsonResponseTransformer();
	private RestServiceSPI() {}

	public static RestServiceSPI getRestService() {
		return _service;
	}

	@Override public String getName() {
		return "org.or5e.core.service.v1.RestServiceV1";
	}

	@Override public Map<String, String> getAllProperties() {
		Map<String, String> returnData = new HashMap<>();
		Properties prop = getAllPropertiesFromFile();
		Set<Object> keySet = prop.keySet();
		for (Object keyObj : keySet) {
			String key = (String) keyObj;
			returnData.put(key, prop.getProperty(key));
		}
		return returnData;
	}

	@Override public Boolean addProperty(String key, String value) {
		setProperties(key, value);
		writeProperties();
		return Boolean.TRUE;
	}

	@Override public Boolean updateProperty(String key, String value) {
		String propValue = getProperties(key);
		if (propValue != null) {
			setProperties(key, value);
			writeProperties();
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static void main(String[] args) {
		RestServiceSPI.getRestService().initilizeService();
	}

	@Override public void initilizeService() {
		get("/getAllProperties", (req, res) -> {
			res.type("application/json");
			Map<String, String> allProperties = getAllProperties();
			System.out.println(allProperties);
			return allProperties;
		}, transformer);

		post("/addProp/*", new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String queryString = request.url();
				System.out.println(request.body());
				StringTokenizer tokens = new StringTokenizer(queryString, "//");
				int index=0;
				String key ="";
				String value = "";
				while (tokens.hasMoreTokens()) {
					String val = tokens.nextToken();
					System.out.println(index+++":"+val);
					if(index == 4) {
						key = val;
					}
					else if(index == 5) {
						value = val;
					}
				}
				addProperty(key, value);
				return "{'admin':'admin', 'pwd':'abcd1234'}";
			}
		});
		get("/getAllExpenceType", (req, res) -> {
			res.type("application/json");
			ExpenceManager em = new ExpenceManager();
			List<ExpenceType> expenceType = em.getExpenceType();
			return expenceType;
		}, transformer);

	}
	@Override public String getPluginID() {
		return getName();
	}
}
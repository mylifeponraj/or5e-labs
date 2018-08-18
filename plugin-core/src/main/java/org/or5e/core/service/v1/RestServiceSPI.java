package org.or5e.core.service.v1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.options;
import static spark.Spark.before;

import org.or5e.core.BaseObject;

import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import spark.Route;

public class RestServiceSPI extends BaseObject implements RestServiceV1 {
	private static RestServiceSPI _service;
	static {
		_service = new RestServiceSPI();
	}
	private ResponseTransformer transformer = new JsonResponseTransformer();

	private RestServiceSPI() {
	}

	public static RestServiceSPI getRestService() {
		return _service;
	}

	public void initilizeAllServices() {
		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
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
	}

	@Override
	public String getName() {
		return "org.or5e.core.service.v1.RestServiceV1";
	}

	@Override
	public Map<String, String> getAllProperties() {
		Map<String, String> returnData = new HashMap<>();
		Properties prop = getAllPropertiesFromFile();
		Set<Object> keySet = prop.keySet();
		for (Object keyObj : keySet) {
			String key = (String) keyObj;
			returnData.put(key, prop.getProperty(key));
		}
		return returnData;
	}

	@Override
	public Boolean addProperty(String key, String value) {
		setProperties(key, value);
		writeProperties();
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateProperty(String key, String value) {
		String propValue = getProperties(key);
		if (propValue != null) {
			setProperties(key, value);
			writeProperties();
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static void main(String[] args) {
		RestServiceSPI.getRestService().initilizeAllServices();
	}
}

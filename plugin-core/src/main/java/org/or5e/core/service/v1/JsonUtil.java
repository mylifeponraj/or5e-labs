package org.or5e.core.service.v1;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonUtil {
	public static String toJson(Object object) {
		System.out.println("Sending JSON String.");
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
}

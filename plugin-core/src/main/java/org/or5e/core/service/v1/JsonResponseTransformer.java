package org.or5e.core.service.v1;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {
	@Override public String render(Object model) throws Exception {
		System.out.println("Calling responce Transformer.");
		return new Gson().toJson(model);
	}
}
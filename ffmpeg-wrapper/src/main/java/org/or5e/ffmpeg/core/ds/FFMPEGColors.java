package org.or5e.ffmpeg.core.ds;

import java.util.HashMap;
import java.util.Map;

public class FFMPEGColors {
	private Map<String, String> supportedColors = null;
	public FFMPEGColors() {
		this.supportedColors = new HashMap<>();
	}
	public void clearMap() {
		this.supportedColors.clear();
	}
	public void addColor(String key, String value) {
		this.supportedColors.put(key, value);
	}
	public Map<String, String> getSupportedColors() {
		return supportedColors;
	}
	public String getColor(String color) {
		return this.supportedColors.get(color);
	}
}
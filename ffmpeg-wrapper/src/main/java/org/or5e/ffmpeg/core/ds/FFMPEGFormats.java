package org.or5e.ffmpeg.core.ds;

import java.util.HashMap;
import java.util.Map;

public class FFMPEGFormats {
	private Map<String, String> muxingFormatsSupported = null;
	private Map<String, String> demuxingFormatsSupported = null;

	public FFMPEGFormats() {
		this.muxingFormatsSupported = new HashMap<String, String>();
		this.demuxingFormatsSupported = new HashMap<String, String>();
	}
	public void addMuxingFormatSupported(String key, String value) {
		this.muxingFormatsSupported.put(key, value);
	}
	public void addDeMuxingFormatSupported(String key, String value) {
		this.demuxingFormatsSupported.put(key, value);
	}
	public Map<String, String> getMuxingFormatsSupported() {
		return muxingFormatsSupported;
	}
	public Map<String, String> getDemuxingFormatsSupported() {
		return demuxingFormatsSupported;
	}
}

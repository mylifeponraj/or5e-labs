package org.or5e.ffmpeg.core.ds;

import java.util.ArrayList;
import java.util.List;

public class FFMPEGDevices {
	public List<String> videoDevices = null;
	public List<String> audioDevices = null;
	public FFMPEGDevices() {
		videoDevices = new ArrayList<>();
		audioDevices = new ArrayList<>();
	}
	public void addVideoDevice(String videoDevice) {
		videoDevices.add(videoDevice);
	}
	public void addAudioDevice(String audioDevice) {
		audioDevices.add(audioDevice);
	}
	@Override public String toString() {
		StringBuilder response = new StringBuilder();
		response.append("Video Devices: ");
		for (String vDevice : videoDevices) {
			response.append(vDevice);
			response.append(", ");
		}
		response.append("\nAudio Devices: ");
		for (String aDevice : audioDevices) {
			response.append(aDevice);
			response.append(", ");
		}
		return response.toString();
	}

}
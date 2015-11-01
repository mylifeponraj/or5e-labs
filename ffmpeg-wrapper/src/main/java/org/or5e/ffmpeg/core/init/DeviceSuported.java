package org.or5e.ffmpeg.core.init;

import org.or5e.ffmpeg.core.ds.FFMPEGDevices;

public class DeviceSuported extends FFMPEGSupport<FFMPEGDevices> {
	public Boolean isVideoDevice=Boolean.TRUE;

	public FFMPEGDevices _deviceSupported;
	public DeviceSuported() {
		super(new FFMPEGDevices());
		_deviceSupported = getFfmpegSupportFor();
	}

	@Override public void outputReceived(String readLine, Integer lineNumner) {
		if(readLine.contains("DirectShow video")) {
			isVideoDevice = Boolean.TRUE;
		}
		else if(readLine.contains("DirectShow audio")) {
			isVideoDevice = Boolean.FALSE;
		}
		else {
			readLine = readLine.substring(readLine.indexOf("]")+1).trim();
			if(readLine.startsWith("\"")) {
				if(isVideoDevice) _deviceSupported.addVideoDevice(readLine);
				else _deviceSupported.addAudioDevice(readLine);
			}
		}
	}

	@Override public void populateSupport() throws Exception {
		_ffmpeg.runOnYourOwnParams("-f dshow -list_devices 1 -i dummy").printCmd().execute(this);
	}

	@Override public String getName() {
		return "DeviceSuported";
	}

	public FFMPEGDevices getDeviceSupported() {
		return _deviceSupported;
	}
}

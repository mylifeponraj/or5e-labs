package org.or5e.ffmpeg.core.init;

import org.or5e.ffmpeg.core.ds.FFMPEGProtocols;

public class ProtocolSupported extends FFMPEGSupport<FFMPEGProtocols> {
	private FFMPEGProtocols protocolSupportedFor;
	public Boolean isInput = Boolean.FALSE;
	public ProtocolSupported() {
		super(new FFMPEGProtocols());
		this.protocolSupportedFor = getFfmpegSupportFor();
	}

	@Override
	public void outputReceived(String readLine, Integer lineNumber) {
		if (lineNumber == 1)
			return;
		if (readLine.equals("Input:")) {
			isInput = Boolean.TRUE;
		} else if (readLine.equals("Output:")) {
			isInput = Boolean.FALSE;
		} else if (isInput) {
			protocolSupportedFor.addInputProtocol(readLine.trim());
		} else {
			protocolSupportedFor.addOutputProtocol(readLine.trim());
		}
	}

	@Override
	public void populateSupport() throws Exception {
		_ffmpeg.listSupport("-protocols").printCmd().execute(this);
	}

	@Override
	public String getName() {
		return "ProtocolSupported";
	}

	public FFMPEGProtocols getProtocolSupportedFor() {
		return protocolSupportedFor;
	}
}

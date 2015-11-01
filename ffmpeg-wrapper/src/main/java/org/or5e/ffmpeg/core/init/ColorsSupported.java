package org.or5e.ffmpeg.core.init;

import java.util.StringTokenizer;

import org.or5e.ffmpeg.core.ds.FFMPEGColors;

public class ColorsSupported extends FFMPEGSupport<FFMPEGColors> {
	public FFMPEGColors _colorsSupported;

	public ColorsSupported() {
		super(new FFMPEGColors());
		_colorsSupported = getFfmpegSupportFor();
	}
	
	@Override public void outputReceived(String readLine, Integer lineNumber) {
		if(lineNumber > 1) {
			StringTokenizer tokens = new StringTokenizer(readLine, " ");
			_colorsSupported.addColor(tokens.nextToken(), tokens.nextToken().trim());
		}
	}

	@Override public void populateSupport() throws Exception {
		_ffmpeg.runOnYourOwnParams("-colors").printCmd().execute(this);
	}

	@Override public String getName() {
		return "ColorsSupported";
	}

	public FFMPEGColors getColorsSupported() {
		return _colorsSupported;
	}
}
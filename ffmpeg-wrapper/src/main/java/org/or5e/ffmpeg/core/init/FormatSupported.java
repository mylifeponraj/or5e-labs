package org.or5e.ffmpeg.core.init;

import java.io.IOException;
import java.util.StringTokenizer;

import org.or5e.ffmpeg.core.ds.FFMPEGFormats;

public class FormatSupported extends FFMPEGSupport<FFMPEGFormats> {
	public FFMPEGFormats _formatSupportedFor;

	public FormatSupported() {
		super(new FFMPEGFormats());
		_formatSupportedFor = getFfmpegSupportFor();
	}

	@Override
	public void outputReceived(String readLine, Integer lineNumber) {
		if (lineNumber > 4) {
			StringTokenizer recordTokens = new StringTokenizer(readLine.trim(), " ");
			while (recordTokens.hasMoreTokens()) {
				String type = recordTokens.nextToken();
				String key = recordTokens.nextToken();
				String value = recordTokens.nextToken("]").trim();
				switch (type) {
				case "DE":
					_formatSupportedFor.addDeMuxingFormatSupported(key, value);
					_formatSupportedFor.addMuxingFormatSupported(key, value);
					break;
				case "D":
					_formatSupportedFor.addDeMuxingFormatSupported(key, value);
					break;
				case "E":
					_formatSupportedFor.addMuxingFormatSupported(key, value);
				}
			}
		}
	}

	@Override
	public void populateSupport() throws IOException {
		_ffmpeg.runOnYourOwnParams("-formats").printCmd().execute(this);
	}

	public FFMPEGFormats getFormatSupportedFor() {
		return _formatSupportedFor;
	}

	@Override
	public String getName() {
		return "FormatSupported";
	}
}

package org.or5e.ffmpeg.core.init;

import org.or5e.core.BaseObject;
import org.or5e.ffmpeg.core.CallbackHandler;
import org.or5e.ffmpeg.core.FFMPEG;

public abstract class FFMPEGSupport<T> extends BaseObject implements CallbackHandler {
	public T ffmpegSupportFor;
	protected FFMPEG _ffmpeg = null;
	private String ffmpegCmd = getProperties("ffmpeg");

	public FFMPEGSupport(T ffmpegSupportFor) {
		this.ffmpegSupportFor = ffmpegSupportFor;
		this._ffmpeg = new FFMPEG(this.ffmpegCmd);
	}

	public abstract void populateSupport() throws Exception;

	public T getFfmpegSupportFor() {
		return ffmpegSupportFor;
	}
}
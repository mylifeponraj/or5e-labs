package org.or5e.ffmpeg.core.init;

import java.util.LinkedList;
import java.util.List;

import org.or5e.core.BaseObject;

@SuppressWarnings("rawtypes")
public class FFMPEGLoader extends BaseObject{
	private List<FFMPEGSupport> _listOfSupportToExecute = null;
	public FFMPEGLoader() {
		this._listOfSupportToExecute = new LinkedList<>();
	}
	public void add(FFMPEGSupport support) {
		this._listOfSupportToExecute.add(support);
	}

	public void loadFFMPEG() {
		for (FFMPEGSupport ffmpegSupport : _listOfSupportToExecute) {
			try {
				ffmpegSupport.populateSupport();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		FFMPEGLoader _loader = new FFMPEGLoader();
		_loader.add(new ProtocolSupported());
		_loader.add(new FormatSupported());
		_loader.loadFFMPEG();
	}
	@Override public String getName() {
		return "FFMPEGLoader";
	}
}
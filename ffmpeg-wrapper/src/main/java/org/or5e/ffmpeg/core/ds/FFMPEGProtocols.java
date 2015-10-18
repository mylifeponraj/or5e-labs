package org.or5e.ffmpeg.core.ds;

import java.util.LinkedList;
import java.util.List;

public class FFMPEGProtocols {
	private List<String> supportedInputProtocols = null;
	private List<String> supportedOutputProtocols = null;
	public FFMPEGProtocols() {
		supportedInputProtocols = new LinkedList<>();
		supportedOutputProtocols = new LinkedList<>();
	}
	public void clearLists() {
		this.supportedInputProtocols.clear();
		this.supportedOutputProtocols.clear();
	}
	public void addInputProtocol(String protocol) {
		this.supportedInputProtocols.add(protocol);
	}
	public void addOutputProtocol(String protocol) {
		this.supportedOutputProtocols.add(protocol);
	}
	public List<String> getSupportedInputProtocols() {
		return supportedInputProtocols;
	}
	public List<String> getSupportedOutputProtocols() {
		return supportedOutputProtocols;
	}
}
package org.or5e.ffmpeg.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.or5e.core.BaseObject;

public class FFMPEG extends BaseObject {
	private final String ffmpegCmd;
	private List<String> ffmpegParams = null;
	public FFMPEG(String ffmpeg) {
		this.ffmpegCmd = ffmpeg;
		clearAllParams();
		initilizeDefaults();
	}
	public FFMPEG listSupport(String support) {
		ffmpegParams.add(support);
		return this;
	}
	public FFMPEG addWindowsFind(String searchFor) {
		ffmpegParams.add(" 2>&1 | Find /I \""+searchFor+"\"");
		return this;
	}
	public void initilizeDefaults() {
		ffmpegParams.add("-hide_banner");
		ffmpegParams.add("-y");
	}
	public FFMPEG clearAllParams() {
		this.ffmpegParams = new LinkedList<String>();
		ffmpegParams.add(this.ffmpegCmd);
		return this;
	}

	public FFMPEG addSource(String sourceFile) {
		this.ffmpegParams.add("-i");
		this.ffmpegParams.add(sourceFile);
		return this;
	}
	public FFMPEG addH264QSV() {
		this.ffmpegParams.add("-c:v");
		this.ffmpegParams.add("h264_qsv");
		return this;
	}
	public FFMPEG addVideoBitRate(String bitRate) {
		this.ffmpegParams.add("-b:v");
		this.ffmpegParams.add(bitRate);
		this.ffmpegParams.add("-bufsize");
		this.ffmpegParams.add(bitRate);
		return this;
	}
	public FFMPEG addAudioBitRate(String bitRate) {
		this.ffmpegParams.add("-b:a");
		this.ffmpegParams.add(bitRate);
		return this;
	}
	public FFMPEG addDestination(String distFile) {
		this.ffmpegParams.add(distFile);
		return this;
	}
	public FFMPEG printCmd() {
		String[] params = ffmpegParams.toArray(new String[ffmpegParams.size()]);
		for (String value : params) {
			System.out.print(value+" ");
		}
		System.out.println();
		return this;
	}
	public String whichFFMPEG() {
		return ffmpegCmd;
	}
	public void execute() throws IOException {
		String[] params = ffmpegParams.toArray(new String[ffmpegParams.size()]);
		ProcessBuilder _builder = new ProcessBuilder(params);
		_builder.redirectErrorStream(Boolean.TRUE);
		Process _process = _builder.start();
		BufferedReader _reader = new BufferedReader(new InputStreamReader(_process.getInputStream()));
		String readLine = null;
		while((readLine=_reader.readLine()) != null) {
			System.out.println(readLine);
		}
		_reader.close();
		System.out.println("Executed Successfully...");
	}
	public void execute(CallbackHandler _handler) throws IOException {
		String[] params = ffmpegParams.toArray(new String[ffmpegParams.size()]);
		ProcessBuilder _builder = new ProcessBuilder(params);
		_builder.redirectErrorStream(Boolean.TRUE);
		Process _process = _builder.start();
		BufferedReader _reader = new BufferedReader(new InputStreamReader(_process.getInputStream()));
		String readLine = null;
		int lineNumber = 1;
		while((readLine=_reader.readLine()) != null) {
			_handler.outputReceived(readLine, lineNumber++);
		}
		_reader.close();
		System.out.println("Executed Successfully...");
	}
	@Override public String getName() {
		return "FFMPEG";
	}
	public static void main(String[] args)  throws IOException{
		FFMPEG _ffmpeg = new FFMPEG("D:\\ffmpeg\\bin\\ffmpeg.exe");
		_ffmpeg.execute();
		System.out.println("----------------------------------------");
		System.out.println("ffmpeg is : "+_ffmpeg.whichFFMPEG());
		System.out.println("Command is : ");
		_ffmpeg.printCmd();
		System.out.println("Main Exitting...");
	}
}

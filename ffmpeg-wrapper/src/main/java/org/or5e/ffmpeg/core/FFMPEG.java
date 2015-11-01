package org.or5e.ffmpeg.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.or5e.core.BaseObject;

public class FFMPEG extends BaseObject implements FFMPEGConstants {
	private final String ffmpegCmd;
	private List<String> ffmpegParams = null;
	public FFMPEG(String ffmpeg, Boolean loadDefaults) {
		this.ffmpegCmd = ffmpeg;
		clearAllParams();
		if(loadDefaults) initilizeDefaults();
	}
	public FFMPEG runOnYourOwnParams(String support) {
		StringTokenizer tokens = new StringTokenizer(support, " ");
		while(tokens.hasMoreTokens()) {
			ffmpegParams.add(tokens.nextToken());
		}
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
	public FFMPEG addDestination(String distFile) {
		this.ffmpegParams.add(distFile);
		return this;
	}
	public FFMPEG addIntelQuickSyncHW() {
		this.ffmpegParams.add("-c:v");
		this.ffmpegParams.add("h264_qsv");
		return this;
	}
	public FFMPEG addCopyVideoBitrate() {
		this.ffmpegParams.add("-c:v");
		this.ffmpegParams.add("copy");
		return this;
	}
	public FFMPEG addCopyAudioBitrate() {
		this.ffmpegParams.add("-c:a");
		this.ffmpegParams.add("copy");
		return this;
	}
	public FFMPEG addRemoveAudio() {
		this.ffmpegParams.add("-an");
		return this;
	}
	public FFMPEG addRemoveVideo() {
		this.ffmpegParams.add("-vn");
		return this;
	}
	public FFMPEG addPixelFormat(String format) {
		this.ffmpegParams.add("-pix_fmt");
		this.ffmpegParams.add(format);
		return this;
	}
	public FFMPEG addASYNC() {
		this.ffmpegParams.add("-async");
		this.ffmpegParams.add("1");
		return this;
	}
	public FFMPEG addAudioCodec(String codec) {
		this.ffmpegParams.add("-c:a");
		this.ffmpegParams.add(codec);
		return this;
	}
	public FFMPEG addVideoCodec(String codec) {
		this.ffmpegParams.add("-c:v");
		this.ffmpegParams.add(codec);
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
	public FFMPEG addSilentAudio() {
		this.ffmpegParams.add("-f");
		this.ffmpegParams.add("lavfi");
		this.ffmpegParams.add("-i");
		this.ffmpegParams.add("anullsrc");
		return this;
	}
	public FFMPEG addResizingVideo(String scaleWidth, String scaleHeight) {
		this.ffmpegParams.add("-vf");
		this.ffmpegParams.add("scale=iw*"+scaleWidth+":ih*"+scaleHeight);
		return this;
	}
	public FFMPEG addMultiUHDDestination(String destination) {
		this.ffmpegParams.add("-s");
		this.ffmpegParams.add("1920X1080");
		this.ffmpegParams.add("-v:c");
		this.ffmpegParams.add("h264_qsv");
		this.ffmpegParams.add(destination);
		
		return this;
	}
	public FFMPEG addMultiHDDestination(String destination) {
		this.ffmpegParams.add("-s");
		this.ffmpegParams.add("1280X720");
		this.ffmpegParams.add("-v:c");
		this.ffmpegParams.add("h264_qsv");
		this.ffmpegParams.add(destination);
		
		return this;
	}
	public FFMPEG addMultiSDDestination(String destination) {
		this.ffmpegParams.add("-s");
		this.ffmpegParams.add("640x480");
		this.ffmpegParams.add("-v:c");
		this.ffmpegParams.add("h264_qsv");
		this.ffmpegParams.add(destination);

		return this;
	}
	public FFMPEG addMultiVGADestination(String destination) {
		this.ffmpegParams.add("-s");
		this.ffmpegParams.add("320x240");
		this.ffmpegParams.add("-v:c");
		this.ffmpegParams.add("h264_qsv");
		this.ffmpegParams.add(destination);

		return this;
	}
	public FFMPEG addSplitVideo(Integer videoDuration, Short noOfPieces) {
		int splitSegment = Math.round((videoDuration*60)/noOfPieces);
		this.ffmpegParams.add("-f");
		this.ffmpegParams.add("segment");
		this.ffmpegParams.add("-segment_times");
		StringBuilder _builder = new StringBuilder();
		int splitDuration = splitSegment;
		while(splitDuration < videoDuration) {
			_builder.append(splitDuration);
			_builder.append(",");
			splitDuration+=splitSegment;
		}
		this.ffmpegParams.add(_builder.toString().substring(0, _builder.length()-1));
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
		FFMPEG _ffmpeg = new FFMPEG("D:\\ffmpeg\\bin\\ffmpeg.exe", Boolean.TRUE);
		_ffmpeg.execute();
		System.out.println("----------------------------------------");
		System.out.println("ffmpeg is : "+_ffmpeg.whichFFMPEG());
		System.out.println("Command is : ");
		_ffmpeg.printCmd();
		System.out.println("Main Exitting...");
	}
}

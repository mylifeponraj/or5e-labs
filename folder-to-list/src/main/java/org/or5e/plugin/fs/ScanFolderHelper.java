package org.or5e.plugin.fs;

import java.io.File;

import org.or5e.core.BaseObject;
import org.or5e.plugin.fs.filters.AudioFileNameFilter;
import org.or5e.plugin.fs.filters.ImageFileNameFilter;
import org.or5e.plugin.fs.filters.VideoFileNameFilter;

public class ScanFolderHelper extends BaseObject {
	public static void scanVideoAndCreatePlaylist(File videoFolder) {
		File[] listOfFilesAndFolder = videoFolder.listFiles(new VideoFileNameFilter());
		for (File fileOrFolder : listOfFilesAndFolder) {
			if (fileOrFolder.isDirectory()) {
				scanVideoAndCreatePlaylist(fileOrFolder);
			} else {
				System.out.println(fileOrFolder.getAbsolutePath());
				getVideoInfo(fileOrFolder);
			}
		}
	}

	@Override public String getName() {
		return "ScanFolderHelper";
	}

	public static void scanAudioAndCreatePlaylist(File audioFolder) {
		File[] listOfFilesAndFolder = audioFolder.listFiles(new AudioFileNameFilter());
		System.out.println("List Size..."+listOfFilesAndFolder.length);
		for (File fileOrFolder : listOfFilesAndFolder) {
			if (fileOrFolder.isDirectory()) {
				scanAudioAndCreatePlaylist(fileOrFolder);
			} else {
				System.out.println(fileOrFolder.getAbsolutePath());
				getAudioInfo(fileOrFolder);
			}
		}		
	}

	public static void scanImageAndCreatePlaylist(File imageFolder) {
		File[] listOfFilesAndFolder = imageFolder.listFiles(new ImageFileNameFilter());
		System.out.println("List Size..."+listOfFilesAndFolder.length);
		for (File fileOrFolder : listOfFilesAndFolder) {
			if (fileOrFolder.isDirectory()) {
				scanImageAndCreatePlaylist(fileOrFolder);
			} else {
				System.out.println(fileOrFolder.getAbsolutePath());
//				getImageInfo(fileOrFolder);
			}
		}		
	}

	private static VideoInfo getVideoInfo(File videoFile) {
		VideoInfo vInfo = new VideoInfo();

		return vInfo;
	}
	private static AudioInfo getAudioInfo(File audioFile) {
		AudioInfo aInfo = new AudioInfo();

		return aInfo;
	}
}
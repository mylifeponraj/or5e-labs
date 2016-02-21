package org.or5e.plugin.fs;

import java.io.File;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;

public class ScanFolderServiceImpl extends PluginLifecycleAdaptor implements ScanFolderService {
	private static Boolean _isServiceRunning = Boolean.FALSE;

	@Override
	public void initilize() throws PluginException {
		info("Plugin is initilizing....");
	}

	@Override
	public void destroy() {
		info("Plugin is Shutting down....");
	}

	@Override
	public void scanForVideo() {
		if (_isServiceRunning)
			throw new PluginException(
					"Service is already Running... Please try after sometime.");
		_isServiceRunning = Boolean.TRUE;
		debug("Running Scan for Video is started...");
		ScanFolderHelper.scanVideoAndCreatePlaylist(new File(
				getProperties("video-folder-name")));
		_isServiceRunning = Boolean.FALSE;
	}

	@Override public void scanForMusic() {
		System.out.println("Scanning for Music.");
		if (_isServiceRunning)
			throw new PluginException("Service is already Running... Please try after sometime.");
		_isServiceRunning = Boolean.TRUE;
		ScanFolderHelper.scanAudioAndCreatePlaylist(new File(getProperties("audio-folder-name")));
		_isServiceRunning = Boolean.FALSE;
	}

	@Override
	public void scanForImage() {
		System.out.println("Scaning image folder.");
		if (_isServiceRunning) {
			System.out.println("Process already Running...");
			throw new PluginException(
					"Service is already Running... Please try after sometime.");
		}
		_isServiceRunning = Boolean.TRUE;
		ScanFolderHelper.scanImageAndCreatePlaylist(new File(
				getProperties("image-folder-name")));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		_isServiceRunning = Boolean.FALSE;
	}

	@Override
	public String getName() {
		return "ScanFolderServiceImpl";
	}

	@Override
	public String getPluginID() {
		return getName();
	}

	@Override
	public void doProcess() {
		scanForMusic();
	}

	public static void main(String[] args) {
		Plugin plugin = new ScanFolderServiceImpl();
		plugin.startPlugin();
		System.out.println("Done...");
	}
}

package org.or5e.mp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.PluginLifecycleAdaptor;
import org.or5e.core.plugin.intent.ConsumeIntent;
import org.or5e.core.plugin.intent.Intent;
import org.or5e.core.plugin.intent.IntentQueue;
import org.or5e.core.plugin.intent.IntentQueueSPI;
import org.or5e.mp.xtrememp.playlist.jspf.JSONPlaylist;
import org.or5e.mp.xtrememp.playlist.jspf.JSONPlaylistManager;

import uk.co.caprica.vlcj.player.Equalizer;

public abstract class MediaPlaylerSPI extends PluginLifecycleAdaptor implements MediaPlayer{
	protected Map<String, Equalizer> equalizersAvailable = null;
	public Set<String> _allAvailablePlaylist = null;
	public JSONPlaylistManager _playlistManager = null;
	public JSONPlaylist _currentPlaylist = null;
	protected int defaultVolume = 50;
	protected String defaultEQSettings = "Rock";
	public Boolean isPlayingPlaylist = Boolean.FALSE;
	public Boolean isMuted = Boolean.FALSE;

	@Override public void initilize() throws PluginException {
		this._playlistManager = new JSONPlaylistManager();
		debug("Loading all the playlist...");
		this._playlistManager.loadAllPlaylist();
		this._allAvailablePlaylist = this._playlistManager.getAllPlaylistNames();
		debug("Getting all the playlist...");
		this._currentPlaylist = this._playlistManager.getCurrentPlaylist();
	}
	public void initilizeEvents() {
		IntentQueue intentQueue = IntentQueueSPI.getIntentQueue();
		List<String> listToIntent = new LinkedList<>();
		listToIntent.add("play");
		listToIntent.add("pause");
		listToIntent.add("stop");
		listToIntent.add("previous");
		listToIntent.add("next");

		intentQueue.listenToIntent(new ConsumeIntent() {
			@Override public void consumeIntent(Intent intent) {
				handleEvent(intent);
			}
		}, listToIntent.toArray(new String[listToIntent.size()]));
	}
	public abstract void callNextAudio(Boolean nextOrPrev);
	public abstract void handleEvent(Intent intent);

//	public class CallNextAudioTrack implements Runnable {
//		@Override public void run() {
//			callNextAudio(Boolean.TRUE);
//		}
//	}
}

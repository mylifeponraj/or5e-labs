package org.or5e.mp.vlc;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.or5e.core.PluginException;
import org.or5e.mp.MediaPlaylerSPI;
import org.or5e.mp.RepeatMode;
import org.or5e.mp.xtrememp.playlist.jspf.Track;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

public class MediaPlayerVLCSPI extends MediaPlaylerSPI{
	private boolean isDestroyed = Boolean.FALSE;
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), getProperties("vlcNative"));
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();
    }

	public MediaPlayerVLCSPI() {
	}

	@Override public String getPluginID() {
		return getName();
	}

	@Override public void initilize() throws PluginException {
		super.initilize();
		initilizePlayer();
	}

	
	@Override public void doProcess() {
		super.doProcess();
	}
	
	@Override public void destroy() {
		if(isDestroyed) {
			debug("Plugin "+getName()+" is already destroyed.");
			return;
		}
		isDestroyed = Boolean.TRUE;
		info("Destroying the VLC Media Player SPI");
		super.destroy();
		if(this.vlcMediaPlayer.isPlaying()) {
			info("Stopping the current audio played");
			this.vlcMediaPlayer.stop();
		}
		info("Releasing the resources for VLC");
		this.vlcMediaPlayer.release();
		this.vlcMediaFactory.release();
	}

	@Override
	public void play() {
		if(this.isAudioPlaying) {
			this.vlcMediaPlayer.play();
		}
	}

	@Override
	public void play(String fileName) {
		this.isPlayingPlaylist = Boolean.FALSE;
		this.stop();
		this.vlcMediaPlayer.playMedia(fileName);
		this.isAudioPlaying = Boolean.TRUE;
	}

	@Override
	public void playCurrentPlaylist() {
		debug("Starting playing current Playlist....");
		this._currentPlaylist = this._playlistManager.getCurrentPlaylist();
		_playlistManager.selectPlaylist(this._currentPlaylist.getTitle());
		this.isPlayingPlaylist = Boolean.TRUE;
		Track currentTrack = this._currentPlaylist.getFirstTrack();
		debug("Selecting track...."+currentTrack.location);
		this.vlcMediaPlayer.playMedia(currentTrack.location);
	}

	@Override
	public void play(URI fileURL) {
		this.isPlayingPlaylist = Boolean.FALSE;
		this.stop();
		this.vlcMediaPlayer.playMedia(fileURL.toString());
		this.isAudioPlaying = Boolean.TRUE;
	}

	@Override
	public void pause() {
		if(this.isAudioPlaying) {
			this.vlcMediaPlayer.pause();
		}
	}

	@Override
	public void stop() {
		if(isAudioPlaying) {
			this.vlcMediaPlayer.stop();
		}
		this.isAudioPlaying = Boolean.FALSE;
	}

	@Override
	public void next() {
		if(isAudioPlaying) {
			this.vlcMediaPlayer.stop();
		}
		callNextAudio(Boolean.TRUE);
	}

	@Override
	public void previous() {
		if(isAudioPlaying) {
			this.vlcMediaPlayer.stop();
		}
		callNextAudio(Boolean.FALSE);
	}

	@Override
	public Boolean isShuffled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isRepeated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShuffle(Boolean doShaffle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRepeated(RepeatMode mode, Boolean doRepeat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getAllPlaylistNames() {
		return _playlistManager.getAllPlaylistNames();
	}

	@Override
	public void selectPlaylist(String playlistName) {
		if(isAudioPlaying) {
			vlcMediaPlayer.stop();
			isAudioPlaying = Boolean.FALSE;
		}
		_playlistManager.selectPlaylist(playlistName);
		this._currentPlaylist = this._playlistManager.getCurrentPlaylist();
		this.isPlayingPlaylist = Boolean.TRUE;
	}

	@Override
	public List<String> getAllSongsInPlaylist(String playlist) {
		return null;
	}

	@Override
	public void addPlaylist(String playlist) {
		
	}

	@Override
	public Set<String> getAllEQPreset() {
		return equalizersAvailable.keySet();
	}

	@Override
	public void setEqualizer(String eqName) {
		this.vlcMediaPlayer.setEqualizer(this.equalizersAvailable.get(defaultEQSettings));
	}

	@Override
	public void initilizePlayer() {
		this.audioPlayer = new VLCMediaPlayerEventComponent(this);
        info("Initilizing the VLC Media Player SPI.");
        this.vlcMediaFactory = audioPlayer.getMediaPlayerFactory();
        this.vlcMediaPlayer = audioPlayer.getMediaPlayer();
        super.equalizersAvailable = vlcMediaFactory.getAllPresetEqualizers();

        info("Setting VLC Media Player with default Volume and Equalizer preset.");
        this.vlcMediaPlayer.setVolume(defaultVolume);
        this.vlcMediaPlayer.setEqualizer(this.equalizersAvailable.get(defaultEQSettings));		
	}

	@Override
	public String getName() {
		return "MediaPlayerVLCSPI";
	}

	public void isMuted(boolean muted) {
		isMuted = Boolean.valueOf(muted);		
	}
	
	protected uk.co.caprica.vlcj.player.MediaPlayer vlcMediaPlayer = null;
	protected MediaPlayerFactory vlcMediaFactory = null;
	protected AudioMediaPlayerComponent audioPlayer = null;
	protected Boolean isAudioPlaying = Boolean.FALSE;
	public static void main(String[] args) {
		MediaPlayerVLCSPI plugin = new MediaPlayerVLCSPI();
		//plugin.initilize();
		plugin.startPlugin();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		plugin.destroy();
//		plugin.playCurrentPlaylist();
//		while(true) {
//			try {
//				System.in.read();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			plugin.next();
//			
//		}
	}

	@Override public void callNextAudio(Boolean nextOrPrev) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		debug("About to run next audio...");
		Track playTrack = (nextOrPrev) ? this._currentPlaylist.getNextTrack() : this._currentPlaylist.getPreviousTrack();
		debug("Running audio..." + playTrack.location);
		this.isAudioPlaying = Boolean.TRUE;
		this.vlcMediaPlayer.playMedia(playTrack.location);
	}
}

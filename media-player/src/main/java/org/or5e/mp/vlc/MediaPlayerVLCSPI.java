package org.or5e.mp.vlc;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.PluginLifecycleAdaptor;
import org.or5e.mp.MediaPlayer;
import org.or5e.mp.RepeatMode;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.Equalizer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

public class MediaPlayerVLCSPI extends PluginLifecycleAdaptor implements MediaPlayer{
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), getProperties("vlcNative"));
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();
    }

	public MediaPlayerVLCSPI() {
		this._processor = new VLCMediaPlayerHelper();
	}

	@Override public String getPluginID() {
		return getName();
	}

	@Override public void initilize() throws PluginException {
		initilizePlayer();
	}

	
	@Override public void doProcess() {
		super.doProcess();
	}
	
	@Override public void destroy() { 
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(URI fileURL) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previous() {
		// TODO Auto-generated method stub
		
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
	public List<String> getAllPlaylistNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSongsInPlaylist(String playlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlaylist(String playlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getAllEQPreset() {
		return equalizersAvailable.keySet();
	}

	@Override
	public void setEqualizer(String eqName) {
		//Do Noting
	}

	@Override
	public void initilizePlayer() {
		AudioMediaPlayerComponent audioPlayer = new AudioMediaPlayerComponent() {
            @Override
            public void volumeChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, float volume) {
            	super.volumeChanged(mediaPlayer, volume);
            	info("Volume is changed to :"+volume);
            }
            @Override
            public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
            	super.finished(mediaPlayer);
            	isAudioPlaying = Boolean.FALSE;
            }
            @Override
            public void error(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
            	super.error(mediaPlayer);
            	debug("Something is wrong in the VLC media player...");
            	isAudioPlaying = Boolean.FALSE;
            }
        };
        info("Initilizing the VLC Media Player SPI.");
        this.vlcMediaFactory = audioPlayer.getMediaPlayerFactory();
        this.vlcMediaPlayer = audioPlayer.getMediaPlayer();
        this.equalizersAvailable = vlcMediaFactory.getAllPresetEqualizers();

        info("Setting VLC Media Player with default Volume and Equalizer preset.");
        this.vlcMediaPlayer.setVolume(defaultVolume);
        this.vlcMediaPlayer.setEqualizer(this.equalizersAvailable.get(defaultEQSettings));		
	}

	@Override
	public String getName() {
		return "MediaPlayerVLCSPI";
	}

	
	private uk.co.caprica.vlcj.player.MediaPlayer vlcMediaPlayer = null;
	private MediaPlayerFactory vlcMediaFactory = null;
	private Map<String, Equalizer> equalizersAvailable = null;
	private int defaultVolume = 50;
	private String defaultEQSettings = "Rock";
	protected Boolean isAudioPlaying = Boolean.FALSE;
	private final VLCMediaPlayerHelper _processor;
}

package org.or5e.mp.xtrem;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Set;

import org.or5e.core.BaseObject;
import org.or5e.mp.MediaPlayer;
import org.or5e.mp.RepeatMode;
import org.or5e.mp.xtrememp.Settings;
import org.or5e.mp.xtrememp.player.audio.AudioPlayer;
import org.or5e.mp.xtrememp.player.audio.PlayerException;
import org.or5e.mp.xtrememp.playlist.Playlist;
import org.or5e.mp.xtrememp.playlist.PlaylistListener;
import org.or5e.mp.xtrememp.util.Utilities;

public class MediaPlayerXtremempSPI extends BaseObject implements MediaPlayer{
	private final AudioPlayer _audioPlayer;
	private final AudioPlaybackListener _playbackListener;
	private final Playlist _currentPlaylist;
	private final PlaylistListener _playlistListener;
	private static enum AudioPlayerStatus {INIT, PLAYING, PAUSE, STOP};
	public AudioPlayerStatus _status = null;

	public MediaPlayerXtremempSPI() {
		this._audioPlayer = new AudioPlayer();
		this._playbackListener = new AudioPlaybackListener();
		this._currentPlaylist = new Playlist();
		this._playlistListener = new AudioPlaylistListener();

		initilizePlayer();
	}
	@Override
	public void play() {
		if(this._status == AudioPlayerStatus.INIT || this._status == AudioPlayerStatus.PAUSE) {
			try {
				_audioPlayer.play();
				this._status = AudioPlayerStatus.PLAYING;
			} catch (PlayerException e) {
				e.printStackTrace();
			}
		}
		else if(this._status == AudioPlayerStatus.PAUSE){
			throw new RuntimeException("Audio Already Playing");
		}
			
	}

	@Override
	public void play(String fileName) {
		if(this._status == AudioPlayerStatus.PLAYING || this._status == AudioPlayerStatus.PAUSE) {
			this._audioPlayer.stop();
			this._status = AudioPlayerStatus.STOP;
		}
		try {
			this._audioPlayer.open(new File(fileName));
			this._audioPlayer.play();
			this._status = AudioPlayerStatus.PLAYING;
		} catch (PlayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void play(URI fileURL) {
		if(this._status == AudioPlayerStatus.PLAYING || this._status == AudioPlayerStatus.PAUSE) {
			this._audioPlayer.stop();
			this._status = AudioPlayerStatus.STOP;
		}
		try {
			this._audioPlayer.open(fileURL.toURL());
			this._audioPlayer.play();
			this._status = AudioPlayerStatus.PLAYING;
		} catch (PlayerException | MalformedURLException e) {
			e.printStackTrace();
		} 	
	}

	@Override
	public void pause() {
		if(this._status == AudioPlayerStatus.PLAYING) {
			this._audioPlayer.pause();
			this._status = AudioPlayerStatus.PAUSE;
		}
	}

	@Override
	public void stop() {
		if(this._status == AudioPlayerStatus.PLAYING || this._status == AudioPlayerStatus.PAUSE) {
			this._audioPlayer.stop();
			this._status = AudioPlayerStatus.STOP;
		}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEqualizer(String eqName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initilizePlayer() {
        // Initialize audio engine
        
        _audioPlayer.addPlaybackListener(this._playbackListener);
        String mixerName = Settings.getMixerName();
        if (!Utilities.isNullOrEmpty(mixerName)) {
            _audioPlayer.setMixerName(mixerName);
        }
       	_status = AudioPlayerStatus.INIT;
	}
	@Override
	public String getName() {
		return "MediaPlayerXtremempSPI";
	}
	@Override
	public Set<String> getAllPlaylistNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void selectPlaylist(String playlistName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void playCurrentPlaylist() {
		// TODO Auto-generated method stub
		
	}

}

package org.or5e.mp;

import org.or5e.mp.vlc.MediaPlayerVLCSPI;
import org.or5e.mp.xtrem.MediaPlayerXtremempSPI;

public class MediaPlayerFactory {
	public final MediaPlayer _xtreamMediaPlayer;
	public final MediaPlayer _vlcMediaPlayer;
	private static MediaPlayerFactory _factory = new MediaPlayerFactory();

	private MediaPlayerFactory() {
		this._vlcMediaPlayer = new MediaPlayerVLCSPI();
		this._xtreamMediaPlayer = new MediaPlayerXtremempSPI();
	}
	public static MediaPlayerFactory getMediaPlayerFactory() {
		return _factory;
	}
	public MediaPlayer getMediaPlayer(MediaPlayerType type) {
		return (type == MediaPlayerType.VLC) ? _vlcMediaPlayer: _xtreamMediaPlayer;
	}
}

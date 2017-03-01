package org.or5e.mp.vlc;

import org.or5e.mp.xtrememp.playlist.jspf.Track;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;

public final class VLCMediaPlayerEventComponent extends AudioMediaPlayerComponent{
	private MediaPlayerVLCSPI mediaPlayerSPI = null;

	public VLCMediaPlayerEventComponent(MediaPlayerVLCSPI mediaPlayerSPI) {
		this.mediaPlayerSPI = mediaPlayerSPI;
	}
	@Override public void playing(MediaPlayer mediaPlayer) {
		super.playing(mediaPlayer);
		mediaPlayerSPI.isAudioPlaying = Boolean.TRUE;
	}
	@Override
	public void paused(MediaPlayer mediaPlayer) {
		super.paused(mediaPlayer);
	}
	@Override
	public void stopped(MediaPlayer mediaPlayer) {
		super.stopped(mediaPlayer);
		mediaPlayerSPI.isAudioPlaying = Boolean.FALSE;
		System.out.println("Stopped Audio...");
	}
	@Override
	public void finished(MediaPlayer mediaPlayer) {
		super.finished(mediaPlayer);
		System.out.println("Finished Audio...");
		Thread t = new Thread(new CallNextAudio());
		t.start();
	}
	
	@Override
	public void muted(MediaPlayer mediaPlayer, boolean muted) {
		super.muted(mediaPlayer, muted);
		mediaPlayerSPI.isMuted(muted);
	}
	@Override
	public void volumeChanged(MediaPlayer mediaPlayer, float volume) {
		super.volumeChanged(mediaPlayer, volume);
	}
	
	@Override
	public void error(MediaPlayer mediaPlayer) {
		super.error(mediaPlayer);
		System.out.println("Something is wrong in the VLC media player...");
		mediaPlayerSPI.isAudioPlaying = Boolean.FALSE;
	}
	public class CallNextAudio implements Runnable  {
		public void run() {
			System.out.println("About to run next audio...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Track playTrack = mediaPlayerSPI._currentPlaylist.getNextTrack();
			System.out.println("Running audio..." + playTrack.location);
			mediaPlayerSPI.isAudioPlaying = Boolean.TRUE;
			mediaPlayerSPI.vlcMediaPlayer.playMedia(playTrack.location);
		}
	}
}

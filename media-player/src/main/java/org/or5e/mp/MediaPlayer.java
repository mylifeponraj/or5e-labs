package org.or5e.mp;

import java.net.URI;
import java.util.List;

public interface MediaPlayer {
	public void initilizePlayer();
	public void play();
	public void play(String fileName);
	public void play(URI fileURL);
	public void pause();
	public void stop();
	public void next();
	public void previous();

	public Boolean isShuffled();
	public Boolean isRepeated();
	public void setShuffle(Boolean doShaffle);
	public void setRepeated(RepeatMode mode, Boolean doRepeat);

	public List<String> getAllPlaylistNames();
	public List<String> getAllSongsInPlaylist(String playlist);
	public void addPlaylist(String playlist);

	public List<String> getAllEQPreset();
	public void setEqualizer(String eqName);
}
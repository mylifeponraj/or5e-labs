package org.or5e.mp;

import java.net.URI;
import java.util.List;
import java.util.Set;

public interface MediaPlayer {
	public void initilizePlayer();
	public void play();
	public void playCurrentPlaylist();
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

	public Set<String> getAllPlaylistNames();
	public List<String> getAllSongsInPlaylist(String playlist);
	public void addPlaylist(String playlist);
	public void selectPlaylist(String playlistName);

	public Set<String> getAllEQPreset();
	public void setEqualizer(String eqName);
}
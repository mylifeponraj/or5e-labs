package org.or5e.itunes.vo;

import java.util.LinkedList;
import java.util.List;

public class Playlist {
	public Integer playlistID = null;
	public String playlistName = null;
	public List<Integer> listOfMusic = null;
	
	public Playlist(Integer playlistID, String playlistName) {
		this.playlistID = playlistID;
		this.playlistName = playlistName;
		this.listOfMusic = new LinkedList<Integer>();
	}
	public void addMusic(Integer musicID) {
		this.listOfMusic.add(musicID);
	}
	public Integer getPlaylistID() {
		return playlistID;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public List<Integer> getListOfMusic() {
		return listOfMusic;
	}
}

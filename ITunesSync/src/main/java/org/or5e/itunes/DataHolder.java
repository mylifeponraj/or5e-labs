package org.or5e.itunes;

import java.util.HashMap;
import java.util.Map;

import org.or5e.core.BaseObject;
import org.or5e.itunes.vo.Music;
import org.or5e.itunes.vo.Playlist;

public class DataHolder extends BaseObject{
	private Map<Integer, Music> availableMusic = new HashMap<>();
	private Map<Integer, Playlist> availablePlaylist = new HashMap<>();

	public void addMusic(org.or5e.itunes.jaxb.binding.Integer songID, Music song) {
		this.availableMusic.put(new Integer(songID.getvalue()), song);
	}
	public void addPlaylist(org.or5e.itunes.jaxb.binding.Integer playlistID, Playlist playlist) {
		this.availablePlaylist.put(new Integer(playlistID.getvalue()), playlist);
	}
	public void addPlaylist(Integer playlistID, Playlist playlist) {
		this.availablePlaylist.put(playlistID, playlist);
	}
	public Playlist getPlaylist(Integer playlistID) {
		return this.availablePlaylist.get(playlistID);
	}
	public Integer getTotalTrackAvailable() {
		return this.availableMusic.size();
	}
	public Map<Integer, Music> getAllTrackAvailable() {
		return this.availableMusic;
	}
	public Map<Integer, Playlist> getAllPlaylistAvailable() {
		return this.availablePlaylist;
	}
	public Music getTrackInfo(Integer trackID) {
		return this.availableMusic.get(trackID);
	}
	@Override
	public String getName() {
		return "DataHolder";
	}
	public Integer getTotalPlaylistAvailable() {
		return this.availablePlaylist.size();
	}
}

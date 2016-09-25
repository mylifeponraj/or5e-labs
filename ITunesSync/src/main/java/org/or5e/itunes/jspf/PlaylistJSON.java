package org.or5e.itunes.jspf;

import java.util.LinkedList;
import java.util.List;

public class PlaylistJSON {
	public String title;
	public List<Track> tracks = new LinkedList<Track>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	public void addTracks(Track track) {
		tracks.add(track);
	}
}

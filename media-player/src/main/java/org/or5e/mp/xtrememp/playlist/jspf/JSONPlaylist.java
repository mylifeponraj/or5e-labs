package org.or5e.mp.xtrememp.playlist.jspf;

import java.util.LinkedList;
import java.util.List;

public class JSONPlaylist {
	public String title;
	public List<Track> tracks = new LinkedList<Track>();
	private int currentTrack = 0;
	private int totalTrackSize = 0;
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
		totalTrackSize = tracks.size();
	}

	public Track getFirstTrack() {
		currentTrack = 0;
		return tracks.get(currentTrack);
	}
	public Track getNextTrack() {
		if(currentTrack != tracks.size()) currentTrack++;
		else currentTrack = 0;
		
		return tracks.get(currentTrack);
	}
	public Track getPreviousTrack() {
		if(currentTrack > 0) currentTrack --;
		
		return null;
	}
}

package org.or5e.itunes;

import java.util.ArrayList;
import java.util.List;

public class NullPlaylistOrTracks {
	protected static List<String> nullifyPlaylist = null;
	static {
		
		nullifyPlaylist = new ArrayList<>();
		nullifyPlaylist.add("Library");
		nullifyPlaylist.add("Music");
		nullifyPlaylist.add("Movies");
		nullifyPlaylist.add("TV Shows");
		nullifyPlaylist.add("Podcasts");
		nullifyPlaylist.add("Books");
		nullifyPlaylist.add("Genius");
		nullifyPlaylist.add("iTunes U");
		nullifyPlaylist.add("Purchased");
		nullifyPlaylist.add("90’s Music");
		nullifyPlaylist.add("Classical Music");
		nullifyPlaylist.add("Recently Added");
		nullifyPlaylist.add("Recently Played");
	}
	public static Boolean isToAddPlaylist(String playlistName) {
		return !nullifyPlaylist.contains(playlistName);
	}
	public static void main(String[] args) {
		System.out.println(NullPlaylistOrTracks.isToAddPlaylist("90’s Music"));
	}
}

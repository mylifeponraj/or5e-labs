package org.or5e.mp.xtrememp.playlist.jspf;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.or5e.core.BaseObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONPlaylistManager extends BaseObject {

	private Map<String, JSONPlaylist> allPlaylistAvailable = null;
	private JSONPlaylist _currentPlaylist = null;
	public JSONPlaylistManager() {
		allPlaylistAvailable = new HashMap<>();
	}
	public void loadAllPlaylist() {
		String playlistPath = getProperties("playlist-path");
		File jspfPlaylistFile = new File(playlistPath);
		File[] listOfFiles = jspfPlaylistFile.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith("jspf"));
			}
		});
		ObjectMapper mapper = new ObjectMapper();
		for (File jspfFile : listOfFiles) {
			try {
				JSONPlaylist playlist = mapper.readValue(jspfFile, JSONPlaylist.class);
				allPlaylistAvailable.put(playlist.getTitle(), playlist);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this._currentPlaylist = this.allPlaylistAvailable.get("My Likes");
		info("Total Playlist loaded: "+this.allPlaylistAvailable.size());
	}
	public Set<String> getAllPlaylistNames() {
		return this.allPlaylistAvailable.keySet();
	}
	public Boolean selectPlaylist(String playlistName) {
		this._currentPlaylist = this.allPlaylistAvailable.get(playlistName);
		return (this._currentPlaylist != null);
	}
	public JSONPlaylist getCurrentPlaylist() {
		return this._currentPlaylist;
	}
	@Override public String getName() {
		return "JSONPlaylistManager";
	}
	public static void main(String[] args) {
		new JSONPlaylistManager().loadAllPlaylist();
	}
}

package org.or5e.itunes.jspf;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.BaseObject;
import org.or5e.itunes.DataHolder;
import org.or5e.itunes.ItunesHelper;
import org.or5e.itunes.vo.Music;
import org.or5e.itunes.vo.Playlist;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSPFPlaylistManager extends BaseObject {
	public void writeAllPlaylistToJSPF() {
		DataHolder _handler = ItunesHelper.getItunesHelper().getDataHolder();
		
		Map<Integer, Playlist> allPlaylistAvailable = _handler.getAllPlaylistAvailable();
		Set<Integer> playlistIDSet = allPlaylistAvailable.keySet();
		for (Integer playlistID : playlistIDSet) {
			Playlist playlist = allPlaylistAvailable.get(playlistID);
			loadPlaylistToJSPF(playlist, _handler);
		}
	}

	private void loadPlaylistToJSPF(Playlist playlist, DataHolder _handler) {
		PlaylistJSON playlistJson = new PlaylistJSON();
		playlistJson.setTitle(playlist.getPlaylistName());
		List<Integer> listOfMusic = playlist.getListOfMusic();
		for (Integer trackID : listOfMusic) {
			Track track = new Track();
			Music music = _handler.getTrackInfo(trackID);
			track.setTitle(music.getTrackName());
			track.setLocation(music.getTrackLocation());
			playlistJson.addTracks(track);
		}
		String playlistPath = getProperties("playlist-path")+playlist.getPlaylistName()+".jspf";
		File jspfPlaylistFile = new File(playlistPath);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(jspfPlaylistFile, playlistJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "JSPFPlaylistManager";
	}
}

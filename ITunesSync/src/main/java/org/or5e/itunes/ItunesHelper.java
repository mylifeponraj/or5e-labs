package org.or5e.itunes;

import java.util.Iterator;
import java.util.List;

import org.or5e.core.BaseObject;
import org.or5e.itunes.jaxb.binding.Array;
import org.or5e.itunes.jaxb.binding.Dict;
import org.or5e.itunes.jaxb.binding.Key;
import org.or5e.itunes.vo.Music;
import org.or5e.itunes.vo.Playlist;

public class ItunesHelper extends BaseObject{
	private final DataHolder _dataHolder;
	private final static ItunesHelper _helper;
	static {
		_helper = new ItunesHelper();
	}
	private ItunesHelper() {_dataHolder = new DataHolder();}
	public static ItunesHelper getItunesHelper() {
		return _helper;
	}
	public void readAllTracks(Dict trackDict) {
		debug("Reading Tracks: "+trackDict);
		List<Object> audioDictionary = trackDict.getDataList();
		for (Object audioOrKey : audioDictionary) {
			if(audioOrKey instanceof Dict) {
				Music music = new Music();
				Dict audioTrack = (Dict) audioOrKey;
				List<Object> audioDataList = audioTrack.getDataList();
				Iterator<Object> trackDetailsIterator = audioDataList.iterator();
				while(trackDetailsIterator.hasNext()) {
					Object trackData = trackDetailsIterator.next();
					if(trackData instanceof Key) {
						String keyValue = ((Key) trackData).getvalue();
						switch(keyValue) {
						case "Track ID":
							org.or5e.itunes.jaxb.binding.Integer trackID = (org.or5e.itunes.jaxb.binding.Integer)trackDetailsIterator.next();
							_dataHolder.addMusic(trackID, music);
							music.addFields("trackID", trackID);
							break;
						case "Name":
							music.addFields("trackName", (org.or5e.itunes.jaxb.binding.String)trackDetailsIterator.next());
							break;
						case "Location":
							org.or5e.itunes.jaxb.binding.String songLocation = (org.or5e.itunes.jaxb.binding.String)trackDetailsIterator.next();
							music.addFields("trackLocation", songLocation);
							info("Adding Song: "+songLocation.getvalue());
						case "Size":
							org.or5e.itunes.jaxb.binding.String songDuration = (org.or5e.itunes.jaxb.binding.String)trackDetailsIterator.next();
							music.addFields("trackDuration", songDuration);
						}
					}
				}
			}
		}
	}
	
	public void readAllAlbum(Array playlistArray) {
		debug("Reading playlist: "+playlistArray);
		List<Object> allPlaylistDictionary = playlistArray.getAllPlaylistsDictionary();
		for (Object playlists : allPlaylistDictionary) {
			Dict playlist = (Dict) playlists;
			List<Object> playlistMetadata = playlist.getDataList();
			Iterator<Object> playlistMetadataIterator = playlistMetadata.iterator();
			Integer playlistID = null;
			Playlist playlistToBeAdded = null;
			while(playlistMetadataIterator.hasNext()) {
				Object metadata = playlistMetadataIterator.next();
				if(metadata instanceof Key) {
					String value = ((Key) metadata).getvalue();
					switch(value){
					case "Playlist ID":
						playlistID = Integer.getInteger(((org.or5e.itunes.jaxb.binding.Integer)playlistMetadataIterator.next()).getvalue());
						break;
					case "Name":
						String nameMetadata = ((org.or5e.itunes.jaxb.binding.String)playlistMetadataIterator.next()).getvalue();
						if(NullPlaylistOrTracks.isToAddPlaylist(nameMetadata)) {
							playlistToBeAdded = new Playlist(playlistID, nameMetadata);
							info("Creating Playlist:"+nameMetadata);
							_dataHolder.addPlaylist(playlistID, playlistToBeAdded);
						}
						else {
							break;
						}
						break;
					case "Playlist Items":
						Array trackList = (Array)playlistMetadataIterator.next();
						loadAllTrackBelongsToThePlaylist(trackList, playlistToBeAdded);
					}
				}
			}
		}
	}
	public void loadAllTrackBelongsToThePlaylist(Array trackList, Playlist playlist) {
		if(playlist == null) return;
		
		List<Object> getTrackListDictionary = trackList.getAllPlaylistsDictionary();
		for (Object getTrackList : getTrackListDictionary) {
			Dict getTrack = (Dict) getTrackList;
			List<Object> trackInfo = getTrack.getDataList();
			playlist.addMusic(Integer.getInteger(((org.or5e.itunes.jaxb.binding.Integer)trackInfo.get(1)).getvalue()));		
		}
	}
	@Override
	public String getName() {
		return "ItunesHelper";
	}
	public Integer getTotalMusicTrackAvailable() {
		return _dataHolder.getTotalTrackAvailable();
	}
	public Integer getTotalPlaylistackAvailable() {
		return _dataHolder.getTotalPlaylistAvailable();
	}
	public DataHolder getDataHolder() {
		return _dataHolder;
	}
	
}

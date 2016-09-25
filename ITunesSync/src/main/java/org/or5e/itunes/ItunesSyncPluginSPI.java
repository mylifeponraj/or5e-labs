package org.or5e.itunes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;
import org.or5e.itunes.jaxb.binding.Array;
import org.or5e.itunes.jaxb.binding.Dict;
import org.or5e.itunes.jaxb.binding.Key;
import org.or5e.itunes.jaxb.binding.Plist;
import org.or5e.itunes.jspf.JSPFPlaylistManager;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ItunesSyncPluginSPI extends PluginLifecycleAdaptor {

	@Override
	public String getPluginID() {
		return getName();
	}

	@Override
	public void initilize() throws PluginException {
		info("Itunes Sync Plugin is initilizing....");
	}

	@Override
	public void destroy() {
		info("Itunes Sync Plugin is Shutting down....");
	}

	@Override
	public String getName() {
		return "ItunesSyncPluginSPI";
	}

	@Override
	public void doProcess() {
		info("Itunes Sync Plugin is Processing");
		try {
			File file = new File("C:/Users/JDPS/Music/iTunes/iTunes Music Library.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Plist.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			InputSource is = new InputSource(new FileInputStream(file));
			final SAXParserFactory sax = SAXParserFactory.newInstance();
			sax.setNamespaceAware(false);
			final XMLReader reader;
			try {
			    reader = sax.newSAXParser().getXMLReader();
			} catch (SAXException | ParserConfigurationException e) {
			    throw new RuntimeException(e);
			}
			SAXSource source = new SAXSource(reader, is);
			Plist list = (Plist)jaxbUnmarshaller.unmarshal(source);
			List<Object> plistDatas = list.getRootDictionary();
			if( plistDatas.size() > 0) {
				debug("Root Dictionary is identified...");
				Dict rootDictionary = (Dict)plistDatas.get(0);
				List<Object> rootDictDataList = rootDictionary.getDataList();
				Boolean isTrackIdentfied = Boolean.FALSE;
				Boolean isAlbumIdentified = Boolean.FALSE;
				for (Object data : rootDictDataList) {
					if(isTrackIdentfied) {
						ItunesHelper.getItunesHelper().readAllTracks((Dict)data);
						debug("Total Songs Loaded: "+ItunesHelper.getItunesHelper().getTotalMusicTrackAvailable());
						isTrackIdentfied = Boolean.FALSE;
					}
					if(isAlbumIdentified) {
						ItunesHelper.getItunesHelper().readAllAlbum((Array)data);
						debug("Total Playlist Loaded: "+ItunesHelper.getItunesHelper().getTotalPlaylistackAvailable());
						isAlbumIdentified = Boolean.FALSE;
					}

					if(data instanceof Key) {
						String keyValue = ((Key) data).getvalue();
						if(keyValue.equalsIgnoreCase("Tracks")) {
							isTrackIdentfied = Boolean.TRUE;
						}
						else if(keyValue.equalsIgnoreCase("Playlists")) {
							isAlbumIdentified = Boolean.TRUE;
						}
					}
				}
				JSPFPlaylistManager manager = new JSPFPlaylistManager();
				manager.writeAllPlaylistToJSPF();
			}
			else {
				debug("No Root dictionary present...");
			}
			
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}  
	}
	
	public static void main(String[] args) {
		Plugin plugin = new ItunesSyncPluginSPI();
		plugin.doProcess();
	}
}

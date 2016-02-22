package org.or5e.mp.itunes.playlist;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.or5e.core.BaseObject;
import org.or5e.mp.itunes.data.Plist;
import org.xml.sax.SAXException;

public class ItunesPlaylistSPI extends BaseObject implements ItunesPlaylist {

	@Override public void initilizeMusicLibrary() {
		File itunesLibrary = new File(getProperties("itunes-xml"));
		info(itunesLibrary);
//		info("Can we read the DTD: "+isNewPropertySupported());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Plist.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Plist pList = (Plist) jaxbUnmarshaller.unmarshal(itunesLibrary);
			info(pList);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	public boolean isNewPropertySupported() {
	       try {
	           SAXParserFactory spf = SAXParserFactory.newInstance();
	           SAXParser parser = spf.newSAXParser();
	           parser.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", "file");
	       } catch (ParserConfigurationException ex) {
	           System.out.println(ex.getMessage());
	       } catch (SAXException ex) {
	           String err = ex.getMessage();
	           if (err.indexOf("Property 'http://www.apple.com/DTDs/PropertyList-1.0.dtd' is not recognized.") > -1)
	           {
	             //expected, jaxp 1.5 not supported
	             return false;
	           }
	       }
	       return true;
	  }
	@Override public String getName() {
		return "ItunesPlaylistSPI";
	}
	public static void main(String[] args) {
		ItunesPlaylist playlist = new ItunesPlaylistSPI();
		playlist.initilizeMusicLibrary();
	}
}

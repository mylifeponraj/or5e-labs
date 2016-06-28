package org.or5e.mp.itunes.playlist;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.or5e.core.BaseObject;
import org.or5e.itunes.jxb.Dict;
import org.or5e.itunes.jxb.Key;
import org.or5e.itunes.jxb.Plist;
import org.xml.sax.SAXException;

public class ItunesPlaylistSPI extends BaseObject implements ItunesPlaylist {
	private static String keyNodeValue="0";
	
	@Override public void initilizeMusicLibrary() {
		File itunesLibrary = new File(getProperties("itunes-xml"));
		info(itunesLibrary);
//		info("Can we read the DTD: "+isNewPropertySupported());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Plist.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Plist pList = (Plist) jaxbUnmarshaller.unmarshal(itunesLibrary);
			List<Object> list = pList.getArrayOrDataOrDateOrDictOrRealOrIntegerOrStringOrTrueOrFalse();
			Dict rootDict = (Dict)list.get(0);
			List<Object> rootKeysandDicts = rootDict.getKeyOrArrayOrDataOrDateOrDictOrRealOrIntegerOrStringOrTrueOrFalse();
			for (Object object : rootKeysandDicts) {
				if(object instanceof Dict) {
					List<Object> songList = ((Dict)object).getKeyOrArrayOrDataOrDateOrDictOrRealOrIntegerOrStringOrTrueOrFalse();
					for (Object obj : songList) {
						if(obj instanceof Key) {
							songList((Key)obj);
						}
						else {
							songList((Dict) obj);
						}
					}
				}
				else if(object instanceof org.or5e.itunes.jxb.Array) {
					List<Object> playlist = ((org.or5e.itunes.jxb.Array)object).getArrayOrDataOrDateOrDictOrRealOrIntegerOrStringOrTrueOrFalse();
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	public void songList(Key keyNode) {
		keyNodeValue = keyNode.getvalue();
	}
	public void songList(Dict dictNode) {
		List<Object> keyValuePairOfDict = dictNode.getKeyOrArrayOrDataOrDateOrDictOrRealOrIntegerOrStringOrTrueOrFalse();
		if(((Key)keyValuePairOfDict.get(6)).getvalue().equalsIgnoreCase("Track Type")) {
			System.out.println("Data");
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

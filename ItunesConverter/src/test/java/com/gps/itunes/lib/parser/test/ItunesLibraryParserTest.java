package com.gps.itunes.lib.parser.test;

import com.gps.itunes.lib.exceptions.LibraryParseException;
import com.gps.itunes.lib.exceptions.NoChildrenException;
import com.gps.itunes.lib.parser.utils.LogInitializer;
import com.gps.itunes.lib.parser.utils.MemoryCheck;
import com.gps.itunes.lib.parser.utils.PropertyManager;
import com.gps.itunes.lib.tasks.LibraryPrinter;
import com.gps.itunes.lib.types.LibraryObject;
import com.gps.itunes.lib.xml.XMLParser;
import org.testng.annotations.Test;

/**
 * Created by leogps on 11/3/14.
 */
public class ItunesLibraryParserTest {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
            .getLogger(ItunesLibraryParserTest.class);

    static {
        LogInitializer.getInstance();
    }

    @Test
    public void test() throws LibraryParseException, NoChildrenException {

        final LibraryObject root = new XMLParser().parseXML(
                PropertyManager.getConfigurationMap().get(PropertyManager.Property.LIBRARY_FILE_LOCATION_PROPERTY.getKey()));

        LOG.debug(root);

        MemoryCheck.printUsedMemoryInfo();

        final LibraryPrinter printer = new LibraryPrinter(root);
        printer.printLibrary();

        checkMemory();
    }

    private void checkMemory(){
        MemoryCheck.printUsedMemoryInfo();
    }

}

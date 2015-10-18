package org.or5e.plugin.fs.filters;

import java.io.File;
import java.io.FilenameFilter;

public class ImageFileNameFilter implements FilenameFilter {

	@Override public boolean accept(File file, String name) {
		if(file.isDirectory()) {
			return true;
		}
		String extn = name.substring(name.lastIndexOf(".")).toLowerCase();
		return (ImageFormat.valueOf(extn) != null);
	}

}
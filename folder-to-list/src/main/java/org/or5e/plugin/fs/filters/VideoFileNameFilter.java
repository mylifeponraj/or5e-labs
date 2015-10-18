package org.or5e.plugin.fs.filters;

import java.io.File;
import java.io.FilenameFilter;

public class VideoFileNameFilter implements FilenameFilter {
	public boolean accept(File file, String name) {
		if(file.isDirectory()) {
			return true;
		}
		String extn = name.substring(name.lastIndexOf(".")).toLowerCase();
		return (VideoFormat.valueOf(extn) != null);
	}
}

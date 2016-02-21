package org.or5e.core.filefilter;

import java.io.File;
import java.io.FilenameFilter;

public class JarFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File folder, String fileName) {
		return fileName.endsWith(".jar");
	}
}
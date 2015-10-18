package org.or5e.plugin.fs;

import org.or5e.core.plugin.Lifecycle;

public interface ScanFolderService extends Lifecycle {
    public void scanForVideo();
    public void scanForMusic();
    public void scanForImage();
}
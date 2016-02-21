/**
 * Xtreme Media Player a cross-platform media player. 
 * Copyright (C) 2005-2014 Besmir Beqiri
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.or5e.mp.xtrememp;

import java.io.File;

import org.or5e.mp.xtrememp.playlist.Playlist;
import org.or5e.mp.xtrememp.playlist.PlaylistException;
import org.or5e.mp.xtrememp.playlist.PlaylistIO;
import org.or5e.mp.xtrememp.playlist.PlaylistItem;
import org.or5e.mp.xtrememp.playlist.filter.Predicate;
import org.or5e.mp.xtrememp.util.file.AudioFileFilter;
import org.or5e.mp.xtrememp.util.file.PlaylistFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Playlist manager class. Special thanks to rom1dep for the changes applied to
 * this class.
 *
 * @author Besmir Beqiri
 */
public class PlaylistManager {

	private final Logger logger = LoggerFactory.getLogger(PlaylistManager.class);
	private final AudioFileFilter audioFileFilter = AudioFileFilter.INSTANCE;
	private final PlaylistFileFilter playlistFileFilter = PlaylistFileFilter.INSTANCE;
	private final ControlListener controlListener;
	private final Playlist playlist;
	private Predicate<PlaylistItem> searchFilter;
	private String searchString;
	private int doubleSelectedRow = -1;
	private volatile boolean firstLoad = false;

	public PlaylistManager(ControlListener controlListener) {
		this.controlListener = controlListener;
		playlist = new Playlist();
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setFirstLoad(boolean flag) {
		this.firstLoad = flag;
	}

	public boolean savePlaylistDialog() {
		String fileName = "";
		File file = new File(fileName);
		try {
			PlaylistIO.saveXSPF(playlist, file.getParent() + File.separator + fileName);
		} catch (PlaylistException e) {
			e.printStackTrace();
		}
		Settings.setLastDir(file.getParent());
		return false;
	}
}

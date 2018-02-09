/**
 * Xtreme Media Player a cross-platform media player.
 * Copyright (C) 2005-2011 Besmir Beqiri
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.or5e.mp.xtrememp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.or5e.mp.xtrememp.playlist.Playlist.PlayMode;
import org.or5e.mp.xtrememp.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Besmir Beqiri
 */
public final class Settings {

    private static final Logger logger = LoggerFactory.getLogger(Settings.class);
    private static final String CACHE_DIR = ".xtrememp";
    private static final String SETTINGS_FILE = "settings.conf";
    private static final String PROPERTY_CACHE_DIR = "xtrememp.cache.dir";
    private static final String PROPERTY_PLAYER_AUDIO_GAIN = "xtrememp.player.audio.gain";
    private static final String PROPERTY_PLAYER_AUDIO_PAN = "xtrememp.player.audio.pan";
    private static final String PROPERTY_PLAYER_AUDIO_MUTE = "xtrememp.player.audio.mute";
    private static final String PROPERTY_PLAYER_AUDIO_MIXERNAME = "xtrememp.player.audio.mixer.name";
    private static final String PROPERTY_EQUILAZER_PRESET_INDEX = "xtrememp.equilazer.preset.index";
    private static final String PROPERTY_LAST_DIR = "xtrememp.last.dir";
    private static final String PROPERTY_LAST_VIEW = "xtrememp.last.view";
    private static final String PROPERTY_PLAYLIST_POSITION = "xtrememp.playlist.position";
    private static final String PROPERTY_PLAYLIST_PLAYMODE = "xtrememp.playlist.playmode";
    private static final Properties properties = new Properties();

    public static void setLastView(String lastView) {
        properties.setProperty(PROPERTY_LAST_VIEW, lastView);
    }

    public static String getLastView() {
        return properties.getProperty(PROPERTY_LAST_VIEW, Utilities.PLAYLIST_MANAGER);
    }

    public static void setLastDir(String lastDir) {
        properties.setProperty(PROPERTY_LAST_DIR, lastDir);
    }

    public static String getLastDir() {
        return properties.getProperty(PROPERTY_LAST_DIR, System.getProperty("user.dir"));
    }

    public static int getPlaylistPosition() {
        return Integer.parseInt(properties.getProperty(PROPERTY_PLAYLIST_POSITION, "0"));
    }

    public static void setPlaylistPosition(int playlistPosition) {
        properties.setProperty(PROPERTY_PLAYLIST_POSITION, Integer.toString(playlistPosition));
    }

    public static PlayMode getPlayMode() {
        return PlayMode.valueOf(properties.getProperty(PROPERTY_PLAYLIST_PLAYMODE, PlayMode.REPEAT_ALL.name()));
    }

    public static void setPlayMode(PlayMode playMode) {
        properties.setProperty(PROPERTY_PLAYLIST_PLAYMODE, playMode.name());
    }

    public static boolean isMuted() {
        return Boolean.parseBoolean(properties.getProperty(PROPERTY_PLAYER_AUDIO_MUTE, Boolean.toString(false)));
    }

    public static void setMuted(boolean mute) {
        properties.setProperty(PROPERTY_PLAYER_AUDIO_MUTE, Boolean.toString(mute));
    }

    public static int getGain() {
        return Integer.parseInt(properties.getProperty(PROPERTY_PLAYER_AUDIO_GAIN, String.valueOf(Utilities.MAX_GAIN)));
    }

    public static void setGain(int gain) {
        properties.setProperty(PROPERTY_PLAYER_AUDIO_GAIN, Integer.toString(gain));
    }

    public static int getPan() {
        return Integer.parseInt(properties.getProperty(PROPERTY_PLAYER_AUDIO_PAN, "0"));
    }

    public static void setPan(int pan) {
        properties.setProperty(PROPERTY_PLAYER_AUDIO_PAN, Integer.toString(pan));
    }

    public static String getMixerName() {
        return properties.getProperty(PROPERTY_PLAYER_AUDIO_MIXERNAME, "");
    }

    public static void setMixerName(String mixerName) {
        properties.setProperty(PROPERTY_PLAYER_AUDIO_MIXERNAME, mixerName);
    }

    public static int getEqualizerPresetIndex() {
        return Integer.parseInt(properties.getProperty(PROPERTY_EQUILAZER_PRESET_INDEX, "0"));
    }

    public static void setEqualizerPresetIndex(int eqIndex) {
        properties.setProperty(PROPERTY_EQUILAZER_PRESET_INDEX, Integer.toString(eqIndex));
    }

    public static File getCacheDir() {
        File cacheDir = new File(properties.getProperty(PROPERTY_CACHE_DIR, System.getProperty("user.home")), CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }
        return cacheDir;
    }

    public static void setCacheDir(File parent) {
        properties.setProperty(PROPERTY_CACHE_DIR, parent.getPath());
    }

    /**
     * Reads all the properties from the settings file.
     */
    public static void loadSettings() {
        File file = new File(getCacheDir(), SETTINGS_FILE);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                properties.load(fis);
                fis.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Writes all the properties in the settings file.
     */
    public static void storeSettings() {
        try {
            File file = new File(getCacheDir(), SETTINGS_FILE);
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, "Xtreme Media Player Settings");
            fos.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}

/**
 * Xtreme Media Player a cross-platform media player.
 * Copyright (C) 2005-2010 Besmir Beqiri
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
package org.or5e.mp.xtrememp.player.audio;

/**
 * This interface defines player controls.
 * 
 * @author Besmir Beqiri
 */
public interface PlaybackListener {
    public void playbackBuffering(PlaybackEvent pe);
    public void playbackOpened(PlaybackEvent pe);
    public void playbackEndOfMedia(PlaybackEvent pe);
    public void playbackPlaying(PlaybackEvent pe);
    public void playbackProgress(PlaybackEvent pe);
    public void playbackPaused(PlaybackEvent pe);
    public void playbackStopped(PlaybackEvent pe);
}

package org.or5e.mp.xtrememp.equalizer;

public class EqualizerSettings {
	private final String[] sliderLabels = {"60", "170", "310", "600", "1K", "3K", "6K", "12K", "14K", "16K"};
    private final String[] presetsLabels = {"Normal", "Classical", "Club", "Dance", "Full Bass", "Full Bass & Treble", "Full Treble", "Laptop", "Live", "Party", "Pop", "Reggae", "Rock", "Techno"};
    private final int[] gainValue = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    private final int[] PRESET_NORMAL = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    private final int[] PRESET_CLASSICAL = {50, 50, 50, 50, 50, 50, 70, 70, 70, 76};
    private final int[] PRESET_CLUB = {50, 50, 42, 34, 34, 34, 42, 50, 50, 50};
    private final int[] PRESET_DANCE = {26, 34, 46, 50, 50, 66, 70, 70, 50, 50};
    private final int[] PRESET_FULLBASS = {26, 26, 26, 36, 46, 62, 76, 78, 78, 78};
    private final int[] PRESET_FULLBASSTREBLE = {34, 34, 50, 68, 62, 46, 28, 22, 18, 18};
    private final int[] PRESET_FULLTREBLE = {78, 78, 78, 62, 42, 24, 8, 8, 8, 8};
    private final int[] PRESET_LAPTOP = {38, 22, 36, 60, 58, 46, 38, 24, 16, 14};
    private final int[] PRESET_LIVE = {66, 50, 40, 36, 34, 34, 40, 42, 42, 42};
    private final int[] PRESET_PARTY = {32, 32, 50, 50, 50, 50, 50, 50, 32, 32};
    private final int[] PRESET_POP = {56, 38, 32, 30, 38, 54, 56, 56, 54, 54};
    private final int[] PRESET_REGGAE = {48, 48, 50, 66, 48, 34, 34, 48, 48, 48};
    private final int[] PRESET_ROCK = {32, 38, 64, 72, 56, 40, 28, 24, 24, 24};
    private final int[] PRESET_TECHNO = {30, 34, 48, 66, 64, 48, 30, 24, 24, 28};

}

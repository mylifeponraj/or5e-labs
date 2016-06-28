package org.or5e.mp.itunes.playlist;

public enum AlbumProperties {
	TRACK_ID("Track ID"), NAME("Name"), LOCATION("Location"), ALBUM("Album"), SIZE("Size");
	
    private String name;

    private AlbumProperties(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
    public static void main(String[] args) {
	}
}

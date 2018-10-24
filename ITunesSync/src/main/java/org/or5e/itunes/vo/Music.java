package org.or5e.itunes.vo;

public class Music {
	public Integer trackID = null;
	public String trackDuration = null;
	public String trackName = null;
	public String trackLocation = null;
	public Integer getTrackID() {
		return trackID;
	}
	public String getTrackDuration() {
		return trackDuration;
	}
	public void setTrackDuration(String trackDuration) {
		this.trackDuration = trackDuration;
	}
	public void setTrackID(Integer trackID) {
		this.trackID = trackID;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public String getTrackLocation() {
		return trackLocation;
	}
	public void setTrackLocation(String trackLocation) {
		this.trackLocation = trackLocation;
	}
	public void addFields(String fieldName, Object value) {
		switch(fieldName) {
		case "trackID":
			this.trackID = Integer.getInteger(((org.or5e.itunes.jaxb.binding.Integer)value).getvalue());
			break;
		case "trackName":
			this.trackName = ((org.or5e.itunes.jaxb.binding.String)value).getvalue();
			break;
		case "trackLocation":
			this.trackLocation = ((org.or5e.itunes.jaxb.binding.String)value).getvalue();
		case "trackDuration":
			this.trackDuration = ((org.or5e.itunes.jaxb.binding.String)value).getvalue();
		}
	}
	@Override public String toString() {
		return "#EXTINF:"+trackDuration+","+trackName+"\n"+trackLocation+"\n";
	}
}

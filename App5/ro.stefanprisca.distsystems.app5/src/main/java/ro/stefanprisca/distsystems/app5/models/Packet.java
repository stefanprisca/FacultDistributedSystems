package ro.stefanprisca.distsystems.app5.models;

public class Packet {

	private long Id;
	private String name;
	private String location;
	private boolean trackingEnabled;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isTrackingEnabled() {
		return trackingEnabled;
	}
	public void setTrackingEnabled(boolean trackingEnabled) {
		this.trackingEnabled = trackingEnabled;
	}
	
	@Override
	public String toString() {
		return this.name + " @ " + this.location;
	}
}

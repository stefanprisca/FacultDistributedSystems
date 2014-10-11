package ro.stefanprisca.distsystems.app1.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "timezone")
public class TimeZoneResponse {

	private String status;
	private String raw_offset;
	private String dst_offset;
	private String time_zone_id;
	private String time_zone_name;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRaw_offset() {
		return raw_offset;
	}

	public void setRaw_offset(String raw_offset) {
		this.raw_offset = raw_offset;
	}

	public String getDst_offset() {
		return dst_offset;
	}

	public void setDst_offset(String dst_offset) {
		this.dst_offset = dst_offset;
	}

	public String getTime_zone_id() {
		return time_zone_id;
	}

	public void setTime_zone_id(String time_zone_id) {
		this.time_zone_id = time_zone_id;
	}

	public String getTime_zone_name() {
		return time_zone_name;
	}

	public void setTime_zone_name(String time_zone_name) {
		this.time_zone_name = time_zone_name;
	}
}

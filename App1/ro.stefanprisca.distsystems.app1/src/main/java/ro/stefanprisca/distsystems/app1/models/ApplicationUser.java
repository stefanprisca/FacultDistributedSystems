package ro.stefanprisca.distsystems.app1.models;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@ManagedBean
@Entity
public class ApplicationUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private boolean canEdit;

	private String name;
	private String homeAddress;
	private String longitude;
	private String latitude;
	private String birthDate;

	private String loginID;
	private String loginPW;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "User" + getName() + " lives at " + getHomeAddress() + "("
				+ getLatitude() + ";" + getLongitude() + "); Born at "
				+ getBirthDate();
	}

	public String getLoginName() {
		return loginID;
	}

	public void setLoginName(String loginName) {
		this.loginID = loginName;
	}

	public String getPassword() {
		return loginPW;
	}

	public void setPassword(String password) {
		this.loginPW = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Long getId() {
		return id;
	}

}

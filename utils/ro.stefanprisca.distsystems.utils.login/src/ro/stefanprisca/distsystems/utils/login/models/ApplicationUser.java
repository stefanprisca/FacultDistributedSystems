package ro.stefanprisca.distsystems.utils.login.models;

import javax.annotation.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("restriction")
@ManagedBean
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicationUser", propOrder = { "id", "name", "homeAddress",
		"longitude", "latitude", "birthDate", "loginID", "loginPW", "type",
		"canEdit" })
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

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
		return "User (" + getId() + ") " + getName() + " lives at "
				+ getHomeAddress() + "(" + getLatitude() + ";" + getLongitude()
				+ "); Born at " + getBirthDate();
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

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getLoginPW() {
		return loginPW;
	}

	public void setLoginPW(String loginPW) {
		this.loginPW = loginPW;
	}

}

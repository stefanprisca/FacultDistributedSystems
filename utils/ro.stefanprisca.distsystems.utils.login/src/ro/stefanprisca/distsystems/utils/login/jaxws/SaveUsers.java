
package ro.stefanprisca.distsystems.utils.login.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.13
 * Sun Nov 23 16:43:48 EET 2014
 * Generated source version: 2.7.13
 */

@XmlRootElement(name = "saveUsers", namespace = "http://login.utils.distsystems.stefanprisca.ro/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveUsers", namespace = "http://login.utils.distsystems.stefanprisca.ro/")

public class SaveUsers {

    @XmlElement(name = "arg0")
    private java.util.List<ro.stefanprisca.distsystems.utils.login.models.ApplicationUser> arg0;

    public java.util.List<ro.stefanprisca.distsystems.utils.login.models.ApplicationUser> getArg0() {
        return this.arg0;
    }

    public void setArg0(java.util.List<ro.stefanprisca.distsystems.utils.login.models.ApplicationUser> newArg0)  {
        this.arg0 = newArg0;
    }

}



package ro.stefanprisca.distsystems.utils.login.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.0.2
 * Fri Nov 07 20:12:07 EET 2014
 * Generated source version: 3.0.2
 */

@XmlRootElement(name = "addUser", namespace = "http://login.utils.distsystems.stefanprisca.ro/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addUser", namespace = "http://login.utils.distsystems.stefanprisca.ro/")

public class AddUser {

    @XmlElement(name = "arg0")
    private ro.stefanprisca.distsystems.utils.login.models.ApplicationUser arg0;

    public ro.stefanprisca.distsystems.utils.login.models.ApplicationUser getArg0() {
        return this.arg0;
    }

    public void setArg0(ro.stefanprisca.distsystems.utils.login.models.ApplicationUser newArg0)  {
        this.arg0 = newArg0;
    }

}


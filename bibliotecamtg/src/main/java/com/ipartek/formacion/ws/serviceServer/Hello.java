
package com.ipartek.formacion.ws.serviceServer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.6
 * Wed Apr 19 11:37:21 CEST 2017
 * Generated source version: 3.1.6
 */

@XmlRootElement(name = "hello", namespace = "http://ws.formacion.ipartek.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hello", namespace = "http://ws.formacion.ipartek.com/")

public class Hello {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

}


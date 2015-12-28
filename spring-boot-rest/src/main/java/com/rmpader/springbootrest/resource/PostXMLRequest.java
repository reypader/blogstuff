package com.rmpader.springbootrest.resource;

import com.rmpader.springbootrest.resource.adapter.MapAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RMPader
 */
@XmlRootElement(name = "request") //used for XML deserialization
@XmlAccessorType(XmlAccessType.FIELD)
public class PostXMLRequest {

    @XmlElement(name = "message")
    private String message;
    @XmlElement(name = "anotherMessage")
    private String anotherMessage;
    @XmlElement(name = "otherProperties")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, Object> otherProperties = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnotherMessage() {
        return anotherMessage;
    }

    public void setAnotherMessage(String anotherMessage) {
        this.anotherMessage = anotherMessage;
    }

    public Map<String, Object> getOtherProperties() {
        return otherProperties;
    }

    public void setOtherProperties(Map<String, Object> otherProperties) {
        this.otherProperties = otherProperties;
    }
}

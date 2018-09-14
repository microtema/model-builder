package de.seven.fate.model.builder.dto;

import javax.xml.bind.annotation.XmlElement;

public class AddressDTO {

    @XmlElement(required = true)
    private String streetName;

    @XmlElement(required = true)
    private String streetNumber;

    @XmlElement(required = true)
    private Integer zipCode;

    @XmlElement(required = true)
    private GeoDataDTO geoData;

    @XmlElement
    private String description;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public GeoDataDTO getGeoData() {
        return geoData;
    }

    public void setGeoData(GeoDataDTO geoData) {
        this.geoData = geoData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

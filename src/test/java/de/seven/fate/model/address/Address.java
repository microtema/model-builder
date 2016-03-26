package de.seven.fate.model.address;

import de.seven.fate.model.geo.GeoData;

/**
 * Created by Mario on 25.03.2016.
 */
public class Address {

    private String streetName;
    private String streetNumber;
    private Integer zipCode;

    private GeoData geoData;

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

    public GeoData getGeoData() {
        return geoData;
    }

    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
    }
}

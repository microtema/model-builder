package de.seven.fate.model.builder.address;

import de.seven.fate.model.builder.geo.GeoData;

import javax.validation.constraints.NotNull;

/**
 * Created by Mario on 25.03.2016.
 */
public class Address {

    @NotNull
    private String streetName;

    @NotNull
    private String streetNumber;

    @NotNull
    private Integer zipCode;

    @NotNull
    private GeoData geoData;

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

    public GeoData getGeoData() {
        return geoData;
    }

    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

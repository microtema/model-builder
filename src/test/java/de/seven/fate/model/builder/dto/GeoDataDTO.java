package de.seven.fate.model.builder.dto;

import de.seven.fate.model.builder.geo.GeoFormat;

import javax.xml.bind.annotation.XmlElement;

public class GeoDataDTO {

    @XmlElement(required = true)
    private Double latitude;

    @XmlElement(required = true)
    private Double longitude;

    @XmlElement(required = true)
    private GeoFormat type;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public GeoFormat getType() {
        return type;
    }

    public void setType(GeoFormat type) {
        this.type = type;
    }
}

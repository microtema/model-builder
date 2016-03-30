package de.seven.fate.model.geo;

/**
 * Created by Mario on 25.03.2016.
 */
public class GeoData {

    private Double latitude;
    private Double longitude;
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

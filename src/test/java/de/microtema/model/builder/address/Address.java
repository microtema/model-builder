package de.microtema.model.builder.address;

import de.microtema.model.builder.geo.GeoData;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
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
}

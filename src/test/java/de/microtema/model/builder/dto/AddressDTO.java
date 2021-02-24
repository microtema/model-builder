package de.microtema.model.builder.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDTO {

    @NotNull
    private String streetName;

    @NotNull
    private String streetNumber;

    @NotNull
    private Integer zipCode;

    @NotNull
    private GeoDataDTO geoData;

    @NotNull
    private String description;
}

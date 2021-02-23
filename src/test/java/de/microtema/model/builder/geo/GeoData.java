package de.microtema.model.builder.geo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeoData {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private GeoFormat type;

}

package de.microtema.model.builder.dto;

import de.microtema.model.builder.geo.GeoFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeoDataDTO {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private GeoFormat type;
}

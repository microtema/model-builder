package de.microtema.model.builder.dto;

import de.microtema.model.builder.geo.GeoFormat;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class GeoDataDTO {

    @XmlElement(required = true)
    private Double latitude;

    @XmlElement(required = true)
    private Double longitude;

    @XmlElement(required = true)
    private GeoFormat type;
}

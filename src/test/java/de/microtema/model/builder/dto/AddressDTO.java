package de.microtema.model.builder.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
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
}

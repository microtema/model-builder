package de.microtema.model.builder.converter;

import de.microtema.model.builder.dto.GeoDataDTO;
import de.microtema.model.builder.geo.GeoData;
import org.apache.commons.lang3.Validate;

import static de.microtema.model.builder.constants.Constants.MAY_NOT_BE_NULL;

public class GeoData2GeoDataDTOConverter {

    public GeoDataDTO convert(GeoData geoData) {
        Validate.notNull(geoData, MAY_NOT_BE_NULL, "geoData");

        GeoDataDTO personDTO = new GeoDataDTO();

        update(geoData, personDTO);

        return personDTO;
    }

    public void update(GeoData geoData, GeoDataDTO geoDataDTO) {
        Validate.notNull(geoData, MAY_NOT_BE_NULL, "geoData");
        Validate.notNull(geoDataDTO, MAY_NOT_BE_NULL, "geoDataDTO");

        geoDataDTO.setLatitude(geoData.getLatitude());
        geoDataDTO.setLongitude(geoData.getLongitude());
        geoDataDTO.setType(geoData.getType());
    }
}

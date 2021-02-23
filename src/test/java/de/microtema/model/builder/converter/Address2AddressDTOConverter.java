package de.microtema.model.builder.converter;

import de.microtema.model.builder.address.Address;
import de.microtema.model.builder.dto.AddressDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static de.microtema.model.builder.constants.Constants.MAY_NOT_BE_NULL;

public class Address2AddressDTOConverter {

    private final GeoData2GeoDataDTOConverter geoData2GeoDataDTOConverter;

    public Address2AddressDTOConverter(GeoData2GeoDataDTOConverter geoData2GeoDataDTOConverter) {
        this.geoData2GeoDataDTOConverter = geoData2GeoDataDTOConverter;
    }


    public AddressDTO convert(Address person) {
        Validate.notNull(person, MAY_NOT_BE_NULL, "person");

        AddressDTO personDTO = new AddressDTO();

        update(person, personDTO);

        return personDTO;
    }

    public void update(Address address, AddressDTO addressDTO) {
        Validate.notNull(address, MAY_NOT_BE_NULL, "address");
        Validate.notNull(addressDTO, MAY_NOT_BE_NULL, "addressDTO");

        addressDTO.setDescription(address.getDescription());
        addressDTO.setStreetName(address.getStreetName());
        addressDTO.setStreetNumber(address.getStreetNumber());
        addressDTO.setZipCode(address.getZipCode());

        addressDTO.setGeoData(geoData2GeoDataDTOConverter.convert(address.getGeoData()));
    }

    public List<AddressDTO> convertToList(Collection<Address> addresses) {
        if (CollectionUtils.isEmpty(addresses)) {

            return Collections.emptyList();
        }

        return addresses.stream().map(this::convert).collect(Collectors.toList());
    }
}

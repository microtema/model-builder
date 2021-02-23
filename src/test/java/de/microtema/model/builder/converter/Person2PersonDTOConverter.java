package de.microtema.model.builder.converter;

import de.microtema.model.builder.dto.PersonDTO;
import de.microtema.model.builder.person.Person;
import org.apache.commons.lang3.Validate;

import static de.microtema.model.builder.constants.Constants.MAY_NOT_BE_NULL;

public class Person2PersonDTOConverter {

    private final Address2AddressDTOConverter address2AddressDTOConverter;

    public Person2PersonDTOConverter(Address2AddressDTOConverter address2AddressDTOConverter) {
        this.address2AddressDTOConverter = address2AddressDTOConverter;
    }

    public PersonDTO convert(Person person) {
        Validate.notNull(person, MAY_NOT_BE_NULL, "person");

        PersonDTO personDTO = new PersonDTO();

        update(person, personDTO);

        return personDTO;
    }

    public void update(Person person, PersonDTO personDTO) {
        Validate.notNull(person, MAY_NOT_BE_NULL, "person");
        Validate.notNull(personDTO, MAY_NOT_BE_NULL, "personDTO");

        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        personDTO.setPosition(person.getPosition());
        personDTO.setDob(person.getDob());
        personDTO.setUpdateDate(person.getUpdateDate());
        personDTO.setEmail(person.getEmail());

        personDTO.setAddresses(address2AddressDTOConverter.convertToList(person.getAddresses()));
    }
}

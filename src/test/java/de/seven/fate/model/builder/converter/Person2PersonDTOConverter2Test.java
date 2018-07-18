package de.seven.fate.model.builder.converter;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.dto.PersonDTO;
import de.seven.fate.model.builder.person.Person;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class Person2PersonDTOConverter2Test {

    @InjectMocks
    Person2PersonDTOConverter sut;

    @Mock
    Address2AddressDTOConverter address2AddressDTOConverter;

    @Model
    Person person;

    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void convert() {

        PersonDTO personDTO = sut.convert(person);

        assertNotNull(personDTO);

        assertEquals(person.getId(), personDTO.getId());
        assertEquals(person.getName(), personDTO.getName());
        assertEquals(person.getPhoneNumber(), personDTO.getPhoneNumber());
        assertEquals(person.getEmail(), personDTO.getEmail());
        assertEquals(person.getPosition(), personDTO.getPosition());
        assertEquals(person.getUpdateDate(), personDTO.getUpdateDate());
        assertEquals(person.getDob(), personDTO.getDob());

        verify(address2AddressDTOConverter).convertToList(person.getAddresses());
    }
}

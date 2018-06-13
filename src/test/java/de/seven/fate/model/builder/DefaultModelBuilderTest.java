package de.seven.fate.model.builder;

import de.seven.fate.model.builder.address.Address;
import de.seven.fate.model.builder.person.Person;
import de.seven.fate.model.builder.util.NumberUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DefaultModelBuilderTest {

    DefaultModelBuilder<Person> sut = new DefaultModelBuilder<Person>() {
        @Override
        public Class<Person> getGenericType() {
            return Person.class;
        }
    };

    Person model;

    @Test
    public void max() {

        model = sut.max();

        assertNotNull(model);
        assertNotNull(model.getId());
        assertNotNull(model.getUpdateDate());
        assertNotNull(model.getAmount());
        assertNotNull(model.getPhoneBill());

        List<Address> addresses = model.getAddresses();
        assertNotNull(addresses);

        assertFalse(addresses.isEmpty());
    }

    @Test
    public void list() {

        int randomCollectionSize = NumberUtil.random(1, 20);

        assertEquals(randomCollectionSize, sut.list(randomCollectionSize).size());
    }

    @Test
    public void set() {

        int randomCollectionSize = NumberUtil.random(1, 15);

        assertEquals(randomCollectionSize, sut.set(randomCollectionSize).size());
    }

    @Test
    public void fix() {
        model = sut.fix();

        assertSame(model, sut.fix());
    }
}
package de.microtema.model.builder.wrapper;

import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PersonWrapperBuilderTest {

    ModelBuilder<PersonWrapper> sut = ModelBuilderFactory.createBuilder(PersonWrapper.class);

    @Test
    public void min() {

        PersonWrapper model = sut.min();

        assertNotNull(model);
        assertNull(model.getId());
    }

    @Test
    public void max() {

        PersonWrapper model = sut.max();

        assertNotNull(model);

        assertNotNull(model.getId());
        assertNotNull(model.getAddresses());

        assertFalse(model.getAddresses().isEmpty());
    }
}
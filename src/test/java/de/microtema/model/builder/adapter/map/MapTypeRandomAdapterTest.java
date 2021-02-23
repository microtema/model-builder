package de.microtema.model.builder.adapter.map;

import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MapTypeRandomAdapterTest {

    @Inject
    MapTypeRandomAdapter sut;

    @Model
    String property;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void randomValueDefault() {

        Map map = sut.randomValueDefault(property);

        assertNotNull(map);

        assertFalse(map.isEmpty());
    }

}
package de.seven.fate.model.builder;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.person.Person;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MapModelBuilderTest {

    @Model
    Map<String, Integer> model;

    @Model
    Map map;

    @Model
    Map<String, Person> idToPerson;

    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void model() {

        assertNotNull(model);

        model.keySet().forEach(it -> assertTrue((it instanceof String)));
        model.values().forEach(it -> assertTrue((it instanceof Integer)));
    }

    @Test
    public void map() {

        assertNotNull(map);

        map.keySet().forEach(it -> assertTrue((it instanceof String)));
        map.values().forEach(it -> assertTrue((it instanceof String)));
    }

    @Test
    public void idToPerson() {

        assertNotNull(idToPerson);

        idToPerson.keySet().forEach(it -> assertTrue((it instanceof String)));
        idToPerson.values().forEach(it -> assertTrue((it instanceof Person)));
    }
}

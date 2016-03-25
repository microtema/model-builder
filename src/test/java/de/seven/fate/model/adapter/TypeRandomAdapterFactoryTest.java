package de.seven.fate.model.adapter;

import de.seven.fate.model.person.Person;
import de.seven.fate.model.person.PersonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Mario on 25.03.2016.
 */
public class TypeRandomAdapterFactoryTest {


    TypeRandomAdapterFactory sut;

    PersonBuilder builder = new PersonBuilder();

    FloatRandomTypeAdapter floatRandomPropertyValueAdapter = new FloatRandomTypeAdapter();

    PropertyRandomAdapter<Float> houseBillNullValueAdapter = new HouseBillNullAdapterProperty();

    PropertyRandomAdapter<Float> phoneBillNullValueAdapter = new PhoneBillNullAdapterProperty();

    @Test
    public void registerRandomAdapter() throws Exception {

        Person person = builder.max();

        assertNull(person.getPhoneBill());

        sut.registerAdapter(floatRandomPropertyValueAdapter);

        person = builder.max();

        assertEquals(Float.valueOf(Float.MIN_NORMAL), person.getPhoneBill());
    }

    @Test
    public void registerRandomAdapter2() throws Exception {

        sut.registerAdapter(floatRandomPropertyValueAdapter);

        Person person = builder.max();

        assertEquals(Float.valueOf(Float.MIN_NORMAL), person.getPhoneBill());

        AbstractTypeRandomAdapter<Float> floatAbstractTypeRandomValueAdapter = sut.lookupRandomAdapter(Float.class);

        floatAbstractTypeRandomValueAdapter.registerPropertyRandomAdapter(phoneBillNullValueAdapter);

        floatAbstractTypeRandomValueAdapter.registerPropertyRandomAdapter(houseBillNullValueAdapter);

        person = builder.max();

        assertNull(person.getPhoneBill());
    }

    static class FloatRandomTypeAdapter extends AbstractTypeRandomAdapter<Float> {

        @Override
        protected Float randomValueDefault(String propertyName) {
            return Float.MIN_NORMAL;
        }

        @Override
        public Class<?> getValueType() {
            return Float.class;
        }
    }

    static class PhoneBillNullAdapterProperty implements PropertyRandomAdapter<Float> {
        @Override
        public Float randomValue() {
            return null;
        }

        @Override
        public String getPropertyName() {
            return "PhoneBill";
        }
    }

    ;

    static class HouseBillNullAdapterProperty implements PropertyRandomAdapter<Float> {
        @Override
        public Float randomValue() {
            return Float.valueOf(Float.NEGATIVE_INFINITY);
        }

        @Override
        public String getPropertyName() {
            return "HouseBill";
        }
    }

    ;

}
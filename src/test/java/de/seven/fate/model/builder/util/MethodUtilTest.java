package de.seven.fate.model.builder.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MethodUtilTest {

    MethodUtil sut;

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<MethodUtil> constructor = MethodUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

    @Test
    public void getProperties() {

        Set<String> properties = MethodUtil.getProperties(Person.class);

        assertNotNull(properties);

        assertEquals(2, properties.size());

        assertTrue(properties.contains("firstName"));
        assertTrue(properties.contains("emails"));
    }

    @Test
    public void getProperties_from_cache() {

        Map<Class<?>, Set<String>> cache = MethodUtil.PROPERTIES_CACHE;

        cache.clear();

        Set<String> properties = MethodUtil.getProperties(Person.class);

        assertEquals(1, cache.size());

        assertEquals(cache.get(Person.class), properties);
    }

    @Test
    public void setProperty() {

        Person person = new Person();

        MethodUtil.setProperties(person, "firstName", "Foo");

        assertEquals("Foo", person.getFirstName());
    }

    @Test
    public void setPropertyArray() {

        Person person = new Person();

        MethodUtil.setProperties(person, "emails", new String[]{"Foo"});

        assertArrayEquals(new String[]{"Foo"}, person.getEmails());
    }

    @Test
    public void setProperty_with_null_value() {

        Person person = new Person();

        MethodUtil.setProperties(person, "firstName", null);

        assertNull(person.getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setProperty_will_throw_exception() {

        Person person = new Person();

        MethodUtil.setProperties(person, "class", "Foo");
    }

    @Test
    public void getGetterMethod() {

        Method method = MethodUtil.getGetterMethod(Person.class, "firstName");

        assertNotNull(method);
    }

    @Test
    public void getPropertyName_set() {

        assertEquals("firstName", MethodUtil.getPropertyName("setFirstName"));
    }

    @Test
    public void getPropertyName_get() {

        assertEquals("firstName", MethodUtil.getPropertyName("getFirstName"));
    }

    public class Person {

        private boolean created;

        private String firstName;

        private String[] emails;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String[] getEmails() {
            return emails;
        }

        public void setEmails(String[] emails) {
            this.emails = emails;
        }

        public String getName() {

            return "Name: " + this.firstName;
        }

        public void setName(String firstName, String lastName) {
            this.firstName = firstName + " : " + lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return Objects.equals(firstName, person.firstName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName);
        }
    }
}
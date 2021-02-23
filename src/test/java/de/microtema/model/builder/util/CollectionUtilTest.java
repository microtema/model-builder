package de.microtema.model.builder.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static de.microtema.model.builder.util.CollectionUtil.first;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 24.03.2016.
 */
public class CollectionUtilTest {

    CollectionUtil sut;

    List<String> list = Arrays.asList("Foo", "Bar", "World", "Java", "JUnit", "Maven", "JavaEE", "Git", "EJB");


    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<CollectionUtil> constructor = CollectionUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

    @Test
    public void testRandomList() {

        Collection<String> randomCollection = CollectionUtil.randomList(list);

        assertEquals(list.size(), randomCollection.size());
        assertNotEquals(list, randomCollection);
    }

    @Test
    public void testRandomListWithArray() {

        Collection<String> randomCollection = CollectionUtil.randomList(list.toArray(new String[list.size()]));

        assertEquals(list.size(), randomCollection.size());
        assertNotEquals(list, randomCollection);
    }


    @Test
    public void testRandomWithArray() {

        String randomElement = CollectionUtil.random(list.toArray(new String[list.size()]));

        assertNotNull(randomElement);
        assertTrue(list.contains(randomElement));
    }

    @Test
    public void testRandomWithExceptionWithArray() {

        String randomElement = CollectionUtil.random(list.toArray(new String[list.size()]), list.get(0));

        assertNotNull(randomElement);
        assertNotEquals(list.get(0), randomElement);
        assertTrue(list.contains(randomElement));
    }

    @Test
    public void testRandomWithExceptionWithArrayWillReturnNull() {

        String randomElement = CollectionUtil.random(Collections.emptyList(), list.get(0));

        assertNull(randomElement);
    }

    @Test
    public void testRandomFromList() {

        assertNotEquals(first(list), CollectionUtil.randomList(list));
    }

    @Test
    public void testFirstWillReturnNull() {

        assertNull(CollectionUtil.first(null));
    }

    @Test
    public void testFirstOnEmpty() {

        assertNull(CollectionUtil.first(Collections.emptyList()));
    }

    @Test
    public void testRandom() {

        assertNotNull(CollectionUtil.random(list));
    }

    @Test
    public void testRandomWithExcludes() {

        String random = CollectionUtil.random(list);
        String other = CollectionUtil.random(list, random);

        assertNotEquals(random, CollectionUtil.random(list, random, other));
    }

    @Test
    public void testLastOnNull() {

        assertNull(CollectionUtil.last(null));
    }

    @Test
    public void testLastOnEmpty() {

        assertNull(CollectionUtil.last(Collections.emptyList()));
    }

    @Test
    public void testLast() {

        assertEquals(list.get(list.size() - 1), CollectionUtil.last(list));
    }

}

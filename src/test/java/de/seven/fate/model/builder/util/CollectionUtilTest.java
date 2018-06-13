package de.seven.fate.model.builder.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static de.seven.fate.commons.utils.CollectionUtil.first;
import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class CollectionUtilTest {

    CollectionUtil sut;

    List<String> list = Arrays.asList("Foo", "Bar", "World", "Java", "JUnit", "Maven", "JavaEE", "Git", "EJB");

    @Test
    public void testRandomList() {

        Collection<String> randomCollection = CollectionUtil.randomList(list);

        assertEquals(list.size(), randomCollection.size());
        assertNotEquals(list, randomCollection);
    }


    @Test
    public void testRandomFromList() {

        assertNotEquals(first(list), CollectionUtil.randomList(list));
    }

    @Test
    public void testFirst() {

        assertEquals(list.get(0), first(list));
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

}

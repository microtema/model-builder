package de.seven.fate.model.util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class CollectionUtilTest {

    CollectionUtil sut;

    List<String> list = Arrays.asList("Foo", "Bar", "World", "Java", "JUnit", "Maven","JavaEE", "Git","EJB");
    Set<String> set = new HashSet<>(list);

    @Test
    public void testRandomList() throws Exception {

        Collection<String> randomCollection = sut.randomList(list);

        assertEquals(list.size(), randomCollection.size());
        assertNotEquals(list, randomCollection);
    }


    @Test
    public void testRandomFromList() throws Exception {
        assertNotEquals(sut.first(list), list);
    }

    @Test
    public void testRandomFromSet() throws Exception {
        assertNotEquals(sut.first(set), set);
    }

    @Test
    public void testRandom1() throws Exception {

    }

    @Test
    public void testFirst() throws Exception {

    }

    @Test
    public void testRandom2() throws Exception {

    }

    @Test
    public void testRandom3() throws Exception {

    }
}
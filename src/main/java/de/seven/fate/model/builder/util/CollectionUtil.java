package de.seven.fate.model.builder.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CollectionUtil {

    private CollectionUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static <E> List<E> randomList(Collection<E> collection) {

        List<E> list = new ArrayList<>(collection);

        Collections.shuffle(list);

        return list;
    }

    public static <E> List<E> randomList(E[] elements) {

        return randomList(Arrays.asList(elements));
    }

    public static <E> E random(E[] elements) {

        return first(randomList(elements));
    }

    public static <E> E random(Collection<E> collection) {

        return first(randomList(collection));
    }

    public static <E> E random(E[] values, E... except) {

        return random(Arrays.asList(values), except);
    }

    public static <E> E random(Collection<E> collection, E... except) {

        List<E> excludes = Arrays.asList(except);

        for (E entry : collection) {

            if (!excludes.contains(entry)) {
                return entry;
            }
        }

        return null;
    }

    /**
     * Return first entry of collection
     *
     * @param collection may be null
     * @param <E>        required type
     * @return first entry of collection or throw IllegalStateException
     */
    public static <E> E first(Collection<E> collection) {

        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }

        return collection.stream().findFirst().orElse(null);
    }

    /**
     * Return last entry of collection
     *
     * @param collection may be null
     * @param <E>        required type
     * @return last entry of collection or throw IllegalStateException
     */
    public static <E> E last(List<E> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }

        return collection.get(collection.size() - 1);
    }
}

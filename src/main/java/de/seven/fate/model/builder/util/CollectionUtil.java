package de.seven.fate.model.builder.util;

import java.util.*;

import static de.seven.fate.commons.utils.CollectionUtil.first;

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
}

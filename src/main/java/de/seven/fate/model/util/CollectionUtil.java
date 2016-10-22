package de.seven.fate.model.util;

import java.util.*;

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

    public static <E> E first(Collection<E> collection) {

        if (isEmpty(collection)) {
            return null;
        }

        Iterator<E> iterator = collection.iterator();

        if (iterator.hasNext()) {
            return iterator.next();
        }

        throw new IllegalStateException("Should not happen");
    }

    private static <E> boolean isEmpty(Collection<E> collection) {

        return (collection == null || collection.isEmpty());
    }

    private static <E> boolean isEmpty(E[] array) {

        return (array == null || array.length == 0);
    }

    public static <E> E random(E[] values, E except) {

        return random(Arrays.asList(values), except);
    }

    public static <E> E random(Collection<E> collection, E except) {

        Collection<E> randomCollection = randomList(collection);

        Iterator<E> iterator = randomCollection.iterator();

        if (iterator.hasNext()) {

            E entry = iterator.next();

            if (!Objects.equals(entry, except)) {
                return entry;
            }
        }

        return null;
    }

    public static <E> List<E> asList(E[] array) {
        if (isEmpty(array)) {
            return new ArrayList<>();
        }

        return Arrays.asList(array);
    }

    public static <K, V> Map<K, V> createMap(Object... objects) {
        if (isEmpty(objects)) {
            return Collections.emptyMap();
        }

        if (objects.length % 2 != 0) {
            throw new IllegalArgumentException("objects should be even");
        }

        Map<K, V> map = new HashMap<>();

        for (int i = 0; i < objects.length; i++) {
            map.put((K) objects[i], (V) objects[++i]);
        }

        return map;
    }
}

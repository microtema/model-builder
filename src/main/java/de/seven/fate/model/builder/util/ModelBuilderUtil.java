package de.seven.fate.model.builder.util;

import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

import static de.seven.fate.model.builder.constants.Constants.MAX_COLLECTION_SIZE;
import static de.seven.fate.model.builder.constants.Constants.MIN_COLLECTION_SIZE;

public final class ModelBuilderUtil {

    private ModelBuilderUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static int randomCollectionSize() {

        return Math.max(MIN_COLLECTION_SIZE, new Random().nextInt(MAX_COLLECTION_SIZE));
    }

    /*
     * ATTENTION! Size of Collection of type Set can be less than size, when adding multiple the same Object
     */
    public static <T> void fillCollection(int size, Collection<T> collection, Function<Void, T> supplier) {

        int count = 0;
        while (count++ < size) {

            T model = supplier.apply(null);

            collection.add(model);
        }
    }
}

package de.seven.fate.model.builder;


import de.seven.fate.model.util.ClassUtil;
import de.seven.fate.model.util.CollectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

import static de.seven.fate.model.adapter.TypeRandomAdapterFactory.*;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {

    private static final int MIN_COLLECTION_SIZE = 1;
    private static final int MAX_COLLECTION_SIZE = 10;

    private static int randomCollectionSize() {
        return Math.max(MIN_COLLECTION_SIZE, new Random().nextInt(MAX_COLLECTION_SIZE));
    }

    @Override
    public Class<T> getGenericType() {

        return ClassUtil.getGenericType(getClass());
    }

    @Override
    public T min() {

        return min(null, false, false);
    }

    protected T min(Field rootField, final boolean skip, final boolean required) {

        return build(rootField, new MinModelAction(required), skip);
    }

    private T build(Field rootField, ModelAction modelAction, boolean skip) {

        Class<T> modelType = getGenericType();

        if (!ClassUtil.isComplexType(modelType)) {

            String propertyName = rootField != null ? rootField.getName() : null;

            return getRandomValue(modelType, propertyName);

        } else if (modelType.isEnum()) {

            return CollectionUtil.random(modelType.getEnumConstants());

        } else if (ClassUtil.isCollectionType(modelType)) {

            Type propertyType = rootField != null ? rootField.getGenericType() : null;

            return getCollection(modelType, propertyType, skip);

        } else if (boolean[].class.isAssignableFrom(modelType)) {

            return null; //ignore this
        }

        T model = createModel();

        generateRandomFieldValues(model, modelAction, skip);

        return model;
    }


    @Override
    public T max() {

        return max(false);
    }

    @Override
    public T random() {

        return random(getRandomValue(Boolean.class));
    }

    @Override
    public List<T> list() {

        return list(randomCollectionSize());
    }

    @Override
    public List<T> list(int size) {

        List<T> list = new ArrayList<>();

        fillCollection(size, list, false);

        return list;
    }

    @Override
    public Set<T> set() {

        return set(randomCollectionSize());
    }

    @Override
    public Set<T> set(int size) {

        return set(size, false);
    }

    public List<T> list(boolean skip) {

        return list(randomCollectionSize(), skip);
    }

    protected List<T> list(int size, boolean skip) {
        List<T> list = new ArrayList<>();

        fillCollection(size, list, skip);

        return list;
    }

    protected Set<T> set(int size, boolean skip) {
        Set<T> set = new HashSet<>();

        fillCollection(size, set, skip);

        return set;
    }

    public Set<T> set(boolean skip) {

        return set(randomCollectionSize(), skip);
    }

    /*
     * ATTENTION! Size of Collection of type Set can be less than size, when adding multiple the same Object
     */
    private void fillCollection(int size, Collection<T> collection, boolean skip) {

        int count = 0;
        while (count++ < size) {
            collection.add(random(getRandomValue(Boolean.class), skip));
        }
    }

    private T random(boolean minOrMax) {
        return minOrMax ? min() : max();
    }

    private T random(boolean minOrMax, boolean skip) {
        return minOrMax ? min(null, skip, false) : max(skip);
    }

    private T createModel() {

        Constructor<T> constructor = ClassUtil.getConstructor(getGenericType());

        Object[] args = getRandomParameters(constructor.getParameterTypes());

        return ClassUtil.createInstance(constructor, args);
    }

    private T max(boolean skip) {

        return min(null, skip, true);
    }

}

package de.seven.fate.model.builder;


import de.seven.fate.commons.utils.ClassUtil;
import de.seven.fate.model.builder.adapter.TypeRandomAdapterFactory;
import de.seven.fate.model.builder.util.CollectionUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {

    private static final int MIN_COLLECTION_SIZE = 1;
    private static final int MAX_COLLECTION_SIZE = 10;

    private Map<Class<T>, T> fixModels = Collections.synchronizedMap(new HashMap<Class<T>, T>());

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

    @SuppressWarnings("unchecked")
    private T build(Field rootField, ModelAction modelAction, boolean skip) {

        Class<T> modelType = getGenericType();

        if (!de.seven.fate.model.builder.util.ClassUtil.isComplexType(modelType)) {

            String propertyName = rootField != null ? rootField.getName() : null;

            return TypeRandomAdapterFactory.getRandomValue(modelType, propertyName);

        } else if (modelType.isEnum()) {

            return CollectionUtil.random(modelType.getEnumConstants());

        } else if (de.seven.fate.model.builder.util.ClassUtil.isCollectionType(modelType)) {

            Type propertyType = rootField != null ? rootField.getGenericType() : null;

            return TypeRandomAdapterFactory.getCollection(modelType, propertyType, skip);

        } else if (modelType.isArray()) {

            Class propertyType = modelType.getComponentType();

            return TypeRandomAdapterFactory.getArray(propertyType, skip);

        } else if (boolean[].class.isAssignableFrom(modelType)) {

            return null; //ignore this

        } else if (Class.class.equals(modelType)) {

            return (T) Class.class;
        }

        T model = createModel();

        TypeRandomAdapterFactory.generateRandomFieldValues(model, modelAction, skip);

        return model;
    }


    @Override
    public T max() {

        return max(false);
    }

    @Override
    public T random() {

        return random(TypeRandomAdapterFactory.getRandomValue(Boolean.class));
    }

    @Override
    public T fix() {

        Class<T> genericType = getGenericType();

        if (fixModels.containsKey(genericType)) {
            return fixModels.get(genericType);
        }

        T min = min();

        fixModels.put(genericType, min);

        return min;
    }

    @Override
    public T fromResource(String resourceLocation) {

        Class<T> genericType = getGenericType();
        if (Properties.class.isAssignableFrom(genericType)) {

            String extension = FilenameUtils.getExtension(resourceLocation);

            if (StringUtils.equals(extension, "properties")) {

                Properties properties = new Properties();

                try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

                    properties.load(resourceAsStream);
                } catch (IOException e) {

                    throw new IllegalArgumentException(e);
                }

                return (T) properties;
            }

            if (StringUtils.equals(extension, "xml")) {

                Properties properties = new Properties();

                try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

                    properties.loadFromXML(resourceAsStream);
                } catch (IOException e) {

                    throw new IllegalArgumentException(e);
                }

                return (T) properties;
            }
        }

        throw new UnsupportedOperationException();
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
    protected void fillCollection(int size, Collection<T> collection, boolean skip) {

        int count = 0;
        while (count++ < size) {
            collection.add(random(TypeRandomAdapterFactory.getRandomValue(Boolean.class), skip));
        }
    }

    /*
     * ATTENTION! Size of Collection of type Set can be less than size, when adding multiple the same Object
     */
    protected void fillCollection(int size, Collection<T> collection) {

        int count = 0;
        while (count++ < size) {
            collection.add(random());
        }
    }

    private T random(boolean minOrMax) {
        return minOrMax ? min() : max();
    }

    private T random(boolean minOrMax, boolean skip) {
        return minOrMax ? min(null, skip, false) : max(skip);
    }

    private T createModel() {

        Class<T> genericType = getGenericType();

        Constructor<T> constructor = ClassUtil.getConstructor(genericType);

        Object[] args = TypeRandomAdapterFactory.getRandomParameters(constructor.getParameterTypes());

        return ClassUtil.createInstance(genericType, args);
    }

    private T max(boolean skip) {

        return min(null, skip, true);
    }

}

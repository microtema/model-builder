package de.seven.fate.model.builder;

import de.seven.fate.model.builder.adapter.TypeRandomAdapterFactory;
import de.seven.fate.model.builder.util.ClassUtil;
import de.seven.fate.model.builder.util.CollectionUtil;
import de.seven.fate.model.builder.util.MethodUtil;
import de.seven.fate.model.builder.util.ModelBuilderUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static de.seven.fate.model.builder.adapter.TypeRandomAdapterFactory.getRandomValue;
import static de.seven.fate.model.builder.constants.Constants.MAY_NOT_BE_EMPTY;
import static de.seven.fate.model.builder.util.ModelBuilderUtil.randomCollectionSize;

/**
 * Top level interface of Model Builder within default methods.
 *
 * @param <T> generic type of model
 */
public interface ModelBuilder<T> {

    /**
     * @return new Instance of Model and init only required fields
     */
    default T min() {

        return build(null, false, false);
    }

    /**
     * @return new Instance of Model and init all fields on top level.
     */
    default T max() {

        return build(null, false, true);
    }

    /**
     * @return new Instance of Model and init as Min or Max
     */
    default T mix() {

        return getRandomValue(Boolean.class) ? min() : max();
    }

    /**
     * @return new Instance of Model and init as Fix
     */
    default T fix() {

        return min();
    }

    /**
     * @param resourceLocation may not be empty
     * @return new Instance of Model created from Resource
     */
    @SuppressWarnings("unchecked")
    default T fromResource(String resourceLocation) {
        Validate.notEmpty(resourceLocation, MAY_NOT_BE_EMPTY, "resourceLocation");

        Class<T> genericType = getGenericType();

        if (Properties.class.isAssignableFrom(genericType)) {

            if (FilenameUtils.isExtension(resourceLocation, "properties")) {

                return (T) ModelBuilderUtil.fromProperties(resourceLocation);
            }

            if (FilenameUtils.isExtension(resourceLocation, "xml")) {

                return (T) ModelBuilderUtil.fromXml(resourceLocation);
            }
        }

        throw new UnsupportedOperationException();
    }

    /**
     * @return new List by mix size with new Instances of Models and init as Min or Max
     */
    default List<T> list() {

        return list(randomCollectionSize());
    }

    /**
     * @param size of Collection
     * @return new Set by mix size with new Instances of Models and init as Min or Max
     */
    default List<T> list(int size) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = mix();

            list.add(model);
        }

        return list;
    }

    /**
     * @return new Set by mix size with new Instances of Models and init as Min or Max
     */
    default Set<T> set() {

        return set(randomCollectionSize());
    }

    /**
     * @param size of Collection
     * @return new Set by fixed size with new Instances of Models and init as Min or Max
     */
    default Set<T> set(int size) {

        return new HashSet<>(list(size));
    }

    default T build(Method rootMethod, final boolean skip, final boolean required) {

        return build(rootMethod, new DefaultModelAction(required), skip);
    }

    /**
     * @param rootMethod  from root model may be null
     * @param modelAction may be not null
     * @param skip        should property be skipped
     * @return new model from type T
     */
    @SuppressWarnings("unchecked")
    default T build(Method rootMethod, ModelAction modelAction, boolean skip) {
        Validate.notNull(modelAction);

        Class<T> modelType = getGenericType();

        if (!ClassUtil.isComplexType(modelType)) {

            String propertyName = rootMethod != null ? MethodUtil.getPropertyName(rootMethod.getName()) : null;

            return getRandomValue(modelType, propertyName);

        } else if (modelType.isEnum()) {

            return CollectionUtil.random(modelType.getEnumConstants());

        } else if (ClassUtil.isCollectionType(modelType)) {

            Type propertyType = rootMethod != null ? ClassUtil.getGenericType(rootMethod.getGenericReturnType()) : null;

            return TypeRandomAdapterFactory.getCollection(modelType, propertyType, skip, getActualTypeArguments());

        } else if (modelType.isArray()) {

            Class propertyType = modelType.getComponentType();

            return TypeRandomAdapterFactory.getArray(propertyType, skip);

        } else if (Class.class.equals(modelType)) {

            return (T) Class.class;
        }

        T model = createModel();

        TypeRandomAdapterFactory.generateRandomFieldValues(model, modelAction, skip);

        return model;
    }

    /**
     * @return Generic Model Typ
     */
    default Class<T> getGenericType() {

        return ClassUtil.getGenericType(getClass());
    }

    /**
     * @return new Model
     */
    default T createModel() {

        Class<T> genericType = getGenericType();

        Constructor<T> constructor = ClassUtil.getConstructor(genericType);

        Object[] args = TypeRandomAdapterFactory.getRandomParameters(constructor.getParameterTypes());

        return ClassUtil.createInstance(genericType, args);
    }

    /**
     * return Actual Type Arguments from Map
     *
     * @return field
     */
    default Class[] getActualTypeArguments() {

        return new Class[0];
    }

}

package de.microtema.model.builder;

import de.microtema.model.builder.adapter.TypeRandomAdapterFactory;
import de.microtema.model.builder.constants.Constants;
import de.microtema.model.builder.util.ClassUtil;
import de.microtema.model.builder.util.CollectionUtil;
import de.microtema.model.builder.util.MethodUtil;
import de.microtema.model.builder.util.ModelBuilderUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

import static de.microtema.model.builder.adapter.TypeRandomAdapterFactory.getRandomValue;

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

        return build(null, new DefaultModelAction(false), false, true);
    }

    /**
     * @return new Instance of Model and init all fields on top level.
     */
    default T max() {

        return build(null, new DefaultModelAction(true), false, true);
    }

    /**
     * @return new Instance of Model and init as Fix
     */
    default T fix() {

        return build(null, new DefaultModelAction(false), false, false);
    }

    /**
     * @return new Instance of Model and init as Min or Max
     */
    default T mix() {

        boolean minOrMax = getRandomValue(Boolean.class);

        return minOrMax ? min() : max();
    }

    default List<T> list(int size, boolean skip, boolean required, boolean random) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = build(null, new DefaultModelAction(required), skip, random);

            list.add(model);
        }

        return list;
    }

    default Set<T> set(int size, boolean skip, boolean required, boolean random) {

        List<T> list = list(size, skip, required, random);

        return new HashSet<>(list);
    }

    /**
     * @param resourceLocation may not be empty
     * @return @return new Instance of Model created from Resource
     */
    @SuppressWarnings("unchecked")
    default T fromResource(String resourceLocation) {
        Validate.notEmpty(resourceLocation, Constants.MAY_NOT_BE_EMPTY, "resourceLocation");

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

        int size = ModelBuilderUtil.randomCollectionSize();

        return list(size);
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

        int size = ModelBuilderUtil.randomCollectionSize();

        return set(size);
    }

    /**
     * @param size of Collection
     * @return new Set by fixed size with new Instances of Models and init as Min or Max
     */
    default Set<T> set(int size) {

        return new HashSet<>(list(size));
    }

    /**
     * @param rootMethod  from root model may be null
     * @param modelAction may be not null
     * @param skip        should property be skipped
     * @param skip        should value be random or fix
     * @return new model from type T
     */
    @SuppressWarnings("unchecked")
    default T build(Method rootMethod, ModelAction modelAction, boolean skip, boolean random) {
        Validate.notNull(modelAction);

        Class<T> modelType = getGenericType();

        if (!ClassUtil.isComplexType(modelType)) {

            String propertyName = rootMethod != null ? MethodUtil.getPropertyName(rootMethod.getName()) : null;

            return TypeRandomAdapterFactory.getValue(modelType, propertyName, random);

        } else if (modelType.isEnum()) {

            if (random) {

                return CollectionUtil.random(modelType.getEnumConstants());
            } else {

                return modelType.getEnumConstants()[0];
            }

        } else if (ClassUtil.isCollectionType(modelType)) {

            Type propertyType = rootMethod != null ? ClassUtil.getGenericType(rootMethod.getGenericReturnType()) : null;

            return TypeRandomAdapterFactory.getCollection(modelType, propertyType, skip, random, getActualTypeArguments());

        } else if (modelType.isArray()) {

            Class propertyType = modelType.getComponentType();

            return TypeRandomAdapterFactory.getArray(propertyType, skip, random);

        } else if (Class.class.equals(modelType)) {

            return (T) Class.class;
        }

        T model = createModel(modelAction, skip, random);

        TypeRandomAdapterFactory.generateFieldValues(model, modelAction, skip, random);

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
    default T createModel(ModelAction modelAction, boolean skip, boolean random) {

        Class<T> instanceType = getGenericType();

        return ModelBuilderUtil.createModel(instanceType, modelAction, skip, random);
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

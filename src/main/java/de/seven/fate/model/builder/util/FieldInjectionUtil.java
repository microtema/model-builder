package de.seven.fate.model.builder.util;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.annotation.Models;
import de.seven.fate.model.builder.enums.ModelType;
import de.seven.fate.model.builder.enums.ModelsType;
import org.apache.commons.lang3.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.seven.fate.model.builder.util.FieldUtil.doWithFields;
import static de.seven.fate.model.builder.util.FieldUtil.makeAccessible;
import static de.seven.fate.model.builder.util.FieldUtil.setFieldValue;


/**
 * Field Injection Util
 */
public final class FieldInjectionUtil {

    private static final Set<String> INJECTIONS_NAMES = Stream.of("Inject", "Autowired", "Resource").collect(Collectors.toSet());

    private FieldInjectionUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    /**
     * @param obj may not be null
     */
    public static void injectFields(Object obj) {
        Validate.notNull(obj);

        injectInstances(obj);

        injectModel(obj);

        injectModels(obj);
    }

    private static void injectModels(Object obj) {
        assert obj != null;

        doWithFields(obj.getClass(), field -> {

            makeAccessible(field);

            Class<?> genericType = ClassUtil.getGenericType(field.getGenericType());

            ModelsType modelsType = getModelsType(field);

            if ((field.getType().isArray())) {
                genericType = field.getType().getComponentType();
            }

            ModelBuilder<?> modelBuilder = getOrCreateModelBuilder(field);

            Models annotation = field.getAnnotation(Models.class);

            int size = annotation.size();

            Object value = getValue(modelBuilder, modelsType, size);

            if ((field.getType().isArray())) {
                value = Array.newInstance(genericType, ((Collection) value).size());
            }

            setFieldValue(field, obj, value);

        }, field -> field.isAnnotationPresent(Models.class));
    }

    private static ModelsType getModelsType(Field field) {

        Class<?> fieldType = field.getType();

        if ((fieldType.isArray())) {
            return ModelsType.ARRAY;
        }

        if (Set.class.isAssignableFrom(fieldType)) {

            return ModelsType.SET;
        }

        if (List.class.isAssignableFrom(fieldType)) {

            return ModelsType.LIST;
        }

        if (Collection.class.isAssignableFrom(fieldType)) {

            return ModelsType.LIST;
        }

        throw new IllegalArgumentException("Unsupported ModelTypes");
    }

    private static void injectModel(Object obj) {
        assert obj != null;

        doWithFields(obj.getClass(), field -> {

            makeAccessible(field);

            ModelBuilder<?> modelBuilder = getOrCreateModelBuilder(field);

            Model annotation = field.getAnnotation(Model.class);

            ModelType modelType = annotation.type();

            Object value = getValue(modelBuilder, modelType, annotation.resource());
            setFieldValue(field, obj, value);

        }, field -> field.isAnnotationPresent(Model.class));
    }

    private static void injectInstances(Object obj) {
        assert obj != null;

        doWithFields(obj.getClass(), field -> {

            makeAccessible(field);

            Object value = ClassUtil.createInstance(field.getType());

            setFieldValue(field, obj, value);

        }, FieldInjectionUtil::isInjectionField);
    }

    private static Object getValue(ModelBuilder<?> modelBuilder, ModelType modelType, String resource) {
        assert modelBuilder != null;
        assert modelType != null;
        assert resource != null;

        switch (modelType) {
            case MIN:
                return modelBuilder.min();
            case MAX:
                return modelBuilder.max();
            case RANDOM:
                return modelBuilder.random();
            case FIX:
                return modelBuilder.fix();
            case SOURCE:
                return modelBuilder.fromResource(resource);
            default:
                throw new IllegalArgumentException("Unsupported modelType: " + modelType);
        }

    }

    private static Object getValue(ModelBuilder<?> modelBuilder, ModelsType modelsType, int size) {

        switch (modelsType) {
            case LIST:
            case ARRAY:
                return size > -1 ? modelBuilder.list(size) : modelBuilder.list();
            case SET:
                return size > -1 ? modelBuilder.set(size) : modelBuilder.set();
            default:
                throw new IllegalArgumentException("Unsupported modelsType: " + modelsType);
        }
    }

    private static ModelBuilder<?> getOrCreateModelBuilder(Field field) {
        assert field != null;

        Class<?> genericType = field.getType();

        Class[] typeArguments;

        if (Map.class.isAssignableFrom(field.getType())) {

            typeArguments = getActualTypeArguments(field);

            // ModelBuilderFactory has own Cache
            return ModelBuilderFactory.createBuilder(genericType, typeArguments);
        } else {

            genericType = getGenericType(field.getGenericType());

            // ModelBuilderFactory has own Cache
            return ModelBuilderFactory.createBuilder(genericType);
        }
    }

    private static Class[] getActualTypeArguments(Field field) {
        assert field != null;

        Type genericType = field.getGenericType();

        if (!(genericType instanceof ParameterizedType)) {

            return new Class[]{String.class, String.class};
        }

        ParameterizedType parameterizedTypet = (ParameterizedType) genericType;

        Type[] actualTypeArguments = parameterizedTypet.getActualTypeArguments();

        if (actualTypeArguments.length == 0) {

            return new Class[]{String.class, String.class};
        }

        Class<?> keyGenericType = (Class<?>) actualTypeArguments[0];
        Class<?> valueGenericType = (Class<?>) actualTypeArguments[1];

        return new Class[]{keyGenericType, valueGenericType};
    }

    private static Class<?> getGenericType(Type propertyType) {

        Class<?> modelType = ClassUtil.getGenericType(propertyType);

        return modelType == null ? (Class<?>) propertyType : modelType;
    }

    private static boolean isInjectionField(Field field) {
        assert field != null;

        Annotation[] annotations = field.getAnnotations();

        return Stream.of(annotations).map(it -> it.annotationType().getSimpleName()).anyMatch(INJECTIONS_NAMES::contains);
    }
}
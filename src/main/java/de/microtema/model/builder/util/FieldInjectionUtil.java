package de.microtema.model.builder.util;

import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.annotation.Models;
import de.microtema.model.builder.enums.ModelType;
import de.microtema.model.builder.enums.ModelsType;
import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.ModelBuilderFactory;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@UtilityClass
public class FieldInjectionUtil {

    private static final Set<String> INJECTIONS_NAMES = Stream.of("Inject", "Autowired", "Resource").collect(Collectors.toSet());

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

        FieldUtil.doWithFields(obj.getClass(), field -> {

            Class<?> genericType = ClassUtil.getGenericType(field.getGenericType());

            ModelsType modelsType = getModelsType(field);

            if ((field.getType().isArray())) {
                genericType = field.getType().getComponentType();
            }

            ModelBuilder<?> modelBuilder = getOrCreateModelBuilder(field);

            Models modelsAnnotation = field.getAnnotation(Models.class);

            Object value = getValue(modelBuilder, modelsType, modelsAnnotation);

            if ((field.getType().isArray())) {
                value = Array.newInstance(genericType, ((Collection) value).size());
            }

            FieldUtil.setFieldValue(field, obj, value);

        }, field -> field.isAnnotationPresent(Models.class));
    }

    private static ModelsType getModelsType(Field field) {
        assert field != null;

        Class<?> fieldType = field.getType();

        if ((fieldType.isArray())) {
            return ModelsType.ARRAY;
        }

        if (Set.class.isAssignableFrom(fieldType)) {

            return ModelsType.SET;
        }

        if (Collection.class.isAssignableFrom(fieldType)) {

            return ModelsType.LIST;
        }

        throw new IllegalArgumentException("Unsupported ModelTypes");
    }

    private static void injectModel(Object obj) {
        assert obj != null;

        FieldUtil.doWithFields(obj.getClass(), field -> {

            ModelBuilder<?> modelBuilder = getOrCreateModelBuilder(field);

            Model annotation = field.getAnnotation(Model.class);

            ModelType modelType = annotation.type();

            Object value = getValue(modelBuilder, modelType, annotation.resource());
            FieldUtil.setFieldValue(field, obj, value);

        }, field -> field.isAnnotationPresent(Model.class));
    }

    private static void injectInstances(Object obj) {
        assert obj != null;

        FieldUtil.doWithFields(obj.getClass(), field -> {

            Object fieldValue = FieldUtil.getFieldValue(field, obj);

            if (Objects.isNull(fieldValue)) {

                fieldValue = ClassUtil.createInstance(field.getType());
                FieldUtil.setFieldValue(field, obj, fieldValue);
            }

            if (fieldValue instanceof ModelBuilder) {

                ModelBuilderFactory.registerModelBuilder((ModelBuilder<?>) fieldValue);
            }

        }, FieldInjectionUtil::isInjectionField);
    }

    private static Object getValue(ModelBuilder<?> modelBuilder, ModelType modelType, String resource) {
        assert modelBuilder != null;
        assert modelType != null;

        switch (modelType) {
            case MIN:
                return modelBuilder.min();
            case MAX:
                return modelBuilder.max();
            case MIX:
                return modelBuilder.mix();
            case FIX:
                return modelBuilder.fix();
            case SOURCE:
                return modelBuilder.fromResource(resource);
            default:
                throw new IllegalArgumentException("Unsupported modelType: " + modelType);
        }

    }

    private static Object getValue(ModelBuilder<?> modelBuilder, ModelsType modelsType, Models models) {
        assert modelBuilder != null;
        assert modelsType != null;

        int size = models.size() == -1 ? ModelBuilderUtil.randomCollectionSize() : models.size();
        ModelType modelType = models.type();
        boolean required = (modelType == ModelType.MAX);

        switch (modelsType) {
            case LIST:
            case ARRAY:
                return modelBuilder.list(size, false, required, true);
            case SET:
                return modelBuilder.set(size, false, required, true);
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

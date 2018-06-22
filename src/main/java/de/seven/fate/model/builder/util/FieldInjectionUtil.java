package de.seven.fate.model.builder.util;

import de.seven.fate.commons.utils.ClassUtil;
import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.annotation.Models;
import de.seven.fate.model.builder.enums.ModelType;
import de.seven.fate.model.builder.enums.ModelsType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.seven.fate.commons.utils.FieldUtil.doWithFields;
import static de.seven.fate.commons.utils.FieldUtil.makeAccessible;
import static de.seven.fate.commons.utils.FieldUtil.setFieldValue;

/**
 * Field Injection Util
 */
public final class FieldInjectionUtil {

    private static final Map<Class, ModelBuilder> BUILDERS = new HashMap<>();
    private static final Set<String> INJECTIONS_NAMES = Stream.of("Inject", "Autowired").collect(Collectors.toSet());

    private FieldInjectionUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    /**
     * @param obj may not be null
     */
    public static void injectFields(Object obj) {

        doWithFields(obj.getClass(), field -> {

            makeAccessible(field);

            Object value = ClassUtil.createInstance(field.getType());

            registerModelBuilder(value);

            setFieldValue(field, obj, value);

        }, FieldInjectionUtil::isInjectionField);

        doWithFields(obj.getClass(), field -> {

            makeAccessible(field);

            ModelBuilder<?> modelBuilder = getOrCreateModelBuilder(field.getType());

            Model annotation = field.getAnnotation(Model.class);

            ModelType modelType = annotation.type();

            Object value = getValue(modelBuilder, modelType, annotation.resource());
            setFieldValue(field, obj, value);

        }, field -> field.isAnnotationPresent(Model.class));

        doWithFields(obj.getClass(), field -> {

            makeAccessible(field);

            Class<?> genericType = ClassUtil.getGenericType(field.getGenericType());

            ModelBuilder<?> modelBuilder = getOrCreateModelBuilder(genericType);

            Models annotation = field.getAnnotation(Models.class);

            ModelsType modelsType = annotation.type();
            int size = annotation.size();

            Object value = getValue(modelBuilder, modelsType, size);
            setFieldValue(field, obj, value);

        }, field -> field.isAnnotationPresent(Models.class));
    }

    private static void registerModelBuilder(Object value) {
        assert value != null;

        if (!(value instanceof ModelBuilder)) {
            return;
        }

        ModelBuilder modelBuilder = (ModelBuilder) value;

        BUILDERS.put(modelBuilder.getGenericType(), modelBuilder);
    }

    private static Object getValue(ModelBuilder<?> modelBuilder, ModelType modelType, String resource) {

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
                return size > -1 ? modelBuilder.list(size) : modelBuilder.list();
            case SET:
                return size > -1 ? modelBuilder.set(size) : modelBuilder.set();
            default:
                throw new IllegalArgumentException("Unsupported modelsType: " + modelsType);
        }
    }

    private static ModelBuilder<?> getOrCreateModelBuilder(Class<?> modelType) {
        assert modelType != null;

        if (BUILDERS.containsKey(modelType)) {
            return BUILDERS.get(modelType);
        }

        // ModelBuilderFactory has own Cache
        return ModelBuilderFactory.createBuilder(modelType);
    }

    private static boolean isInjectionField(Field field) {
        assert field != null;

        Annotation[] annotations = field.getAnnotations();

        return Stream.of(annotations).map(it -> it.annotationType().getSimpleName()).anyMatch(INJECTIONS_NAMES::contains);
    }
}
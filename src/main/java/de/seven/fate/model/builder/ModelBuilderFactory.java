package de.seven.fate.model.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ModelBuilderFactory {

    private static final Map<Class, ModelBuilder> BUILDERS = new HashMap<>();

    private ModelBuilderFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <T> ModelBuilder<T> createBuilder(final Class<T> modelType) {

        return BUILDERS.computeIfAbsent(modelType, it -> new AbstractModelBuilder<T>() {

            @Override
            public Class<T> getGenericType() {
                return it;
            }
        });
    }

    public static <T> T min(final Class<T> modelType) {

        return createBuilder(modelType).min();
    }

    public static <T> T max(final Class<T> modelType) {

        return createBuilder(modelType).max();
    }

    public static <T> T random(final Class<T> modelType) {

        return createBuilder(modelType).random();
    }

    public static <T> T fromResource(final Class<T> modelType, String fromResource) {

        return createBuilder(modelType).fromResource(fromResource);
    }

    public static <T> List<T> list(final Class<T> modelType) {

        return createBuilder(modelType).list();
    }

    public static <T> Set<T> set(final Class<T> modelType) {

        return createBuilder(modelType).set();
    }
}

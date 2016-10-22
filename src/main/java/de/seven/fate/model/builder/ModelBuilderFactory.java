package de.seven.fate.model.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ModelBuilderFactory {

    private final static Map<Class, ModelBuilder> BUILDERS = new HashMap<>();

    private ModelBuilderFactory() {
    }

    public static <T> ModelBuilder<T> createBuilder(final Class<T> modelType) {

        ModelBuilder<T> modelBuilder = BUILDERS.get(modelType);

        if (modelBuilder == null) {

            modelBuilder = new AbstractModelBuilder<T>() {

                @Override
                public Class<T> getGenericType() {
                    return modelType;
                }
            };

            BUILDERS.put(modelType, modelBuilder);
        }

        return modelBuilder;
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

    public static <T> List<T> list(final Class<T> modelType) {

        return createBuilder(modelType).list();
    }

    public static <T> Set<T> set(final Class<T> modelType) {

        return createBuilder(modelType).set();
    }
}

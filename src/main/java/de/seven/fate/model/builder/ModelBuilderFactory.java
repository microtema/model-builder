package de.seven.fate.model.builder;

import java.util.HashMap;
import java.util.Map;

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
}

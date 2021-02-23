package de.microtema.model.builder;

import de.microtema.model.builder.constants.Constants;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@UtilityClass
public final class ModelBuilderFactory {

    private static final Set<ModelBuilder> BUILDERS = new HashSet<>();

    public static <T> void registerModelBuilder(ModelBuilder<T> modelBuilder) {
        Validate.notNull(modelBuilder, Constants.MAY_NOT_BE_NULL, "modelBuilder");

        Optional<ModelBuilder> optionalModelBuilder = findModelBuilder(modelBuilder.getGenericType(), modelBuilder.getActualTypeArguments());

        if (optionalModelBuilder.isPresent()) {

            return;
        }

        BUILDERS.add(modelBuilder);
    }

    @SuppressWarnings("unchecked")
    public static <T> ModelBuilder<T> createBuilder(final Class<T> modelType) {

        if (Map.class.isAssignableFrom(modelType)) {

            return createBuilder(modelType, new Class[]{String.class, String.class});
        }

        return createBuilder(modelType, new Class[0]);
    }

    @SuppressWarnings("unchecked")
    public static <T> ModelBuilder<T> createBuilder(final Class<T> modelType, final Class[] actualTypeArguments) {

        Optional<ModelBuilder> optionalModelBuilder = findModelBuilder(modelType, actualTypeArguments);

        if (optionalModelBuilder.isPresent()) {

            return optionalModelBuilder.get();
        }

        ModelBuilder<T> modelBuilder = new ModelBuilder<T>() {

            @Override
            public Class<T> getGenericType() {
                return modelType;
            }

            @Override
            public Class[] getActualTypeArguments() {
                return actualTypeArguments;
            }
        };

        registerModelBuilder(modelBuilder);

        return modelBuilder;
    }

    private static <T> Optional<ModelBuilder> findModelBuilder(Class<T> modelType, Class[] actualTypeArguments) {

        return BUILDERS.stream()
                .filter(it -> (it.getGenericType() == modelType) && Arrays.equals(it.getActualTypeArguments(), actualTypeArguments))
                .findAny();
    }

    public static <T> T min(final Class<T> modelType) {

        return createBuilder(modelType).min();
    }

    public static <T> T max(final Class<T> modelType) {

        return createBuilder(modelType).max();
    }

    public static <T> T mix(final Class<T> modelType) {

        return createBuilder(modelType).mix();
    }

    public static <T> T fix(final Class<T> modelType) {

        return createBuilder(modelType).fix();
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

    public static <T> List<T> list(final Class<T> modelType, int size) {

        return createBuilder(modelType).list(size);
    }

    public static <T> Set<T> set(final Class<T> modelType, int size) {

        return createBuilder(modelType).set(size);
    }
}

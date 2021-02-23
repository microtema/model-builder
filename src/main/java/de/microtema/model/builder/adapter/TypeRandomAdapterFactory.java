package de.microtema.model.builder.adapter;

import de.microtema.model.builder.adapter.binary.BinaryTypeRandomAdapter;
import de.microtema.model.builder.adapter.bool.BooleanRandomAdapter;
import de.microtema.model.builder.adapter.chars.CharRandomAdapter;
import de.microtema.model.builder.adapter.date.DateRandomAdapter;
import de.microtema.model.builder.adapter.date.LocalDateRandomAdapter;
import de.microtema.model.builder.adapter.date.LocalDateTimeRandomAdapter;
import de.microtema.model.builder.adapter.decimal.BigDecimalRandomAdapter;
import de.microtema.model.builder.adapter.doublev.DoubleTypeRandomAdapter;
import de.microtema.model.builder.adapter.floats.FloatTypeRandomAdapter;
import de.microtema.model.builder.adapter.integer.IntegerRandomAdapter;
import de.microtema.model.builder.adapter.longv.LongRandomAdapter;
import de.microtema.model.builder.adapter.map.MapTypeRandomAdapter;
import de.microtema.model.builder.adapter.string.StringRandomAdapter;
import de.microtema.model.builder.adapter.url.UrlRandomAdapter;
import de.microtema.model.builder.ModelAction;
import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.ModelBuilderFactory;
import de.microtema.model.builder.util.ClassUtil;
import de.microtema.model.builder.util.MethodUtil;
import de.microtema.model.builder.util.ModelBuilderUtil;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@SuppressWarnings("ALL")
@UtilityClass
public final class TypeRandomAdapterFactory {

    private static final Map<Class<?>, AbstractTypeRandomAdapter<?>> ADAPTERS = Collections.synchronizedMap(new HashMap<Class<?>, AbstractTypeRandomAdapter<?>>());
    private static final Map<Class, Class> BOX_TYPE = new HashMap<>();

    static {
        BOX_TYPE.put(int.class, Integer.class);
        BOX_TYPE.put(double.class, Double.class);
        BOX_TYPE.put(long.class, Long.class);
        BOX_TYPE.put(boolean.class, Boolean.class);
        BOX_TYPE.put(float.class, Float.class);
        BOX_TYPE.put(byte.class, Byte.class);
        BOX_TYPE.put(char.class, Character.class);
    }

    static {
        registerAdapter(ClassUtil.createInstance(StringRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(UrlRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(BooleanRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(DateRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(LocalDateRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(LocalDateTimeRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(IntegerRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(BigDecimalRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(DoubleTypeRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(LongRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(FloatTypeRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(BinaryTypeRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(MapTypeRandomAdapter.class));
        registerAdapter(ClassUtil.createInstance(CharRandomAdapter.class));
    }

    public static <T> AbstractTypeRandomAdapter<T> lookupAdapter(Class<T> valueType) {
        Validate.notNull(valueType);

        return (AbstractTypeRandomAdapter<T>) ADAPTERS.get(valueType);
    }

    public static <T> T getRandomValue(Class<T> propertyType) {

        return getRandomValue(propertyType, null);
    }

    public static <T> T getFixValue(Class<T> propertyType) {

        return getFixValue(propertyType, null);
    }

    public static <T> T getFixValue(Class<T> propertyType, String propertyName) {
        Validate.notNull(propertyType);

        if (ADAPTERS.containsKey(propertyType)) {
            return (T) ADAPTERS.get(propertyType).fixValue(propertyName);
        }

        return null;
    }

    public static <T> T getRandomValue(Class<T> propertyType, String propertyName) {
        Validate.notNull(propertyType);

        if (ADAPTERS.containsKey(propertyType)) {
            return (T) ADAPTERS.get(propertyType).randomValue(propertyName);
        }

        return null;
    }

    public static <T> T getValue(Class<T> propertyType, String propertyName, boolean random) {
        Validate.notNull(propertyType);

        if (!ADAPTERS.containsKey(propertyType)) {

          return null;
        }

        AbstractTypeRandomAdapter<?> abstractTypeRandomAdapter = ADAPTERS.get(propertyType);

        if(random) {

            return (T) abstractTypeRandomAdapter.randomValue(propertyName);
        }

        return (T) abstractTypeRandomAdapter.fixValue(propertyName);
    }

    public static Object[] getParameters(Class<?>[] parameterTypes) {

        Object[] args = new Object[parameterTypes.length];

        for (int index = 0; index < args.length; index++) {
            args[index] = getRandomValue(parameterTypes[index]);
        }

        return args;
    }

    public static <T> T getCollection(Class<T> modelType, Type propertyType, boolean skip, boolean random, Class[] actualTypeArguments) {

        int size = ModelBuilderUtil.randomCollectionSize();

        if(random){
            size = 1;
        }

        if (List.class.isAssignableFrom(modelType)) {

            Class<?> genericType = getGenericType(propertyType);

            ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(genericType);

            return (T) builder.list(size, skip, false, random);

        } else if (Set.class.isAssignableFrom(modelType)) {

            Class<?> genericType = getGenericType(propertyType);

            ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(genericType);

            return (T) builder.set(size, skip, false, random);

        } else if (Map.class.isAssignableFrom(modelType)) {

            Class<?> keyGenericType = actualTypeArguments[0];
            Class<?> valueGenericType = actualTypeArguments[1];

            ModelBuilder<?> keyBuilder = ModelBuilderFactory.createBuilder(keyGenericType);
            ModelBuilder<?> valueBuilder = ModelBuilderFactory.createBuilder(valueGenericType);

            Set set = keyBuilder.set(size, skip, false, random);

            return (T) set.stream().collect(Collectors.toMap(it -> it, it -> valueBuilder.min()));
        }

        return null;
    }

    public static <T> T getArray(Class<?> modelType, boolean skip, boolean random) {

        int size = ModelBuilderUtil.randomCollectionSize();

        if(random){
            size = 1;
        }

        ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(modelType);

        List list = builder.list(size, skip, false, random);

        Object[] array = (Object[]) Array.newInstance(modelType, list.size());

        return (T) list.toArray(array);
    }

    public static <T> void generateFieldValues(T model, ModelAction createAction, boolean skip, boolean random) {
        Validate.notNull(model);
        Validate.notNull(createAction);

        Class<?> modelClass = model.getClass();
        Set<String> properties = MethodUtil.getProperties(modelClass);

        for (String property : properties) {

            Method method = MethodUtil.getGetterMethod(modelClass, property);

            Class<?> fieldGenericType = ClassUtil.getGenericType(method.getGenericReturnType());

            boolean overFlow = fieldGenericType == modelClass;

            if (overFlow && skip) {
                continue;
            }

            Object propertyValue = createAction.execute(method, overFlow, random);

            Optional.ofNullable(propertyValue).ifPresent(it -> MethodUtil.setProperties(model, property, it));
        }
    }

    private static void registerAdapter(AbstractTypeRandomAdapter<?> valueAdapter) {
        assert valueAdapter != null;

        ADAPTERS.put(valueAdapter.getValueType(), valueAdapter);

        for (Map.Entry<Class, Class> entry : BOX_TYPE.entrySet()) {
            if (entry.getValue() == valueAdapter.getValueType()) {
                ADAPTERS.put(entry.getKey(), valueAdapter);
            }
        }
    }

    private static Class<?> getGenericType(Type propertyType) {

        Class<?> modelType = ClassUtil.getGenericType(propertyType);

        return modelType == null ? (Class<?>) propertyType : modelType;
    }
}

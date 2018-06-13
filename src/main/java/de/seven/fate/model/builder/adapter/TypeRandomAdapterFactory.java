package de.seven.fate.model.builder.adapter;

import de.seven.fate.commons.utils.ClassUtil;
import de.seven.fate.commons.utils.FieldUtil;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.model.builder.ModelAction;
import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import de.seven.fate.model.builder.adapter.binary.BinaryTypeRandomAdapter;
import de.seven.fate.model.builder.adapter.bool.BooleanRandomAdapter;
import de.seven.fate.model.builder.adapter.date.DateRandomAdapter;
import de.seven.fate.model.builder.adapter.date.LocalDateRandomAdapter;
import de.seven.fate.model.builder.adapter.date.LocalDateTimeRandomAdapter;
import de.seven.fate.model.builder.adapter.decimal.BigDecimalRandomAdapter;
import de.seven.fate.model.builder.adapter.doublev.DoubleTypeRandomAdapter;
import de.seven.fate.model.builder.adapter.floats.FloatTypeRandomAdapter;
import de.seven.fate.model.builder.adapter.integer.IntegerRandomAdapter;
import de.seven.fate.model.builder.adapter.longv.LongRandomAdapter;
import de.seven.fate.model.builder.adapter.map.MapTypeRandomAdapter;
import de.seven.fate.model.builder.adapter.string.StringRandomAdapter;
import de.seven.fate.model.builder.adapter.url.UrlRandomAdapter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@SuppressWarnings("ALL")
public final class TypeRandomAdapterFactory {

    private static final Logger LOGGER = Logger.getLogger(TypeRandomAdapterFactory.class.getName());

    private static final Map<Class<?>, AbstractTypeRandomAdapter<?>> ADAPTERS = Collections.synchronizedMap(new HashMap<Class<?>, AbstractTypeRandomAdapter<?>>());
    private static final Map<Class, Class> BOX_TYPE = new HashMap<>();

    static {
        BOX_TYPE.put(int.class, Integer.class);
        BOX_TYPE.put(double.class, Double.class);
        BOX_TYPE.put(long.class, Long.class);
        BOX_TYPE.put(boolean.class, Boolean.class);
        BOX_TYPE.put(float.class, Float.class);
        BOX_TYPE.put(byte.class, Byte.class);
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
    }

    private TypeRandomAdapterFactory() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }


    public static <T> AbstractTypeRandomAdapter<T> lookupAdapter(Class<T> valueType) {
        assert valueType != null;

        return (AbstractTypeRandomAdapter<T>) ADAPTERS.get(valueType);
    }


    public static <T> T getRandomValue(Class<T> propertyType) {

        return getRandomValue(propertyType, null);
    }

    public static <T> T getRandomValue(Class<T> propertyType, String propertyName) {
        assert propertyType != null;

        if (ADAPTERS.containsKey(propertyType)) {
            return (T) ADAPTERS.get(propertyType).randomValue(propertyName);
        }

        return null;
    }

    public static Object[] getRandomParameters(Class<?>[] parameterTypes) {

        Object[] args = new Object[parameterTypes.length];

        for (int index = 0; index < args.length; index++) {
            args[index] = getRandomValue(parameterTypes[index]);
        }

        return args;
    }

    public static <T> T getCollection(Class<T> modelType, Type propertyType, boolean skip) {

        if (List.class.isAssignableFrom(modelType)) {

            Class<?> genericType = ClassUtil.getGenericType(propertyType);

            ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(genericType);

            return (T) ((AbstractModelBuilder) builder).list(skip);

        } else if (Set.class.isAssignableFrom(modelType)) {

            Class<?> genericType = ClassUtil.getGenericType(propertyType);

            ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(genericType);

            return (T) ((AbstractModelBuilder) builder).set(skip);

        } else if (Map.class.isAssignableFrom(modelType)) {

            Class<?> keyGenericType = propertyType != null ? ClassUtil.getGenericType(propertyType) : String.class;
            Class<?> valueGenericType = propertyType != null ? ClassUtil.getGenericType(propertyType, 1) : String.class;

            ModelBuilder<?> keyBuilder = ModelBuilderFactory.createBuilder(keyGenericType);
            ModelBuilder<?> valueBuilder = ModelBuilderFactory.createBuilder(valueGenericType);

            Set set = ((AbstractModelBuilder) keyBuilder).set(skip);

            return (T) set.stream().collect(Collectors.toMap(it -> it, it -> ((AbstractModelBuilder) valueBuilder).min()));
        }

        return null;
    }

    public static <T> T getArray(Class<?> modelType, boolean skip) {

        ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(modelType);

        List list = ((AbstractModelBuilder) builder).list(skip);

        return (T) list.toArray();
    }

    public static <T> void generateRandomFieldValues(T model, ModelAction createAction, boolean skip) {
        assert model != null;
        assert createAction != null;

        Class<?> modelClass = model.getClass();
        List<Field> fields = FieldUtil.getPropertyFields(modelClass);

        for (Field field : fields) {

            String fieldName = field.getName();
            Class<?> fieldGenericType = ClassUtil.getGenericType(field.getGenericType());

            boolean overFlow = fieldGenericType == modelClass;

            if (overFlow && skip) {
                continue;
            }

            Object propertyValue = createAction.execute(field, overFlow);

            if (propertyValue != null) {

                try {
                    BeanUtils.setProperty(model, fieldName, propertyValue);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.log(Level.WARNING, "Unable to set property: " + fieldName + " on model: " + model, e);
                }
            }
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

}

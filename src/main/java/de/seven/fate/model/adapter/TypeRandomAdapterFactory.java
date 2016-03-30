package de.seven.fate.model.adapter;

import de.seven.fate.model.adapter.bool.BooleanRandomAdapter;
import de.seven.fate.model.adapter.date.DateRandomAdapter;
import de.seven.fate.model.adapter.decimal.BigDecimalRandomAdapter;
import de.seven.fate.model.adapter.doublev.DoubleTypeRandomAdapter;
import de.seven.fate.model.adapter.integer.IntegerRandomAdapter;
import de.seven.fate.model.adapter.longv.LongRandomAdapter;
import de.seven.fate.model.adapter.string.StringRandomAdapter;
import de.seven.fate.model.util.ClassUtil;
import de.seven.fate.model.util.CollectionUtil;
import de.seven.fate.model.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mario on 24.03.2016.
 */
@SuppressWarnings("ALL")
public final class TypeRandomAdapterFactory {

    private static final Logger LOGGER = Logger.getLogger(TypeRandomAdapterFactory.class.getName());

    private static final Map<Class<?>, AbstractTypeRandomAdapter<?>> ADAPTERS = Collections.synchronizedMap(new HashMap<Class<?>, AbstractTypeRandomAdapter<?>>());

    private static final AbstractTypeRandomAdapter DEFAULT_ADAPTER = new DefaultTypeRandomAdapter();

    static {
        registerAdapter(new StringRandomAdapter());
        registerAdapter(new BooleanRandomAdapter());
        registerAdapter(new IntegerRandomAdapter());
        registerAdapter(new DateRandomAdapter());
        registerAdapter(new BigDecimalRandomAdapter());
        registerAdapter(new DoubleTypeRandomAdapter());
        registerAdapter(new LongRandomAdapter());
    }

    private TypeRandomAdapterFactory() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static void initPropertiesWithRandomValues(Object model) {
        assert model != null;

        List<String> propertyNames = ClassUtil.getPropertyNames(model.getClass());

        List<Field> fields = ClassUtil.getAllFields(model.getClass());

        for (Field field : fields) {
            setProperty(field, model);
        }
    }

    public static void registerAdapter(AbstractTypeRandomAdapter<?> valueAdapter) {
        assert valueAdapter != null;

        ADAPTERS.put(valueAdapter.getValueType(), valueAdapter);
    }

    public static void unregisterAdapter(Class<?> valueType) {
        assert valueType != null;

        ADAPTERS.remove(valueType);
    }

    public static <T> void unregisterAdapters() {

        ADAPTERS.clear();
    }

    public static <T> AbstractTypeRandomAdapter<T> lookupAdapter(Class<T> valueType) {
        assert valueType != null;

        return (AbstractTypeRandomAdapter<T>) ADAPTERS.get(valueType);
    }

    private static void setProperty(Field field, Object model) {
        assert field != null;
        assert model != null;

        String propertyName = field.getName();

        Object propertyValue = getPropertyValue(field);

        if (propertyValue == null) {
            return;
        }

        try {
            BeanUtils.setProperty(model, propertyName, propertyValue);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Unable to set property: " + propertyName + " on model: " + model, e);
        }
    }

    private static Object getPropertyValue(Field field) {
        assert field != null;

        String propertyName = field.getName();
        Class<?> fieldType = field.getType();
        TypeRandomAdapter<?> typeRandomAdapter = getRandomPropertyValueAdapter(fieldType);

        if (typeRandomAdapter != null) {
            return typeRandomAdapter.randomValue(propertyName);
        }

        if (fieldType.isEnum()) {
            return CollectionUtil.random(fieldType.getEnumConstants());
        }

        if (List.class.isAssignableFrom(fieldType)) {

            List list = new ArrayList();

            Class<?> genericType = ClassUtil.getActualTypeArgument(field.getGenericType());

            TypeRandomAdapter<?> randomAdapter = getRandomPropertyValueAdapter(genericType);

            if (randomAdapter == null) {
                return null;
            }

            fillCollection(list, randomAdapter, propertyName);

            return list;
        }

        return null;
    }


    private static TypeRandomAdapter<?> getRandomPropertyValueAdapter(Class<?> propertyType) {
        assert propertyType != null;

        if (ADAPTERS.containsKey(propertyType)) {
            return ADAPTERS.get(propertyType);
        }

        return null;
    }

    private static <E> void fillCollection(Collection<E> collection, TypeRandomAdapter<E> adapter, String propertyName) {

        int randomSize = NumberUtil.random(1, 10);
        int count = 0;

        while (count++ < randomSize) {
            collection.add(adapter.randomValue(propertyName));
        }
    }
}

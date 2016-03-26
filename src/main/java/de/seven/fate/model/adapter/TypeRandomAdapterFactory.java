package de.seven.fate.model.adapter;

import de.seven.fate.model.adapter.bool.BooleanRandomAdapter;
import de.seven.fate.model.adapter.date.DateRandomAdapter;
import de.seven.fate.model.adapter.decimal.BigDecimalRandomAdapter;
import de.seven.fate.model.adapter.doublev.DoubleTypeRandomAdapter;
import de.seven.fate.model.adapter.integer.IntegerRandomAdapter;
import de.seven.fate.model.adapter.longv.LongRandomAdapter;
import de.seven.fate.model.adapter.string.StringRandomAdapter;
import de.seven.fate.model.util.ClassUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        for (String propertyName : propertyNames) {
            setProperty(model, propertyName);
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

    private static void setProperty(Object model, String propertyName) {
        assert model != null;
        assert propertyName != null;

        Class<?> modelType = model.getClass();

        Class<?> propertyType = ClassUtil.getPropertyType(propertyName, modelType);

        TypeRandomAdapter<?> typeRandomAdapter = getRandomPropertyValueAdapter(propertyType);

        Object propertyValue = typeRandomAdapter.randomValue(propertyName);

        if (propertyValue == null) {
            return;
        }

        try {

            BeanUtils.setProperty(model, propertyName, propertyValue);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Unable to set property: " + propertyName + " on model: " + model, e);
        }
    }

    private static TypeRandomAdapter<?> getRandomPropertyValueAdapter(Class<?> propertyType) {
        assert propertyType != null;

        if (ADAPTERS.containsKey(propertyType)) {
            return ADAPTERS.get(propertyType);
        }

        return DEFAULT_ADAPTER;
    }
}

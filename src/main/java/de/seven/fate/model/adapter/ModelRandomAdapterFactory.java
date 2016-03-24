package de.seven.fate.model.adapter;

import de.seven.fate.model.adapter.bool.BooleanRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.date.DateRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.decimal.BigDecimalRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.integer.IntegerRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.longv.LongRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.string.StringRandomPropertyValueAdapter;
import de.seven.fate.model.util.ClassUtil;
import de.seven.fate.model.util.CollectionUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario on 24.03.2016.
 */
public final class ModelRandomAdapterFactory {

    private static final Map<Class<?>, RandomPropertyValueAdapter<?>> MAP = new HashMap<>();
    public static final RandomPropertyValueAdapter DEFAULT_ADAPTER = new NullRandomAdapter();

    static {
        registerRandomAdapter(new StringRandomPropertyValueAdapter());
        registerRandomAdapter(new BooleanRandomPropertyValueAdapter());
        registerRandomAdapter(new IntegerRandomPropertyValueAdapter());
        registerRandomAdapter(new DateRandomPropertyValueAdapter());
        registerRandomAdapter(new BigDecimalRandomPropertyValueAdapter());
        registerRandomAdapter(new LongRandomPropertyValueAdapter());
    }

    private ModelRandomAdapterFactory() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static void initPropertiesWithRandomValues(Object model) {
        assert model != null;

        List<String> propertyNames = ClassUtil.getPropertyNames(model.getClass());

        for (String propertyName : propertyNames) {
            setProperty(model, propertyName);
        }
    }

    public static void registerRandomAdapter(RandomPropertyValueAdapter<?> valueAdapter) {
        assert valueAdapter != null;

        MAP.put(valueAdapter.getValueType(), valueAdapter);
    }

    private static void setProperty(Object model, String propertyName) {
        assert model != null;
        assert propertyName != null;

        Class<?> modelType = model.getClass();

        Class<?> propertyType = ClassUtil.getPropertyType(propertyName, modelType);

        Object propertyValue = getRandomPropertyValueAdapter(propertyType).randomValue(propertyName, modelType);

        try {
            BeanUtils.setProperty(model, propertyName, propertyValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static RandomPropertyValueAdapter<?> getRandomPropertyValueAdapter(Class<?> propertyType) {
        assert propertyType != null;

        if (MAP.containsKey(propertyType)) {
            return MAP.get(propertyType);
        }

        return DEFAULT_ADAPTER;
    }
}

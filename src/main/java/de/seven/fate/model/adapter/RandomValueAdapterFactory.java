package de.seven.fate.model.adapter;

import de.seven.fate.model.adapter.bool.BooleanPropertyRandomValueAdapter;
import de.seven.fate.model.adapter.date.DatePropertyRandomValueAdapter;
import de.seven.fate.model.adapter.decimal.BigDecimalPropertyRandomValueAdapter;
import de.seven.fate.model.adapter.integer.IntegerPropertyRandomValueAdapter;
import de.seven.fate.model.adapter.longv.LongPropertyRandomValueAdapter;
import de.seven.fate.model.adapter.string.StringPropertyRandomValueAdapter;
import de.seven.fate.model.util.ClassUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario on 24.03.2016.
 */
public final class RandomValueAdapterFactory {

    private static final Map<Class<?>, RandomPropertyValueAdapter<?>> MAP = new HashMap<>();
    private static final RandomPropertyValueAdapter DEFAULT_ADAPTER = new DefaultRandomValueAdapter();

    static {
        registerRandomAdapter(new StringPropertyRandomValueAdapter());
        registerRandomAdapter(new BooleanPropertyRandomValueAdapter());
        registerRandomAdapter(new IntegerPropertyRandomValueAdapter());
        registerRandomAdapter(new DatePropertyRandomValueAdapter());
        registerRandomAdapter(new BigDecimalPropertyRandomValueAdapter());
        registerRandomAdapter(new LongPropertyRandomValueAdapter());
    }

    private RandomValueAdapterFactory() {
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

        synchronized (MAP) {
            MAP.put(valueAdapter.getValueType(), valueAdapter);
        }
    }

    private static void setProperty(Object model, String propertyName) {
        assert model != null;
        assert propertyName != null;

        Class<?> modelType = model.getClass();

        Class<?> propertyType = ClassUtil.getPropertyType(propertyName, modelType);

        RandomPropertyValueAdapter<?> randomPropertyValueAdapter = getRandomPropertyValueAdapter(propertyType);

        Object propertyValue = randomPropertyValueAdapter.randomValue(propertyName, modelType);

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

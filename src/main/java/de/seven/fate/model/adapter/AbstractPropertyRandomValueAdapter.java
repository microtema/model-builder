package de.seven.fate.model.adapter;

import de.seven.fate.model.util.ClassUtil;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPropertyRandomValueAdapter<T> implements RandomPropertyValueAdapter<T> {

    private final Map<String, RandomValueAdapter<T>> MAP = new HashMap<>();

    public void registerRandomAdapter(RandomValueAdapter<T>... valueAdapters) {

        for (RandomValueAdapter<T> valueAdapter : valueAdapters) {
            MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
        }
    }


    @Override
    public T randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return randomValueImpl(propertyName, objectType);
    }

    protected abstract T randomValueImpl(String propertyName, Class<?> objectType);

    public Class<?> getValueType() {

        return ClassUtil.getGenericType(getClass());
    }
}
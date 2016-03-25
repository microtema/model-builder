package de.seven.fate.model.adapter;

import de.seven.fate.model.util.ClassUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTypeRandomAdapter<T> implements TypeRandomAdapter<T> {

    private final Map<String, PropertyRandomAdapter<T>> MAP = Collections.synchronizedMap( new HashMap<String, PropertyRandomAdapter<T>>());

    public void registerPropertyRandomAdapter(PropertyRandomAdapter<T>... valueAdapters) {

        for (PropertyRandomAdapter<T> valueAdapter : valueAdapters) {
            MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
        }
    }


    @Override
    public T randomValue(String propertyName) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return randomValueDefault(propertyName);
    }

    protected abstract T randomValueDefault(String propertyName);

    public Class<?> getValueType() {

        return ClassUtil.getGenericType(getClass());
    }

    public Set<String> getAdapterNames() {

        return MAP.keySet();
    }
}
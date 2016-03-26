package de.seven.fate.model.adapter;

import de.seven.fate.model.util.ClassUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTypeRandomAdapter<T> implements TypeRandomAdapter<T> {

    private final Map<String, PropertyRandomAdapter<T>> ADAPTERS = Collections.synchronizedMap(new HashMap<String, PropertyRandomAdapter<T>>());

    public void registerPropertyAdapter(PropertyRandomAdapter<T>... valueAdapters) {

        for (PropertyRandomAdapter<T> valueAdapter : valueAdapters) {
            ADAPTERS.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
        }
    }

    public void unregisterPropertyAdapter(String propertyName) {
        assert propertyName != null;

        ADAPTERS.remove(propertyName.toLowerCase());
    }

    public void clearPropertyAdapters() {

        ADAPTERS.clear();
    }

    @Override
    public T randomValue(String propertyName) {
        assert propertyName != null;

        String key = propertyName.toLowerCase();

        if (ADAPTERS.containsKey(key)) {
            return ADAPTERS.get(key).randomValue();
        }

        return randomValueDefault(propertyName);
    }

    protected abstract T randomValueDefault(String propertyName);

    @Override
    public Class<?> getValueType() {

        return ClassUtil.getGenericType(getClass());
    }

    public Set<String> getAdapterNames() {

        return ADAPTERS.keySet();
    }
}
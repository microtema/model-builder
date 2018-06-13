package de.seven.fate.model.builder.adapter;


import de.seven.fate.commons.utils.ClassUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTypeRandomAdapter<T> implements TypeRandomAdapter<T> {

    private final Map<String, PropertyRandomAdapter<T>> adapters = Collections.synchronizedMap(new HashMap<String, PropertyRandomAdapter<T>>());


    @Override
    public T randomValue(String propertyName) {

        String key = StringUtils.trimToEmpty(propertyName).toLowerCase();

        if (adapters.containsKey(key)) {
            return adapters.get(key).randomValue();
        }

        return randomValueDefault(propertyName);
    }

    protected abstract T randomValueDefault(String propertyName);

    @Override
    public Class<?> getValueType() {

        return ClassUtil.getGenericType(getClass());
    }

    public Set<String> getAdapterNames() {

        return adapters.keySet();
    }

    protected void registerPropertyAdapter(PropertyRandomAdapter<T>... valueAdapters) {

        for (PropertyRandomAdapter<T> valueAdapter : valueAdapters) {
            adapters.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
        }
    }
}

package de.seven.fate.model.adapter;

import de.seven.fate.model.util.ClassUtil;

public abstract class AbstractRandomPropertyValueAdapter<T> implements RandomPropertyValueAdapter<T> {

    public Class<?> getValueType() {

        return ClassUtil.getGenericType(getClass());
    }
}
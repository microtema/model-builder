package de.seven.fate.model.adapter;

public interface RandomPropertyValueAdapter<T> {

    /**
     * @param propertyName
     * @param objectType
     * @return @return random value for given propertyName and objectType
     */
    T randomValue(String propertyName, Class<?> objectType);

    Class<?> getValueType();
}
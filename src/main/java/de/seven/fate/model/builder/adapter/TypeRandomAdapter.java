package de.seven.fate.model.builder.adapter;

public interface TypeRandomAdapter<T> {

    /**
     * @param propertyName Model property name
     * @return mix value for given propertyName
     */
    T randomValue(String propertyName);

    /**
     * @return Type of property
     */
    Class<?> getValueType();
}

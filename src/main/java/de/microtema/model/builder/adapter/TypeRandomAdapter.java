package de.microtema.model.builder.adapter;

public interface TypeRandomAdapter<T> {

    /**
     * @param propertyName Model property name
     * @return mix value for given propertyName
     */
    T randomValue(String propertyName);

    /**
     *
     * @param propertyName Model property name
     * @return fix value
     */
    T fixValue(String propertyName);

    /**
     * @return Type of property
     */
    Class<?> getValueType();
}

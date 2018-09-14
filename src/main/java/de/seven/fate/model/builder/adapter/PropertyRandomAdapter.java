package de.seven.fate.model.builder.adapter;

public interface PropertyRandomAdapter<T> {

    /**
     * @return mix value for given propertyName
     */
    T randomValue();

    String getPropertyName();

}

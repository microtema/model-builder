package de.seven.fate.model.adapter;

public interface PropertyRandomAdapter<T> {

    /**
     * @return random value for given propertyName
     */
    T randomValue();

    String getPropertyName();

}
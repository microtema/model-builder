package de.seven.fate.model.adapter;

public interface RandomValueAdapter<T> {

    /**
     * @return random value for given propertyName
     */
    T randomValue();

    String getPropertyName();

}
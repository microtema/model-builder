package de.microtema.model.builder.adapter;

public interface PropertyRandomAdapter<T> {

    /**
     * @return mix value for given propertyName
     */
    T randomValue();

    /**
     * @return fix value for given propertyName
     */
    T fixValue(String property);

    String getPropertyName();

}

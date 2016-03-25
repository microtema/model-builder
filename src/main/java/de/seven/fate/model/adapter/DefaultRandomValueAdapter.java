package de.seven.fate.model.adapter;

/**
 * Created by Mario on 24.03.2016.
 */
public class DefaultRandomValueAdapter implements  RandomPropertyValueAdapter<Object>  {

    @Override
    public Object randomValue(String propertyName, Class<?> objectType) {
        return null;
    }

    @Override
    public Class<?> getValueType() {
        return null;
    }
}

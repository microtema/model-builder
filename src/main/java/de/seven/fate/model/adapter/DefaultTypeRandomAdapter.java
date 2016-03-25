package de.seven.fate.model.adapter;

/**
 * Created by Mario on 24.03.2016.
 */
public class DefaultTypeRandomAdapter extends AbstractTypeRandomAdapter<Object> {

    @Override
    protected Object randomValueDefault(String propertyName) {
        return null;
    }

    @Override
    public Class<?> getValueType() {
        return null;
    }
}

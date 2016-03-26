package de.seven.fate.model.adapter;


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

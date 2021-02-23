package de.microtema.model.builder.adapter.floats;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;


public class FloatTypeRandomAdapter extends AbstractTypeRandomAdapter<Float> {


    @Override
    protected Float randomValueDefault(String propertyName) {

        return RANDOM.nextFloat();
    }


    @Override
    public Float fixValue(String propertyName) {

        return (float) propertyName.length();
    }
}

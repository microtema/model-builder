package de.seven.fate.model.builder.adapter.floats;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;


public class FloatTypeRandomAdapter extends AbstractTypeRandomAdapter<Float> {


    @Override
    protected Float randomValueDefault(String propertyName) {

        return RANDOM.nextFloat();
    }


}

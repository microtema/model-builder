package de.seven.fate.model.builder.adapter.doublev;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;


public class DoubleTypeRandomAdapter extends AbstractTypeRandomAdapter<Double> {


    @Override
    protected Double randomValueDefault(String propertyName) {

        return RANDOM.nextDouble();
    }


}

package de.microtema.model.builder.adapter.doublev;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;


public class DoubleTypeRandomAdapter extends AbstractTypeRandomAdapter<Double> {


    @Override
    protected Double randomValueDefault(String propertyName) {

        return RANDOM.nextDouble();
    }


    @Override
    public Double fixValue(String propertyName) {

        return (double) propertyName.length();
    }
}

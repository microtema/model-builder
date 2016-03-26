package de.seven.fate.model.adapter.doublev;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.Random;


public class DoubleTypeRandomAdapter extends AbstractTypeRandomAdapter<Double> {


    @Override
    protected Double randomValueDefault(String propertyName) {

        return new Random().nextDouble();
    }


}

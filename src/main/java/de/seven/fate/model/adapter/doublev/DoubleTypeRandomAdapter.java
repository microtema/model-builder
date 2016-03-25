package de.seven.fate.model.adapter.doublev;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class DoubleTypeRandomAdapter extends AbstractTypeRandomAdapter<Double> {


    @Override
    protected Double randomValueDefault(String propertyName) {

        return new Random().nextDouble();
    }


}

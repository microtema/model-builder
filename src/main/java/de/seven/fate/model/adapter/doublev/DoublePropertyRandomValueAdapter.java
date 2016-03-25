package de.seven.fate.model.adapter.doublev;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;

import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class DoublePropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<Double> {


    @Override
    protected Double randomValueImpl(String propertyName, Class<?> objectType) {

        return new Random().nextDouble();
    }


}

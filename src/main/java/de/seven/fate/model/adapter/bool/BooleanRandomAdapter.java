package de.seven.fate.model.adapter.bool;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class BooleanRandomAdapter extends AbstractTypeRandomAdapter<Boolean> {

    @Override
    protected Boolean randomValueDefault(String propertyName) {

        return new Random().nextBoolean();
    }

}

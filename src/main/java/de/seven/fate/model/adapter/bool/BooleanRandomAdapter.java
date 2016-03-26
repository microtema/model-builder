package de.seven.fate.model.adapter.bool;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.Random;


public class BooleanRandomAdapter extends AbstractTypeRandomAdapter<Boolean> {

    @Override
    protected Boolean randomValueDefault(String propertyName) {

        return new Random().nextBoolean();
    }

}

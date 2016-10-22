package de.seven.fate.model.adapter.bool;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.security.SecureRandom;


public class BooleanRandomAdapter extends AbstractTypeRandomAdapter<Boolean> {

    @Override
    protected Boolean randomValueDefault(String propertyName) {

        return new SecureRandom().nextBoolean();
    }

}

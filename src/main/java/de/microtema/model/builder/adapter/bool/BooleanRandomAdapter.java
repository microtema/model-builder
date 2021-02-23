package de.microtema.model.builder.adapter.bool;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;

import java.security.SecureRandom;


public class BooleanRandomAdapter extends AbstractTypeRandomAdapter<Boolean> {

    @Override
    protected Boolean randomValueDefault(String propertyName) {

        return new SecureRandom().nextBoolean();
    }

    @Override
    public Boolean fixValue(String propertyName) {

        return propertyName.length() % 2 == 0;
    }
}

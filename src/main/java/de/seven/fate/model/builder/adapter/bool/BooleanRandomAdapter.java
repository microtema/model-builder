package de.seven.fate.model.builder.adapter.bool;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.security.SecureRandom;


public class BooleanRandomAdapter extends AbstractTypeRandomAdapter<Boolean> {

    @Override
    protected Boolean randomValueDefault(String propertyName) {

        return new SecureRandom().nextBoolean();
    }

}

package de.microtema.model.builder.adapter.binary;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;

import java.util.UUID;

public class BinaryTypeRandomAdapter extends AbstractTypeRandomAdapter<Byte> {

    @Override
    protected Byte randomValueDefault(String propertyName) {
        return UUID.randomUUID().toString().getBytes()[0];
    }

    @Override
    public Byte fixValue(String propertyName) {

        return propertyName.getBytes()[0];
    }
}

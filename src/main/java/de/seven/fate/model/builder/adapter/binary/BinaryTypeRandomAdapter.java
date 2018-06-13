package de.seven.fate.model.builder.adapter.binary;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.util.UUID;

public class BinaryTypeRandomAdapter extends AbstractTypeRandomAdapter<Byte> {

    @Override
    protected Byte randomValueDefault(String propertyName) {
        return UUID.randomUUID().toString().getBytes()[0];
    }
}

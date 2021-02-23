package de.microtema.model.builder.adapter.integer;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;

import java.util.concurrent.atomic.AtomicInteger;


public class PositionPropertyRandomAdapter implements PropertyRandomAdapter<Integer> {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    @Override
    public Integer randomValue() {

        return ATOMIC_INTEGER.getAndIncrement();
    }

    @Override
    public Integer fixValue(String property) {

        return property.length();
    }

    @Override
    public String getPropertyName() {
        return "position";
    }

}

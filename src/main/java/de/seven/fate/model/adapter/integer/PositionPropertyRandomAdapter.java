package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.adapter.PropertyRandomAdapter;

import java.util.concurrent.atomic.AtomicInteger;


public class PositionPropertyRandomAdapter implements PropertyRandomAdapter<Integer> {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    @Override
    public Integer randomValue() {

        return ATOMIC_INTEGER.getAndIncrement();
    }

    @Override
    public String getPropertyName() {
        return "position";
    }

}

package de.microtema.model.builder.adapter.longv;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;

import java.util.concurrent.atomic.AtomicInteger;


public class IdPropertyRandomAdapter implements PropertyRandomAdapter<Long> {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1000);

    @Override
    public Long randomValue() {
        return Long.parseLong(String.valueOf(ATOMIC_INTEGER.getAndIncrement()));
    }

    @Override
    public Long fixValue(String property) {

        return (long) property.length();
    }

    @Override
    public String getPropertyName() {
        return "id";
    }

}

package de.seven.fate.model.adapter.longv;

import de.seven.fate.model.adapter.RandomValueAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mario on 24.03.2016.
 */
public class IdRandomAdapter implements RandomValueAdapter<Long> {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1000);

    @Override
    public Long randomValue() {
        return Long.parseLong(String.valueOf(ATOMIC_INTEGER.getAndIncrement()));
    }

    @Override
    public String getPropertyName() {
        return "id";
    }

}

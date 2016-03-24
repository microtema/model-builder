package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mario on 24.03.2016.
 */
public class PositionRandomAdapter implements RandomValueAdapter<Integer> {

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

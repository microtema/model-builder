package de.microtema.model.builder.adapter.integer;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;
import de.microtema.model.builder.util.NumberUtil;


public class ZipCodePropertyRandomAdapter implements PropertyRandomAdapter<Integer> {

    private static final int MIN_SIZE = 1000;
    private static final int MAX_SIZE = 9000;

    @Override
    public Integer randomValue() {

        return NumberUtil.random(MIN_SIZE, MAX_SIZE);
    }

    @Override
    public Integer fixValue(String property) {

        return MIN_SIZE;
    }

    @Override
    public String getPropertyName() {
        return "zipCode";
    }

}

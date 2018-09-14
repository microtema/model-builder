package de.seven.fate.model.builder.adapter.integer;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.NumberUtil;


public class ZipCodePropertyRandomAdapter implements PropertyRandomAdapter<Integer> {

    private static final int MIN_SIZE = 1000;
    private static final int MAX_SIZE = 9000;

    @Override
    public Integer randomValue() {

        return NumberUtil.random(MIN_SIZE, MAX_SIZE);
    }

    @Override
    public String getPropertyName() {
        return "zipCode";
    }

}

package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.adapter.PropertyRandomAdapter;
import de.seven.fate.model.util.NumberUtil;


public class ZipCodePropertyRandomAdapter implements PropertyRandomAdapter<Integer> {

    @Override
    public Integer randomValue() {

        return NumberUtil.random(1000, 9000);
    }

    @Override
    public String getPropertyName() {
        return "zipCode";
    }

}

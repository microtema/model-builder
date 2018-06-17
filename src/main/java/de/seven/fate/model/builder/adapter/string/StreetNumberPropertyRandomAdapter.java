package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.NumberUtil;


public class StreetNumberPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    @Override
    public String randomValue() {

        return String.valueOf(NumberUtil.random(0, 1000));
    }

    @Override
    public String getPropertyName() {
        return "streetNumber";
    }

}
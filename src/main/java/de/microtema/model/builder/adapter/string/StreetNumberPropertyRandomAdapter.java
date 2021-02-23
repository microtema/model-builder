package de.microtema.model.builder.adapter.string;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;
import de.microtema.model.builder.util.NumberUtil;

import javax.management.StringValueExp;


public class StreetNumberPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final int MAX_SIZE = 1000;

    @Override
    public String randomValue() {

        return String.valueOf(NumberUtil.random(0, MAX_SIZE));
    }

    @Override
    public String fixValue(String property) {

        return String.valueOf(property.length());
    }

    @Override
    public String getPropertyName() {
        return "streetNumber";
    }

}

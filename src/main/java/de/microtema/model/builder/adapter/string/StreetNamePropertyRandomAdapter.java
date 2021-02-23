package de.microtema.model.builder.adapter.string;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;
import de.microtema.model.builder.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;


public class StreetNamePropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> STREETS = Arrays.asList("Leopoldstr", "Gottesauerstr", "Maximilianstr", "Pfinztstr", "Bachstr");

    @Override
    public String randomValue() {

        return CollectionUtil.random(STREETS);
    }

    @Override
    public String fixValue(String property) {

        return CollectionUtil.first(STREETS);
    }

    @Override
    public String getPropertyName() {
        return "streetName";
    }

}

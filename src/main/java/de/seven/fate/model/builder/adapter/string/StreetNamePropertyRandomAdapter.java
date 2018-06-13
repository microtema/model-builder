package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;


public class StreetNamePropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> STREETS = Arrays.asList("Leopoldstr", "Gottesauerstr", "Maximilianstr", "Pfinztstr");

    @Override
    public String randomValue() {

        return CollectionUtil.random(STREETS);
    }

    @Override
    public String getPropertyName() {
        return "streetName";
    }

}

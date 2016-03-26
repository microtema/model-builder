package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.PropertyRandomAdapter;
import de.seven.fate.model.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mario on 24.03.2016.
 */
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

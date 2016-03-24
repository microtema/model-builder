package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mario on 24.03.2016.
 */
public class NameRandomAdapter implements RandomValueAdapter<String> {

    private static final List<String> NAMES = Arrays.asList("Mario","Emmy","Eva","Aeneas");

    @Override
    public String randomValue() {
        return CollectionUtil.random(NAMES);
    }

    @Override
    public String getPropertyName() {
        return "name";
    }

}

package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;
import de.seven.fate.model.adapter.PropertyRandomAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class IntegerRandomAdapter extends AbstractTypeRandomAdapter<Integer> {

    private static final Map<String, PropertyRandomAdapter<Integer>> MAP = new HashMap<>();

    public IntegerRandomAdapter() {
        registerPropertyRandomAdapter(new PositionPropertyRandomAdapter());
    }


    @Override
    protected Integer randomValueDefault(String propertyName) {

        return new Random().nextInt();
    }

}

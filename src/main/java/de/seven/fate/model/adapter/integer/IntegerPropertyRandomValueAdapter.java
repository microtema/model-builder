package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class IntegerPropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<Integer> {

    private static final Map<String, RandomValueAdapter<Integer>> MAP = new HashMap<>();

    public IntegerPropertyRandomValueAdapter() {
        registerRandomAdapter(new PositionRandomAdapter());
    }


    @Override
    protected Integer randomValueImpl(String propertyName, Class<?> objectType) {

        return new Random().nextInt();
    }

}

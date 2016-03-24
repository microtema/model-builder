package de.seven.fate.model.adapter.bool;

import de.seven.fate.model.adapter.AbstractRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class BooleanRandomPropertyValueAdapter extends AbstractRandomPropertyValueAdapter<Boolean> {

    private static final Map<String, RandomValueAdapter<Boolean>> MAP = new HashMap<>();

    @Override
    public Boolean randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return new Random().nextBoolean();
    }

    public static void registerRandomAdapter(RandomValueAdapter<Boolean> valueAdapter) {

        MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
    }
}

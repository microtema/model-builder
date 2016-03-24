package de.seven.fate.model.adapter.longv;

import de.seven.fate.model.adapter.AbstractRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.adapter.integer.PositionRandomAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class LongRandomPropertyValueAdapter extends AbstractRandomPropertyValueAdapter<Long> {

    private static final Map<String, RandomValueAdapter<Long>> MAP = new HashMap<>();

    static {
        registerRandomAdapter(new IdRandomAdapter());
    }


    @Override
    public Long randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return new Random().nextLong();
    }

    public static void registerRandomAdapter(RandomValueAdapter<Long> valueAdapter) {

        MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
    }
}

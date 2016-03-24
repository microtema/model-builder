package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.adapter.AbstractRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.adapter.string.EmailRandomAdapter;
import de.seven.fate.model.adapter.string.NameRandomAdapter;
import de.seven.fate.model.adapter.string.PhoneNumberRandomAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Mario on 24.03.2016.
 */
public class IntegerRandomPropertyValueAdapter extends AbstractRandomPropertyValueAdapter<Integer> {

    private static final Map<String, RandomValueAdapter<Integer>> MAP = new HashMap<>();

    static {
        registerRandomAdapter(new PositionRandomAdapter());
    }


    @Override
    public Integer randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return new Random().nextInt();
    }

    public static void registerRandomAdapter(RandomValueAdapter<Integer> valueAdapter) {

        MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
    }
}

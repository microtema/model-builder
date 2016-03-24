package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.AbstractRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Mario on 24.03.2016.
 */
public class StringRandomPropertyValueAdapter extends AbstractRandomPropertyValueAdapter<String> {

    private static final Map<String, RandomValueAdapter<String>> MAP = new HashMap<>();

    static {
        registerRandomAdapter(new NameRandomAdapter());
        registerRandomAdapter(new EmailRandomAdapter());
        registerRandomAdapter(new PhoneNumberRandomAdapter());
    }


    @Override
    public String randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return propertyName + ':' + UUID.randomUUID().toString();
    }

    public static void registerRandomAdapter(RandomValueAdapter<String> valueAdapter) {

        MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
    }
}

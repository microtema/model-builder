package de.seven.fate.model.adapter.date;

import de.seven.fate.model.adapter.AbstractRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mario on 24.03.2016.
 */
public class DateRandomPropertyValueAdapter extends AbstractRandomPropertyValueAdapter<Date> {

    private static final Map<String, RandomValueAdapter<Date>> MAP = new HashMap<>();

    static {
        registerRandomAdapter(new BodRandomAdapter());
    }

    @Override
    public Date randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return new Date();
    }

    public static void registerRandomAdapter(RandomValueAdapter<Date> valueAdapter) {

        MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
    }
}

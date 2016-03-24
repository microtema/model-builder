package de.seven.fate.model.adapter.decimal;

import de.seven.fate.model.adapter.AbstractRandomPropertyValueAdapter;
import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.adapter.integer.PositionRandomAdapter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class BigDecimalRandomPropertyValueAdapter extends AbstractRandomPropertyValueAdapter<BigDecimal> {

    private static final Map<String, RandomValueAdapter<BigDecimal>> MAP = new HashMap<>();


    @Override
    public BigDecimal randomValue(String propertyName, Class<?> objectType) {

        String key = propertyName.toLowerCase();

        if (MAP.containsKey(key)) {
            return MAP.get(key).randomValue();
        }

        return BigDecimal.valueOf(new Random().nextLong(), 2);
    }

    public static void registerRandomAdapter(RandomValueAdapter<BigDecimal> valueAdapter) {

        MAP.put(valueAdapter.getPropertyName().toLowerCase(), valueAdapter);
    }
}

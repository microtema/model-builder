package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.PropertyRandomAdapter;
import de.seven.fate.model.util.NumberUtil;

/**
 * Created by Mario on 24.03.2016.
 */
public class StreetNumberPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    @Override
    public String randomValue() {

        return String.valueOf(NumberUtil.random(0, 1000));
    }

    @Override
    public String getPropertyName() {
        return "streetNumber";
    }

}

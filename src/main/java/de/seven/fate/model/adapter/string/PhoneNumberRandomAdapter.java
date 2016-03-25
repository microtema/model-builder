package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.util.NumberUtil;
import de.seven.fate.model.util.StringUtil;

/**
 * Created by Mario on 24.03.2016.
 */
public class PhoneNumberRandomAdapter implements RandomValueAdapter<String> {

    @Override
    public String randomValue() {

        return StringUtil.leftPad(String.valueOf(NumberUtil.random(12345678, 123456789)), 10, '0');
    }

    @Override
    public String getPropertyName() {
        return "phoneNumber";
    }
}

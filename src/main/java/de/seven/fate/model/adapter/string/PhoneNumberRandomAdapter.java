package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Mario on 24.03.2016.
 */
public class PhoneNumberRandomAdapter implements RandomValueAdapter<String> {

    @Override
    public String randomValue() {

        return StringUtils.leftPad(String.valueOf(NumberUtil.random(12345678, 123456789)), 10, "0");
    }

    @Override
    public String getPropertyName() {
        return "phoneNumber";
    }
}

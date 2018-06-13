package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;


public class PhoneNumberPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    @Override
    public String randomValue() {

        return StringUtils.leftPad(String.valueOf(NumberUtil.random(12345678, 123456789)), 10, '0');
    }

    @Override
    public String getPropertyName() {
        return "phoneNumber";
    }
}

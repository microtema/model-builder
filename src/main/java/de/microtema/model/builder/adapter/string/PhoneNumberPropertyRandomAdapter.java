package de.microtema.model.builder.adapter.string;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;
import de.microtema.model.builder.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;


public class PhoneNumberPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final int MAX_SIZE = 123456789;
    private static final int MIN_SIZE = 12345678;
    private static final int MAX_WORD_SIZE = 10;

    @Override
    public String randomValue() {

        return StringUtils.leftPad(String.valueOf(NumberUtil.random(MIN_SIZE, MAX_SIZE)), MAX_WORD_SIZE, '0');
    }

    @Override
    public String fixValue(String property) {

        return String.valueOf(MAX_SIZE);
    }

    @Override
    public String getPropertyName() {
        return "phoneNumber";
    }
}

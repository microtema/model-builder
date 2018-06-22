package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class EmailPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> DOMAINS = Arrays.asList("com", "de", "org", "al");
    private static final int BEGIN_INDEX = 10;
    private static final int END_INDEX = 20;
    private static final int MAX_NAME_SIZE = 10;

    @Override
    public String randomValue() {

        String string = UUID.randomUUID().toString();

        return string.substring(0, MAX_NAME_SIZE) + "@" + string.substring(BEGIN_INDEX, END_INDEX) + "." + CollectionUtil.random(DOMAINS);
    }

    @Override
    public String getPropertyName() {
        return "email";
    }

}

package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class EmailPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> DOMAINS = Arrays.asList("com", "de", "org", "al");

    @Override
    public String randomValue() {

        String string = UUID.randomUUID().toString();

        return string.substring(0, 10) + "@" + string.substring(10, 20) + "." + CollectionUtil.random(DOMAINS);
    }

    @Override
    public String getPropertyName() {
        return "email";
    }

}

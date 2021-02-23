package de.microtema.model.builder.adapter.string;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;
import de.microtema.model.builder.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class LinkPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> PROTOCOLS = Arrays.asList("http", "https", "ftp");
    private static final List<String> DOMAINS = Arrays.asList("com", "de", "org", "al");
    private static final int BEGIN_INDEX = 10;
    private static final int END_INDEX = 20;

    @Override
    public  String randomValue() {

        String string = UUID.randomUUID().toString();

        return CollectionUtil.random(PROTOCOLS) + "://" + string.substring(BEGIN_INDEX, END_INDEX) + "." + CollectionUtil.random(DOMAINS);
    }

    @Override
    public String fixValue(String property) {

        return CollectionUtil.first(PROTOCOLS) + "://" + property + "." + CollectionUtil.first(DOMAINS);
    }

    @Override
    public String getPropertyName() {
        return "link";
    }

}

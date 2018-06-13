package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class LinkPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> PROTOCOLS = Arrays.asList("http", "https", "ftp");
    private static final List<String> DOMAINS = Arrays.asList("com", "de", "org", "al");

    public static String randomLink() {

        String string = UUID.randomUUID().toString();

        return CollectionUtil.random(PROTOCOLS) + "://" + string.substring(10, 20) + "." + CollectionUtil.random(DOMAINS);
    }

    @Override
    public String randomValue() {
        return randomLink();
    }

    @Override
    public String getPropertyName() {
        return "link";
    }

}

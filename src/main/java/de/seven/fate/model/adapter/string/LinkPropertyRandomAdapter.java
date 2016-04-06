package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.PropertyRandomAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.seven.fate.model.util.CollectionUtil.random;


public class LinkPropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final List<String> PROTOCOLS = Arrays.asList("http", "https", "ftp");
    private static final List<String> DOMAINS = Arrays.asList("com", "de", "org", "al");

    public static String randomLink() {

        String string = UUID.randomUUID().toString();

        return random(PROTOCOLS) + "://" + string.substring(10, 20) + "." + random(DOMAINS);
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

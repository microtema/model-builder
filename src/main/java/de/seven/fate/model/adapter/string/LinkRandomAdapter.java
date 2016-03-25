package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.RandomValueAdapter;
import de.seven.fate.model.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.seven.fate.model.util.CollectionUtil.random;

/**
 * Created by Mario on 24.03.2016.
 */
public class LinkRandomAdapter implements RandomValueAdapter<String> {

    private static final List<String> PROTOCOLS = Arrays.asList("http", "https", "ftp", "ssh");
    private static final List<String> DOMAINS = Arrays.asList("com", "de", "org", "al");

    @Override
    public String randomValue() {

        String string = UUID.randomUUID().toString();

        return random(PROTOCOLS) + "://" + string.substring(10, 20) + "." + random(DOMAINS);
    }

    @Override
    public String getPropertyName() {
        return "link";
    }

}

package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;
import de.seven.fate.model.builder.util.CollectionUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class NamePropertyRandomAdapter implements PropertyRandomAdapter<String> {

    private static final Logger LOGGER = Logger.getLogger(NamePropertyRandomAdapter.class.getName());

    private static final List<String> NAMES = readNames();

    private static List<String> readNames() {

        try {

            InputStream inputStream = NamePropertyRandomAdapter.class.getResourceAsStream("/person-list.txt");

            List<String> strings = IOUtils.readLines(inputStream, Charset.defaultCharset());

            return strings.stream().map(it -> WordUtils.capitalizeFully(it.split("\\s")[0])).collect(Collectors.toList());
        } catch (IOException e) {

            LOGGER.log(Level.WARNING, "Unable to resolve person-list.txt in class path!", e);

            return Arrays.asList("Mario", "Emmy", "Eva", "Aeneas");
        }
    }

    @Override
    public String randomValue() {
        return CollectionUtil.random(NAMES);
    }

    @Override
    public String getPropertyName() {
        return "name";
    }

}

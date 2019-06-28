package de.seven.fate.model.builder.util;

import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;

import static de.seven.fate.model.builder.constants.Constants.MAX_COLLECTION_SIZE;
import static de.seven.fate.model.builder.constants.Constants.MIN_COLLECTION_SIZE;

/**
 * Model Builder Util
 */
public final class ModelBuilderUtil {

    private ModelBuilderUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    /**
     * @return random collection size
     */
    public static int randomCollectionSize() {

        return Math.max(MIN_COLLECTION_SIZE, new SecureRandom().nextInt(MAX_COLLECTION_SIZE));
    }

    /**
     * @param resourceLocation may not be null
     * @return Properties from properties files
     */
    public static Properties fromProperties(String resourceLocation) {
        Validate.notNull(resourceLocation, "resourceLocation");

        Properties properties = new Properties();

        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

            properties.load(Validate.notNull(resourceAsStream));
        } catch (IOException | NullPointerException e) {

            throw new IllegalArgumentException(e);
        }

        return properties;
    }

    /**
     * @param resourceLocation may not be null
     * @return Properties from xml files
     */
    public static Properties fromXml(String resourceLocation) {
        Validate.notNull(resourceLocation, "resourceLocation");

        Properties properties = new Properties();

        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

            properties.loadFromXML(Validate.notNull(resourceAsStream));
        } catch (IOException | NullPointerException e) {

            throw new IllegalArgumentException(e);
        }

        return properties;
    }
}

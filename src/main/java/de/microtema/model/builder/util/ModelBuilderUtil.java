package de.microtema.model.builder.util;

import de.microtema.model.builder.constants.Constants;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;

@UtilityClass
public final class ModelBuilderUtil {

    /**
     * @return random collection size
     */
    public static int randomCollectionSize() {

        return Math.max(Constants.MIN_COLLECTION_SIZE, new SecureRandom().nextInt(Constants.MAX_COLLECTION_SIZE));
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

package de.seven.fate.model.builder;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static de.seven.fate.model.builder.constants.Constants.MAY_NOT_BE_EMPTY;
import static de.seven.fate.model.builder.util.ModelBuilderUtil.randomCollectionSize;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {

    public List<T> list(boolean skip) {

        int size = randomCollectionSize();

        return list(size, skip);
    }

    protected List<T> list(int size, boolean skip) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = build(null, skip, false);

            list.add(model);
        }

        return list;
    }

    public List<T> list(int size, boolean skip, boolean required) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = build(null, skip, required);

            list.add(model);
        }

        return list;
    }

    protected Set<T> set(int size, boolean skip) {

        return new HashSet<>(list(size, skip));
    }

    public Set<T> set(int size, boolean skip, boolean required) {

        return new HashSet<>(list(size, skip, required));
    }

    public Set<T> set(boolean skip) {

        int collectionSize = randomCollectionSize();

        return set(collectionSize, skip);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T fromResource(String resourceLocation) {
        Validate.notNull(resourceLocation, MAY_NOT_BE_EMPTY, "resourceLocation");

        Class<T> genericType = getGenericType();

        if (Properties.class.isAssignableFrom(genericType)) {

            String extension = FilenameUtils.getExtension(resourceLocation);

            if (StringUtils.equals(extension, "properties")) {

                Properties properties = new Properties();

                try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

                    properties.load(resourceAsStream);
                } catch (IOException e) {

                    throw new IllegalArgumentException(e);
                }

                return (T) properties;
            }

            if (StringUtils.equals(extension, "xml")) {

                Properties properties = new Properties();

                try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

                    properties.loadFromXML(resourceAsStream);
                } catch (IOException e) {

                    throw new IllegalArgumentException(e);
                }

                return (T) properties;
            }
        }

        throw new UnsupportedOperationException();
    }


}

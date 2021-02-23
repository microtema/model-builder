package de.microtema.model.builder.adapter.bool;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;


public class BooleanRandomAdapter extends AbstractTypeRandomAdapter<Boolean> {

    @Override
    protected Boolean randomValueDefault(String propertyName) {

        return new SecureRandom().nextBoolean();
    }

    @Override
    public Boolean fixValue(String propertyName) {

        return StringUtils.trimToEmpty(propertyName).length() % 2 == 0;
    }
}

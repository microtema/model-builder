package de.microtema.model.builder.adapter.string;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;


public class StringRandomAdapter extends AbstractTypeRandomAdapter<String> {

    public StringRandomAdapter(NamePropertyRandomAdapter namePropertyRandomAdapter,
                               EmailPropertyRandomAdapter emailPropertyRandomAdapter,
                               PhoneNumberPropertyRandomAdapter phoneNumberPropertyRandomAdapter,
                               LinkPropertyRandomAdapter linkPropertyRandomAdapter,
                               UrlPropertyRandomAdapter urlPropertyRandomAdapter,
                               StreetNamePropertyRandomAdapter streetNamePropertyRandomAdapter,
                               StreetNumberPropertyRandomAdapter streetNumberPropertyRandomAdapter) {
        registerPropertyAdapter(
                namePropertyRandomAdapter,
                emailPropertyRandomAdapter,
                phoneNumberPropertyRandomAdapter,
                linkPropertyRandomAdapter,
                urlPropertyRandomAdapter,
                streetNamePropertyRandomAdapter,
                streetNumberPropertyRandomAdapter);
    }


    @Override
    public String randomValueDefault(String propertyName) {

        if (StringUtils.isBlank(propertyName)) {
            return UUID.randomUUID().toString();
        }

        return propertyName + ':' + UUID.randomUUID().toString();
    }

    @Override
    public String fixValue(String propertyName) {

        return propertyName;
    }
}

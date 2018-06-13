package de.seven.fate.model.builder.adapter.string;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;


public class StringRandomAdapter extends AbstractTypeRandomAdapter<String> {

    public StringRandomAdapter() {
        registerPropertyAdapter(
                new NamePropertyRandomAdapter(),
                new EmailPropertyRandomAdapter(),
                new PhoneNumberPropertyRandomAdapter(),
                new LinkPropertyRandomAdapter(),
                new UrlPropertyRandomAdapter(),
                new StreetNamePropertyRandomAdapter(),
                new StreetNumberPropertyRandomAdapter());
    }


    @Override
    public String randomValueDefault(String propertyName) {

        if (StringUtils.isBlank(propertyName)) {
            return UUID.randomUUID().toString();
        }

        return propertyName + ':' + UUID.randomUUID().toString();
    }

}

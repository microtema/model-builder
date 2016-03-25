package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.UUID;

/**
 * Created by Mario on 24.03.2016.
 */
public class StringRandomAdapter extends AbstractTypeRandomAdapter<String> {

    public StringRandomAdapter() {
        registerPropertyRandomAdapter(new NamePropertyRandomAdapter(), new EmailPropertyRandomAdapter(), new PhoneNumberPropertyRandomAdapter(), new LinkPropertyRandomAdapter());
    }


    @Override
    public String randomValueDefault(String propertyName) {

        return propertyName + ':' + UUID.randomUUID().toString();
    }

}

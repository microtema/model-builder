package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;

import java.util.UUID;

/**
 * Created by Mario on 24.03.2016.
 */
public class StringPropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<String> {

    public StringPropertyRandomValueAdapter() {
        registerRandomAdapter(new NameRandomAdapter(), new EmailRandomAdapter(), new PhoneNumberRandomAdapter());
    }


    @Override
    public String randomValueImpl(String propertyName, Class<?> objectType) {

        return propertyName + ':' + UUID.randomUUID().toString();
    }

}

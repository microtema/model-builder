package de.seven.fate.model.adapter.date;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;

import java.util.Date;

/**
 * Created by Mario on 24.03.2016.
 */
public class DatePropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<Date> {

    public DatePropertyRandomValueAdapter() {
        registerRandomAdapter(new BodRandomAdapter());
    }

    @Override
    protected Date randomValueImpl(String propertyName, Class<?> objectType) {

        return new Date();
    }
}

package de.seven.fate.model.adapter.date;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.Date;


public class DateRandomAdapter extends AbstractTypeRandomAdapter<Date> {

    public DateRandomAdapter() {
        registerPropertyAdapter(new BodPropertyRandomAdapter());
    }

    @Override
    protected Date randomValueDefault(String propertyName) {

        return new Date();
    }
}

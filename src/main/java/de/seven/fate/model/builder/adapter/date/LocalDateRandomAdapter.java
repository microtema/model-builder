package de.seven.fate.model.builder.adapter.date;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.time.LocalDate;


public class LocalDateRandomAdapter extends AbstractTypeRandomAdapter<LocalDate> {

    @Override
    protected LocalDate randomValueDefault(String propertyName) {

        return LocalDate.now();
    }
}

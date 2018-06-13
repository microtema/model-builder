package de.seven.fate.model.builder.adapter.date;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.time.LocalDateTime;


public class LocalDateTimeRandomAdapter extends AbstractTypeRandomAdapter<LocalDateTime> {

    @Override
    protected LocalDateTime randomValueDefault(String propertyName) {

        return LocalDateTime.now();
    }
}

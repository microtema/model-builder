package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
public class LocalDateTimeRandomAdapter extends AbstractTypeRandomAdapter<LocalDateTime> {

    private final DateRandomAdapter dateRandomAdapter;

    @Override
    protected LocalDateTime randomValueDefault(String propertyName) {

        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime fixValue(String propertyName) {

        Date date = dateRandomAdapter.fixValue(propertyName);

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}

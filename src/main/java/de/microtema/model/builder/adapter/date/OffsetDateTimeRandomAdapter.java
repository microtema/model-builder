package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
public class OffsetDateTimeRandomAdapter extends AbstractTypeRandomAdapter<OffsetDateTime> {

    private final DateRandomAdapter dateRandomAdapter;

    @Override
    protected OffsetDateTime randomValueDefault(String propertyName) {

        return OffsetDateTime.now();
    }

    @Override
    public OffsetDateTime fixValue(String propertyName) {

        Date date = dateRandomAdapter.fixValue(propertyName);

        return OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}

package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
public class LocalDateRandomAdapter extends AbstractTypeRandomAdapter<LocalDate> {

    private final DateRandomAdapter dateRandomAdapter;

    @Override
    protected LocalDate randomValueDefault(String propertyName) {

        return LocalDate.now();
    }

    @Override
    @SneakyThrows
    public LocalDate fixValue(String propertyName) {

        Date date = dateRandomAdapter.fixValue(propertyName);

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

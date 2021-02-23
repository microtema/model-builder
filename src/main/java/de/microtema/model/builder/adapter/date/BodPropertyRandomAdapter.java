package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.adapter.PropertyRandomAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static de.microtema.model.builder.adapter.date.DateRandomAdapter.formatSeconds;

@RequiredArgsConstructor
public class BodPropertyRandomAdapter implements PropertyRandomAdapter<Date> {

    @Override
    public Date randomValue() {

        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    @Override
    @SneakyThrows
    public Date fixValue(String property) {

        GregorianCalendar calendar = new GregorianCalendar();

        int seconds = property.length();

        String formattedTime = formatSeconds(seconds);

        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2021-01-01T" + formattedTime);

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, Math.min(property.length(), 30));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    @Override
    public String getPropertyName() {
        return "dob";
    }

}

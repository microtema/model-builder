package de.seven.fate.model.adapter.date;

import de.seven.fate.model.adapter.PropertyRandomAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Mario on 24.03.2016.
 */
public class BodPropertyRandomAdapter implements PropertyRandomAdapter<Date> {

    private static final GregorianCalendar CALENDAR = new GregorianCalendar();

    @Override
    public Date randomValue() {

        CALENDAR.setTime(new Date());
        CALENDAR.set(Calendar.HOUR_OF_DAY, 0);
        CALENDAR.set(Calendar.MINUTE, 0);
        CALENDAR.set(Calendar.SECOND, 0);
        CALENDAR.set(Calendar.MILLISECOND, 0);

        return CALENDAR.getTime();
    }

    @Override
    public String getPropertyName() {
        return "dob";
    }

}

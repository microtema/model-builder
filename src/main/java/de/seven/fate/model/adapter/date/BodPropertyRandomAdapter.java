package de.seven.fate.model.adapter.date;

import de.seven.fate.model.adapter.PropertyRandomAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class BodPropertyRandomAdapter implements PropertyRandomAdapter<Date> {

    @Override
    public Date randomValue() {

        GregorianCalendar CALENDAR = new GregorianCalendar();

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

package de.seven.fate.model.builder.adapter.date;

import de.seven.fate.model.builder.adapter.PropertyRandomAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
    public String getPropertyName() {
        return "dob";
    }

}

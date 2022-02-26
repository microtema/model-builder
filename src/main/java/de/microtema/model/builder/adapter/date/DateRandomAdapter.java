package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import de.microtema.model.builder.util.NumberUtil;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateRandomAdapter extends AbstractTypeRandomAdapter<Date> {

    public DateRandomAdapter(BodPropertyRandomAdapter bodPropertyRandomAdapter) {

        registerPropertyAdapter(bodPropertyRandomAdapter);
    }

    @Override
    protected Date randomValueDefault(String propertyName) {

        return new Date();
    }

    @Override
    @SneakyThrows
    public Date fixValue(String propertyName) {

        int seconds = NumberUtil.getStringSum(propertyName);

        String formattedTime = formatSeconds(seconds);

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2021-01-01T" + formattedTime);
    }

    public static String formatSeconds(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds;

        return formattedTime;
    }
}

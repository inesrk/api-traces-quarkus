package org.enterpriseflowsrepository.api.traces.quarkus.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateParameter implements Serializable {

    private static final long serialVersionUID = 2990276389332022931L;

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    private static SimpleDateFormat format;

    private Date date;

    static {
        format = new SimpleDateFormat(DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public DateParameter(Date date) {
        this.date = date;
    }

    public static DateParameter valueOf(String dateString) {
        try {
            return new DateParameter(format.parse(dateString));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return format.format(date);
    }
}

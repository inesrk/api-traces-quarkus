package org.enterpriseflowsrepository.api.traces.quarkus.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Use date & REST with a standardized approach.
 * 
 * <p>Implement the ISO 8601.
 */
public class DateParameter implements Serializable {
  private static final long serialVersionUID = 2990276389332022931L;

  /**
   * Date format for parsing a date String.
   * 
   * <p>Implement the ISO 8601. See: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
   */
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

  /**
   * Implementation of the date parser.
   */
  private static SimpleDateFormat format;

  /**
   * Date parsed or generated.
   */
  private Date date;

  static {
    format = new SimpleDateFormat(DATE_FORMAT);
    format.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  /**
   * Create from a Date object.
   * 
   * <p>Allow you then to transform this date in a REST valid string.
   */
  public DateParameter(Date date) {
    this.date = date;
  }

  /**
   * Generate a Date parser from a string.
   */
  public static DateParameter valueOf(String dateString) {
    try {
      return new DateParameter(format.parse(dateString));
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Date parsed or generated.
   */
  public Date getDate() {
    return date;
  }

  /**
   * Date parsed or generated.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * Retreive this date in a String format.
   */
  @Override
  public String toString() {
    return format.format(date);
  }
}

package uk.co.mruoc.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ElapsedTimeValidator {

    private static final String PATTERN = "HH:mm:ss.SSS";

    public boolean validate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(PATTERN);
            formatter.parseLocalTime(input);
            return true;
        } catch (IllegalArgumentException e) {
            throw new ElapsedTimeFormatException(input, e);
        }
    }

}

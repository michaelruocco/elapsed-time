package uk.co.mruoc.time;

import static java.lang.Integer.parseInt;
import static uk.co.mruoc.time.ElapsedTime.MILLIS_IN_HOUR;
import static uk.co.mruoc.time.ElapsedTime.MILLIS_IN_MINUTE;
import static uk.co.mruoc.time.ElapsedTime.MILLIS_IN_SECONDS;

public class ElapsedTimeParser {

    public ElapsedTime parse(String input) {
        try {
            long hours = toHours(input);
            long minutes = toMinutes(input);
            long seconds = toSeconds(input);
            long millis = toMillis(input);
            long totalMillis = calculateTotalMillis(hours, minutes, seconds, millis);
            return new ElapsedTime(totalMillis);
        } catch (NumberFormatException e) {
            throw new ElapsedTimeFormatException(input, e);
        }
    }

    private int toHours(String s) {
        return parseInt(splitOnColon(s)[0]);
    }

    private int toMinutes(String s) {
        return parseInt(splitOnColon(s)[1]);
    }

    private String toSecondsAndMillis(String s) {
        return splitOnColon(s)[2];
    }

    private int toSeconds(String s) {
        String secondsAndMillis = toSecondsAndMillis(s);
        return parseInt(splitOnDot(secondsAndMillis)[0]);
    }

    private int toMillis(String s) {
        String secondsAndMillis = toSecondsAndMillis(s);
        return parseInt(splitOnDot(secondsAndMillis)[1]);
    }

    private String[] splitOnColon(String s) {
        return s.split(":");
    }

    private String[] splitOnDot(String s) {
        return s.split("\\.");
    }

    private long calculateTotalMillis(long hours, long minutes, long seconds, long millis) {
        long totalMillis = (hours * MILLIS_IN_HOUR);
        totalMillis += (minutes * MILLIS_IN_MINUTE);
        totalMillis += (seconds * MILLIS_IN_SECONDS);
        totalMillis += millis;
        return totalMillis;
    }

}

package uk.co.mruoc.race;

import java.text.NumberFormat;

import static java.lang.Integer.parseInt;

public class RaceTime {

    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECONDS = 1000;

    private final long totalMillis;
    private final long hours;
    private final long minutes;
    private final long seconds;
    private final long millis;

    private final String formatted;

    public RaceTime() {
        this(0);
    }

    public RaceTime(String input) {
        this(new RaceTimeParser().toTotalMillis(input));
    }

    public RaceTime(long totalMillis) {
        this.totalMillis = totalMillis;

        this.hours = toHours(totalMillis);
        totalMillis -= hoursInMillis();

        this.minutes = toMinutes(totalMillis);
        totalMillis -= minutesInMillis();

        this.seconds = toSeconds(totalMillis);
        totalMillis -= secondsInMillis();

        this.millis = totalMillis;

        RaceTimeFormatter timeFormatter = new RaceTimeFormatter();
        this.formatted = timeFormatter.format(this);
    }

    public long getTotalMillis() {
        return totalMillis;
    }

    public boolean isEqualTo(RaceTime otherTime) {
        return this.totalMillis == otherTime.totalMillis;
    }

    public boolean isAfter(RaceTime otherTime) {
        return this.totalMillis > otherTime.totalMillis;
    }

    public boolean isBefore(RaceTime otherTime) {
        return this.totalMillis < otherTime.totalMillis;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public long getMillis() {
        return millis;
    }

    public String asString() {
        return formatted;
    }

    private long toHours(long totalMillis) {
        return (totalMillis / MILLIS_IN_HOUR);
    }

    private long toMinutes(long totalMillis) {
        return (totalMillis / MILLIS_IN_MINUTE);
    }

    private long toSeconds(long totalMillis) {
        return (totalMillis / MILLIS_IN_SECONDS);
    }

    private long hoursInMillis() {
        return hours * MILLIS_IN_HOUR;
    }

    private long minutesInMillis() {
        return minutes * MILLIS_IN_MINUTE;
    }

    private long secondsInMillis() {
        return seconds * MILLIS_IN_SECONDS;
    }

    private static class RaceTimeFormatter {

        private final NumberFormatter numberFormatter = new NumberFormatter(2);
        private final NumberFormatter milliFormatter = new NumberFormatter(3);

        private String format(RaceTime time) {
            return String.format("%s:%s:%s.%s",
                    numberFormatter.format(time.getHours()),
                    numberFormatter.format(time.getMinutes()),
                    numberFormatter.format(time.getSeconds()),
                    milliFormatter.format(time.getMillis()));
        }

        private static class NumberFormatter {

            private final NumberFormat format = NumberFormat.getNumberInstance();

            private NumberFormatter(int minimumIntegerDigits) {
                format.setMinimumIntegerDigits(minimumIntegerDigits);
            }

            private String format(double value) {
                return format.format(value);
            }

        }

    }

    private static class RaceTimeParser {

        private long toTotalMillis(String input) {
            long hours = toHours(input);
            long minutes = toMinutes(input);
            long seconds = toSeconds(input);
            long millis = toMillis(input);
            return calculateTotalMillis(hours, minutes, seconds, millis);
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

}

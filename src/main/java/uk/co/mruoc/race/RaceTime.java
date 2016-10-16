package uk.co.mruoc.race;

import java.text.NumberFormat;

public class RaceTime {

    private static final int MILLIS_IN_HOUR = 3600000;
    private static final int MILLIS_IN_MINUTE = 60000;
    private static final int MILLIS_IN_SECONDS = 1000;

    private final NumberFormatter numberFormatter = new NumberFormatter(2);
    private final NumberFormatter milliFormatter = new NumberFormatter(3);

    private final long totalMillis;
    private final long hours;
    private final long minutes;
    private final long seconds;
    private final long millis;

    private final String formattedTime;

    public RaceTime() {
        this(0);
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
        this.formattedTime = buildFormattedTime();
    }

    public long getTotalMillis() {
        return totalMillis;
    }

    public String asString() {
        return formattedTime;
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

    private String buildFormattedTime() {
        return String.format("%s:%s:%s.%s",
                numberFormatter.format(hours),
                numberFormatter.format(minutes),
                numberFormatter.format(seconds),
                milliFormatter.format(millis));
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

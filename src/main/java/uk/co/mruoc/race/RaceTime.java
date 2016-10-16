package uk.co.mruoc.race;

import java.text.NumberFormat;

public class RaceTime {

    private final NumberFormatter numberFormatter = new NumberFormatter(2);
    private final NumberFormatter milliFormatter = new NumberFormatter(3);

    private final long hours;
    private final long minutes;
    private final long seconds;
    private final long millis;

    private final String formattedTime;

    public RaceTime() {
        this(0);
    }

    public RaceTime(long totalMillis) {
        /*totalMillis = Math.abs(totalMillis);
        this.totalMillis = totalMillis;

        this.hours = toHours(totalMillis);
        totalMillis -= hoursInMillis();

        this.minutes = toMinutes(totalMillis);
        totalMillis -= minutesInMillis();

        this.seconds = toSeconds(totalMillis);
        totalMillis -= secondsInMillis();

        this.millis = totalMillis;*/

        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.millis = 0;

        this.formattedTime = buildFormattedTime();
    }

    public long getTotalMillis() {
        return 0;
    }

    public String asString() {
        return formattedTime;
    }

    private String buildFormattedTime() {
        return String.format("%s:%s:%s.%s",
                numberFormatter.format(hours),
                numberFormatter.format(minutes),
                numberFormatter.format(seconds),
                milliFormatter.format(millis));
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

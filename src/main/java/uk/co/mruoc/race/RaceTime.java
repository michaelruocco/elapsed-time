package uk.co.mruoc.race;

public class RaceTime {

    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECONDS = 1000;

    private final long totalMillis;
    private final long hours;
    private final long minutes;
    private final long seconds;
    private final long millis;

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

}

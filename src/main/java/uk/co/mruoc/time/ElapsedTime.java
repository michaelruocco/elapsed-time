package uk.co.mruoc.time;

public class ElapsedTime implements Comparable<ElapsedTime> {

    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECONDS = 1000;

    private final long totalMillis;
    private final long hours;
    private final long minutes;
    private final long seconds;
    private final long millis;

    public ElapsedTime() {
        this(0);
    }

    public ElapsedTime(long totalMillis) {
        this.totalMillis = totalMillis;
        long tempTotalMillis = totalMillis;

        this.hours = toHours(tempTotalMillis);
        tempTotalMillis -= hoursInMillis();

        this.minutes = toMinutes(tempTotalMillis);
        tempTotalMillis -= minutesInMillis();

        this.seconds = toSeconds(tempTotalMillis);
        tempTotalMillis -= secondsInMillis();

        this.millis = tempTotalMillis;
    }

    public long getTotalMillis() {
        return totalMillis;
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

    public boolean isEqualTo(ElapsedTime otherTime) {
        return this.totalMillis == otherTime.totalMillis;
    }

    public boolean isAfter(ElapsedTime otherTime) {
        return this.totalMillis > otherTime.totalMillis;
    }

    public boolean isBefore(ElapsedTime otherTime) {
        return this.totalMillis < otherTime.totalMillis;
    }

    public ElapsedTime add(ElapsedTime otherTime) {
        return add(otherTime.getTotalMillis());
    }

    public ElapsedTime add(long millisToAdd) {
        long combinedTotalMillis = totalMillis + millisToAdd;
        return new ElapsedTime(combinedTotalMillis);
    }

    public ElapsedTime subtract(ElapsedTime otherTime) {
        return subtract(otherTime.getTotalMillis());
    }

    public ElapsedTime subtract(long millisToSubtract) {
        long subtractedMillis = totalMillis - millisToSubtract;
        return new ElapsedTime(subtractedMillis);
    }

    public ElapsedTime difference(ElapsedTime otherTime) {
        return difference(otherTime.getTotalMillis());
    }

    public ElapsedTime difference(long otherMillis) {
        long differenceMillis = Math.abs(totalMillis - otherMillis);
        return new ElapsedTime(differenceMillis);
    }

    @Override
    public int compareTo(ElapsedTime otherTime) {
        return new Long(totalMillis).compareTo(otherTime.getTotalMillis());
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

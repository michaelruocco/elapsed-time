package uk.co.mruoc.time;


import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ElapsedTimeTest {

    private static final int ZERO = 0;
    private static final int ONE_MILLI = 1;
    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60000;
    private static final int ONE_HOUR = 3600000;
    private static final int TWO_MINUTES = ONE_MINUTE * 2;
    private static final String TWO_MINUTES_STRING = "00:02:00.000";

    @Test
    public void shouldReturnZeroMillisByDefault() {
        assertThat(new ElapsedTime().getTotalMillis()).isEqualTo(ZERO);
    }

    @Test
    public void shouldReturnTotalMillis() {
        assertThat(new ElapsedTime(ZERO).getTotalMillis()).isEqualTo(ZERO);
        assertThat(new ElapsedTime(ONE_MILLI).getTotalMillis()).isEqualTo(ONE_MILLI);
        assertThat(new ElapsedTime(ONE_SECOND).getTotalMillis()).isEqualTo(ONE_SECOND);
        assertThat(new ElapsedTime(ONE_MINUTE).getTotalMillis()).isEqualTo(ONE_MINUTE);
        assertThat(new ElapsedTime(ONE_HOUR).getTotalMillis()).isEqualTo(ONE_HOUR);
    }

    @Test
    public void shouldReturnHours() {
        assertThat(new ElapsedTime(ZERO).getHours()).isEqualTo(0);
        assertThat(new ElapsedTime(ONE_HOUR).getHours()).isEqualTo(1);
    }

    @Test
    public void shouldReturnMinutes() {
        assertThat(new ElapsedTime(ZERO).getMinutes()).isEqualTo(0);
        assertThat(new ElapsedTime(ONE_MINUTE).getMinutes()).isEqualTo(1);
    }

    @Test
    public void shouldReturnSeconds() {
        assertThat(new ElapsedTime(ZERO).getSeconds()).isEqualTo(0);
        assertThat(new ElapsedTime(ONE_SECOND).getSeconds()).isEqualTo(1);
    }

    @Test
    public void shouldReturnMillis() {
        assertThat(new ElapsedTime(ZERO).getMillis()).isEqualTo(0);
        assertThat(new ElapsedTime(ONE_MILLI).getMillis()).isEqualTo(1);
    }

    @Test
    public void isEqualToShouldReturnTrueIfTimesAreEqual() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_MINUTE);
        assertThat(time.isEqualTo(otherTime)).isTrue();
    }

    @Test
    public void isEqualTohouldReturnFalseIfTimesAreNotEqual() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_HOUR);
        assertThat(time.isEqualTo(otherTime)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfAfterOtherTime() {
        ElapsedTime time = new ElapsedTime(ONE_HOUR);
        ElapsedTime otherTime = new ElapsedTime(ONE_MINUTE);
        assertThat(time.isAfter(otherTime)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNotAfterOtherTime() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_MINUTE);
        assertThat(time.isAfter(otherTime)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfBeforeOtherTime() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_HOUR);
        assertThat(time.isBefore(otherTime)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNotBeforeOtherTime() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_MINUTE);
        assertThat(time.isBefore(otherTime)).isFalse();
    }

    @Test
    public void shouldAddTimeOneTimeToAnother() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);
        ElapsedTime result = oneMinute.add(oneMinute);
        assertThat(result.getTotalMillis()).isEqualTo(TWO_MINUTES);
    }

    @Test
    public void shouldAddMillisToTime() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);
        ElapsedTime result = oneMinute.add(ONE_MINUTE);
        assertThat(result.getTotalMillis()).isEqualTo(TWO_MINUTES);
    }

    @Test
    public void shouldSubtractOneTimeFromAnotherTogether() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);
        ElapsedTime result = oneMinute.subtract(oneMinute);
        assertThat(result.getTotalMillis()).isEqualTo(ZERO);
    }

    @Test
    public void shouldSubtractMillisFromTime() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);
        ElapsedTime result = oneMinute.subtract(ONE_MINUTE);
        assertThat(result.getTotalMillis()).isEqualTo(ZERO);
    }

    @Test
    public void shouldCalculateDifferentBetweenOneTimeAndAnother() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);
        ElapsedTime twoMinutes = new ElapsedTime(TWO_MINUTES);
        ElapsedTime result = oneMinute.difference(twoMinutes);
        assertThat(result.getTotalMillis()).isEqualTo(ONE_MINUTE);
    }

    @Test
    public void shouldCalculateDifferentBetweenTimeAndMillis() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);
        ElapsedTime result = oneMinute.difference(TWO_MINUTES);
        assertThat(result.getTotalMillis()).isEqualTo(ONE_MINUTE);
    }

    @Test
    public void shouldCompareOneTimeToAnother() {
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);

        assertThat(oneMinute.compareTo(new ElapsedTime(ONE_MINUTE))).isEqualTo(0);
        assertThat(oneMinute.compareTo(new ElapsedTime(ONE_SECOND))).isEqualTo(1);
        assertThat(oneMinute.compareTo(new ElapsedTime(ONE_HOUR))).isEqualTo(-1);
    }

    @Test
    public void toStringShouldReturnFormattedTime() {
        ElapsedTime time = new ElapsedTime(TWO_MINUTES);

        assertThat(time.toString()).isEqualTo(TWO_MINUTES_STRING);
    }

    @Test
    public void shouldBeConstructedFromString() {
        ElapsedTime time = new ElapsedTime(TWO_MINUTES_STRING);

        assertThat(time.getTotalMillis()).isEqualTo(TWO_MINUTES);
    }

    @Test
    public void equalsShouldReturnFalseIsComparedToNull() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        assertThat(time.equals(null)).isFalse();
    }

    @Test
    public void equalsShouldReturnFalseIfNotComparedToElapsedTime() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        assertThat(time.equals(new Object())).isFalse();
    }

    @Test
    public void hashCodeValueShouldBeTheSameIfTotalMillisecondsIsTheSame() {
        ElapsedTime time1 = new ElapsedTime(ONE_MINUTE);
        ElapsedTime time2 = new ElapsedTime(ONE_MINUTE);
        assertThat(time1.hashCode()).isEqualTo(time2.hashCode());
    }

    @Test
    public void hashCodeValueShouldBeDifferentIfTotalMillisecondsIsDifferent() {
        ElapsedTime time1 = new ElapsedTime(ONE_MINUTE);
        ElapsedTime time2 = new ElapsedTime(TWO_MINUTES);
        assertThat(time1.hashCode()).isNotEqualTo(time2.hashCode());
    }

    @Test
    public void equalsShouldReturnTrueIfTimesAreEqual() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_MINUTE);
        assertThat(time.equals(otherTime)).isTrue();
    }

    @Test
    public void equalsShouldReturnFalseIfTimesAreNotEqual() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_HOUR);
        assertThat(time.equals(otherTime)).isFalse();
    }

    @Test
    public void shouldSortCorrectlyInCollection() {
        ElapsedTime third = new ElapsedTime(ONE_HOUR);
        ElapsedTime first = new ElapsedTime(ONE_MILLI);
        ElapsedTime second = new ElapsedTime(ONE_MILLI);


        List<ElapsedTime> times = Arrays.asList(third, first, second);

        Collections.sort(times);

        assertThat(times.get(0)).isEqualTo(first);
        assertThat(times.get(1)).isEqualTo(second);
        assertThat(times.get(2)).isEqualTo(third);
    }

    @Test
    public void shouldObserveContainsCorrectlyInCollection() {
        ElapsedTime oneMilli1 = new ElapsedTime(ONE_MILLI);
        ElapsedTime oneMilli2 = new ElapsedTime(ONE_MILLI);
        ElapsedTime oneMinute = new ElapsedTime(ONE_MINUTE);

        List<ElapsedTime> times = Collections.singletonList(oneMilli1);

        assertThat(times.contains(oneMilli1)).isTrue();
        assertThat(times.contains(oneMilli2)).isTrue();
        assertThat(times.contains(oneMinute)).isFalse();
    }

}

package uk.co.mruoc.time;


import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ElapsedTimeTest {

    private static final int ZERO = 0;
    private static final int ONE_MILLI = 1;
    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60000;
    private static final int ONE_HOUR = 3600000;
    private static final int TWO_MINUTES = ONE_MINUTE * 2;

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
    public void shouldReturnTrueIfTimesAreEqual() {
        ElapsedTime time = new ElapsedTime(ONE_MINUTE);
        ElapsedTime otherTime = new ElapsedTime(ONE_MINUTE);
        assertThat(time.isEqualTo(otherTime)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfTimesAreNotEqual() {
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
    public void shouldAddTimesTogether() {
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

}

package uk.co.mruoc.race;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RaceTimeTest {

    private static final int ZERO = 0;
    private static final int ONE_MILLI = 1;
    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60000;
    private static final int ONE_HOUR = 3600000;

    @Test
    public void shouldReturnZeroMillisByDefault() {
        assertThat(new RaceTime().getTotalMillis()).isEqualTo(ZERO);
    }

    @Test
    public void shouldReturnTotalMillis() {
        assertThat(new RaceTime(ZERO).getTotalMillis()).isEqualTo(ZERO);
        assertThat(new RaceTime(ONE_MILLI).getTotalMillis()).isEqualTo(ONE_MILLI);
        assertThat(new RaceTime(ONE_SECOND).getTotalMillis()).isEqualTo(ONE_SECOND);
        assertThat(new RaceTime(ONE_MINUTE).getTotalMillis()).isEqualTo(ONE_MINUTE);
        assertThat(new RaceTime(ONE_HOUR).getTotalMillis()).isEqualTo(ONE_HOUR);
    }

    @Test
    public void shouldReturnHours() {
        assertThat(new RaceTime(ZERO).getHours()).isEqualTo(0);
        assertThat(new RaceTime(ONE_HOUR).getHours()).isEqualTo(1);
    }

    @Test
    public void shouldReturnMinutes() {
        assertThat(new RaceTime(ZERO).getMinutes()).isEqualTo(0);
        assertThat(new RaceTime(ONE_MINUTE).getMinutes()).isEqualTo(1);
    }

    @Test
    public void shouldReturnSeconds() {
        assertThat(new RaceTime(ZERO).getSeconds()).isEqualTo(0);
        assertThat(new RaceTime(ONE_SECOND).getSeconds()).isEqualTo(1);
    }

    @Test
    public void shouldReturnMillis() {
        assertThat(new RaceTime(ZERO).getMillis()).isEqualTo(0);
        assertThat(new RaceTime(ONE_MILLI).getMillis()).isEqualTo(1);
    }

    @Test
    public void shouldReturnTrueIfTimesAreEqual() {
        RaceTime time = new RaceTime(ONE_MINUTE);
        RaceTime otherTime = new RaceTime(ONE_MINUTE);
        assertThat(time.isEqualTo(otherTime)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfTimesAreNotEqual() {
        RaceTime time = new RaceTime(ONE_MINUTE);
        RaceTime otherTime = new RaceTime(ONE_HOUR);
        assertThat(time.isEqualTo(otherTime)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfAfterOtherTime() {
        RaceTime time = new RaceTime(ONE_HOUR);
        RaceTime otherTime = new RaceTime(ONE_MINUTE);
        assertThat(time.isAfter(otherTime)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNotAfterOtherTime() {
        RaceTime time = new RaceTime(ONE_MINUTE);
        RaceTime otherTime = new RaceTime(ONE_MINUTE);
        assertThat(time.isAfter(otherTime)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfBeforeOtherTime() {
        RaceTime time = new RaceTime(ONE_MINUTE);
        RaceTime otherTime = new RaceTime(ONE_HOUR);
        assertThat(time.isBefore(otherTime)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNotBeforeOtherTime() {
        RaceTime time = new RaceTime(ONE_MINUTE);
        RaceTime otherTime = new RaceTime(ONE_MINUTE);
        assertThat(time.isBefore(otherTime)).isFalse();
    }

}

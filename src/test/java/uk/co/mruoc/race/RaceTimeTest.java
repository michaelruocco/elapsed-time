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
    public void shouldReturnFormattedTimeString() {
        assertThat(new RaceTime(ZERO).asString()).isEqualTo("00:00:00.000");
        assertThat(new RaceTime(ONE_MILLI).asString()).isEqualTo("00:00:00.001");
        assertThat(new RaceTime(ONE_SECOND).asString()).isEqualTo("00:00:01.000");
        assertThat(new RaceTime(ONE_MINUTE).asString()).isEqualTo("00:01:00.000");
        assertThat(new RaceTime(ONE_HOUR).asString()).isEqualTo("01:00:00.000");
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

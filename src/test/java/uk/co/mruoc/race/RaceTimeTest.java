package uk.co.mruoc.race;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RaceTimeTest {

    @Test
    public void shouldInitialiseToNoElapsedTime() {
        RaceTime time = new RaceTime();

        assertThat(time.getTotalMillis()).isEqualTo(0);
    }

    @Test
    public void shouldReturnFormattedTimeString() {
        RaceTime time = new RaceTime();

        assertThat(time.asString()).isEqualTo("00:00:00.000");
    }

}

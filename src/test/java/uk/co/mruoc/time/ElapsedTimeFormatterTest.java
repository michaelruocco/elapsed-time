package uk.co.mruoc.time;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ElapsedTimeFormatterTest {

    private static final long TOTAL_MILLIS = 3661001;
    private static final String TIME_STRING = "01:01:01.001";

    private final ElapsedTimeFormatter formatter = new ElapsedTimeFormatter();

    @Test
    public void shouldFormatElapsedTime() {
        ElapsedTime time = new ElapsedTime(TOTAL_MILLIS);

        String result = formatter.format(time);

        assertThat(result).isEqualTo(TIME_STRING);
    }

}
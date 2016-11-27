package uk.co.mruoc.time;

import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6BDDAssertions.then;

public class ElapsedTimeConverterTest {

    private static final long TOTAL_MILLIS = 3661001;
    private static final String TIME_STRING = "01:01:01.001";

    private final ElapsedTimeConverter converter = new ElapsedTimeConverter();

    @Test
    public void shouldConvertRaceTimeToString() {
        ElapsedTime time = new ElapsedTime(TOTAL_MILLIS);
        assertThat(converter.toString(time)).isEqualTo(TIME_STRING);
    }

    @Test
    public void shouldConvertStringToRaceTime() {
        ElapsedTime time = converter.toElapsedTime(TIME_STRING);
        assertThat(time.getTotalMillis()).isEqualTo(TOTAL_MILLIS);
    }

    @Test
    public void shouldThrowExceptionIfStringIsNotValid() {
        String invalidTime = "invalid";

        when(converter).toElapsedTime(invalidTime);

        then(caughtException())
                .isInstanceOf(ElapsedTimeFormatException.class)
                .hasMessage(invalidTime)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

}
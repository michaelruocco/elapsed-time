package uk.co.mruoc.time;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class ElapsedTimeParserTest {

    private ElapsedTimeParser parser = new ElapsedTimeParser();

    @Test
    public void shouldParseValidTime() {
        String validTime = "01:02:03.004";

        ElapsedTime time = parser.parse(validTime);

        assertThat(time.getHours()).isEqualTo(1);
        assertThat(time.getMinutes()).isEqualTo(2);
        assertThat(time.getSeconds()).isEqualTo(3);
        assertThat(time.getMillis()).isEqualTo(4);
    }

    @Test
    public void shouldParseValidTimeWithTwoMillisecondDigitsSpecified() {
        String validTime = "01:02:03.10";

        ElapsedTime time = parser.parse(validTime);

        assertThat(time.getHours()).isEqualTo(1);
        assertThat(time.getMinutes()).isEqualTo(2);
        assertThat(time.getSeconds()).isEqualTo(3);
        assertThat(time.getMillis()).isEqualTo(100);
    }

    @Test
    public void shouldThrowExceptionForInvalidTime() {
        String invalidTime = "invalid";

        Throwable thrown = catchThrowable(() -> parser.parse(invalidTime));

        assertThat(thrown).isInstanceOf(ElapsedTimeFormatException.class)
                .hasCauseInstanceOf(NumberFormatException.class)
                .hasMessage(invalidTime);
    }

}

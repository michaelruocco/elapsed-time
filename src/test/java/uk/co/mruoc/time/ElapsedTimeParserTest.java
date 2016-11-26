package uk.co.mruoc.time;

import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6BDDAssertions.then;

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
    public void shouldThrowExceptionForInvalidTime() {
        String invalidTime = "invalid";

        when(parser).parse(invalidTime);

        then(caughtException())
                .isInstanceOf(ElapsedTimeFormatException.class)
                .hasMessage(invalidTime)
                .hasCauseInstanceOf(NumberFormatException.class);
    }

}

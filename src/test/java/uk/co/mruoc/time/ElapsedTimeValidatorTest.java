package uk.co.mruoc.time;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class ElapsedTimeValidatorTest {

    private final ElapsedTimeValidator validator = new ElapsedTimeValidator();

    @Test
    public void shouldReturnTrueIfTimeStringIsValid() {
        String validTime = "01:01:01.001";

        boolean result = validator.validate(validTime);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldThrowExceptionIfTimeStringIsInvalid() {
        String invalidTime = "invalid";

        Throwable thrown = catchThrowable(() -> validator.validate(invalidTime));

        assertThat(thrown).isInstanceOf(ElapsedTimeFormatException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessage(invalidTime);
    }

}

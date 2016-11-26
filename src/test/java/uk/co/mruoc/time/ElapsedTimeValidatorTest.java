package uk.co.mruoc.time;

import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6BDDAssertions.then;

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

        when(validator).validate(invalidTime);

        then(caughtException())
                .isInstanceOf(ElapsedTimeFormatException.class)
                .hasMessage(invalidTime)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

}

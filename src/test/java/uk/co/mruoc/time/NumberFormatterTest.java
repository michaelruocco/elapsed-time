package uk.co.mruoc.time;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class NumberFormatterTest {

    @Test
    public void shouldFormatNumberWithMinimumNumberOfIntegerDigits() {
        NumberFormatter formatter = new NumberFormatter(5);

        String result = formatter.format(1);

        assertThat(result).isEqualTo("00001");
    }

    @Test
    public void shouldFormatNumberWithDifferentMinimumNumberOfIntegerDigits() {
        NumberFormatter formatter = new NumberFormatter(3);

        String result = formatter.format(22);

        assertThat(result).isEqualTo("022");
    }

}

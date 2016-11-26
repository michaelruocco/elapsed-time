package uk.co.mruoc.time;

import java.text.NumberFormat;

public class NumberFormatter {

    private final NumberFormat format = NumberFormat.getNumberInstance();

    public NumberFormatter(int minimumIntegerDigits) {
        format.setGroupingUsed(false);
        format.setMinimumIntegerDigits(minimumIntegerDigits);
    }

    public String format(double value) {
        return format.format(value);
    }

}

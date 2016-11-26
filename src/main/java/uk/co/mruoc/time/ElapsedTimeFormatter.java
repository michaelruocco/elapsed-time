package uk.co.mruoc.time;

public class ElapsedTimeFormatter {

    private final NumberFormatter numberFormatter = new NumberFormatter(2);
    private final NumberFormatter milliFormatter = new NumberFormatter(3);

    public String format(ElapsedTime time) {
        return String.format("%s:%s:%s.%s",
                numberFormatter.format(time.getHours()),
                numberFormatter.format(time.getMinutes()),
                numberFormatter.format(time.getSeconds()),
                milliFormatter.format(time.getMillis()));
    }

}

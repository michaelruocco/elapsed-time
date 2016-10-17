package uk.co.mruoc.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.NumberFormat;

import static java.lang.Integer.parseInt;
import static uk.co.mruoc.time.ElapsedTime.MILLIS_IN_HOUR;
import static uk.co.mruoc.time.ElapsedTime.MILLIS_IN_MINUTE;
import static uk.co.mruoc.time.ElapsedTime.MILLIS_IN_SECONDS;

public class ElapsedTimeConverter {

    private final RaceTimeFormatter formatter = new RaceTimeFormatter();
    private final RaceTimeParser parser = new RaceTimeParser();
    private final RaceTimeValidator validator = new RaceTimeValidator();

    public ElapsedTime toElapsedTime(String input) {
        validator.validate(input);
        return parser.parser(input);
    }

    public String toString(ElapsedTime time) {
        return formatter.format(time);
    }

    private static class RaceTimeFormatter {

        private final NumberFormatter numberFormatter = new NumberFormatter(2);
        private final NumberFormatter milliFormatter = new NumberFormatter(3);

        private String format(ElapsedTime time) {
            return String.format("%s:%s:%s.%s",
                    numberFormatter.format(time.getHours()),
                    numberFormatter.format(time.getMinutes()),
                    numberFormatter.format(time.getSeconds()),
                    milliFormatter.format(time.getMillis()));
        }

        private static class NumberFormatter {

            private final NumberFormat format = NumberFormat.getNumberInstance();

            private NumberFormatter(int minimumIntegerDigits) {
                format.setMinimumIntegerDigits(minimumIntegerDigits);
            }

            private String format(double value) {
                return format.format(value);
            }

        }

    }

    private static class RaceTimeValidator {

        private void validate(String input) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss.SSS");
                formatter.parseLocalTime(input);
            } catch (IllegalArgumentException e) {
                throw new ElapsedTimeFormatException(input, e);
            }
        }

    }

    private static class RaceTimeParser {

        private ElapsedTime parser(String input) {
            long hours = toHours(input);
            long minutes = toMinutes(input);
            long seconds = toSeconds(input);
            long millis = toMillis(input);
            long totalMillis = calculateTotalMillis(hours, minutes, seconds, millis);
            return new ElapsedTime(totalMillis);
        }

        private int toHours(String s) {
            return parseInt(splitOnColon(s)[0]);
        }

        private int toMinutes(String s) {
            return parseInt(splitOnColon(s)[1]);
        }

        private String toSecondsAndMillis(String s) {
            return splitOnColon(s)[2];
        }

        private int toSeconds(String s) {
            String secondsAndMillis = toSecondsAndMillis(s);
            return parseInt(splitOnDot(secondsAndMillis)[0]);
        }

        private int toMillis(String s) {
            String secondsAndMillis = toSecondsAndMillis(s);
            return parseInt(splitOnDot(secondsAndMillis)[1]);
        }

        private String[] splitOnColon(String s) {
            return s.split(":");
        }

        private String[] splitOnDot(String s) {
            return s.split("\\.");
        }

        private long calculateTotalMillis(long hours, long minutes, long seconds, long millis) {
            long totalMillis = (hours * MILLIS_IN_HOUR);
            totalMillis += (minutes * MILLIS_IN_MINUTE);
            totalMillis += (seconds * MILLIS_IN_SECONDS);
            totalMillis += millis;
            return totalMillis;
        }

    }

}

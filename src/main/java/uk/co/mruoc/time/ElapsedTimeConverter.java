package uk.co.mruoc.time;

public class ElapsedTimeConverter {

    private final ElapsedTimeFormatter formatter = new ElapsedTimeFormatter();
    private final ElapsedTimeParser parser = new ElapsedTimeParser();
    private final ElapsedTimeValidator validator = new ElapsedTimeValidator();

    public ElapsedTime toElapsedTime(String input) {
        validator.validate(input);
        return parser.parse(input);
    }

    public long toMilliseconds(String input) {
        validator.validate(input);
        return parser.toMilliseconds(input);
    }

    public String toString(ElapsedTime time) {
        return formatter.format(time);
    }

}

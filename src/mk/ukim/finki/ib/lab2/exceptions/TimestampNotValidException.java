package mk.ukim.finki.ib.lab2.exceptions;

/**
 * This is an exception that is thrown when the timestamp is not valid.
 */
public class TimestampNotValidException extends RuntimeException {
    public TimestampNotValidException() {
        super("Timestamp not valid exception");
    }
}

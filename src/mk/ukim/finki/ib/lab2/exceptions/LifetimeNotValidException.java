package mk.ukim.finki.ib.lab2.exceptions;

/**
 * This is an exception that is thrown when the lifetime has been expired.
 */
public class LifetimeNotValidException extends RuntimeException {
    public LifetimeNotValidException() {
        super("Lifetime is expired.");
    }
}

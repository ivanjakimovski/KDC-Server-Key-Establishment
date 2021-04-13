package mk.ukim.finki.ib.lab2.exceptions;

/**
 * This is an exception that is thrown when ID B from Bob is not valid.
 */
public class IDBNotValidException extends RuntimeException {
    public IDBNotValidException() {
        super("ID B not valid exception.");
    }
}

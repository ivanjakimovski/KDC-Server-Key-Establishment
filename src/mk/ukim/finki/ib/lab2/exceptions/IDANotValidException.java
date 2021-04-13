package mk.ukim.finki.ib.lab2.exceptions;

/**
 * This is an exception that is thrown when ID A from Alice is not valid.
 */
public class IDANotValidException extends RuntimeException {
    public IDANotValidException() {
        super("ID A not valid exception.");
    }
}

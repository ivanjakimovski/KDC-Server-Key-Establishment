package mk.ukim.finki.ib.lab2.exceptions;

/**
 * This is an exception that is thrown when the nonce is not valid.
 */
public class NonceNotValidException extends RuntimeException {
    public NonceNotValidException() {
        super("Nonce is not valid.");
    }
}

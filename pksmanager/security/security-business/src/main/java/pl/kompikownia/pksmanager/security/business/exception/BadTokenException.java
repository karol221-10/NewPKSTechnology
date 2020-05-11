package pl.kompikownia.pksmanager.security.business.exception;

public class BadTokenException extends RuntimeException {

    public BadTokenException(String message) {
        super(message);
    }
}

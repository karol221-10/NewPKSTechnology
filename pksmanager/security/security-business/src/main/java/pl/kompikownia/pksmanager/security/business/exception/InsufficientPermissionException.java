package pl.kompikownia.pksmanager.security.business.exception;

public class InsufficientPermissionException extends RuntimeException {

    public InsufficientPermissionException(String message) {
        super(message);
    }
}

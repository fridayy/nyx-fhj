package ninja.harmless.nyx.publish.exception;

/**
 * @author bnjm@harmless.ninja - 3/10/17.
 */
public class PublishRequestDeniedException extends RuntimeException {
    public PublishRequestDeniedException() {
        super();
    }

    public PublishRequestDeniedException(String message) {
        super(message);
    }

    public PublishRequestDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PublishRequestDeniedException(Throwable cause) {
        super(cause);
    }

    protected PublishRequestDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

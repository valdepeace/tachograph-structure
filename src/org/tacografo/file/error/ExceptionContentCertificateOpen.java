package org.tacografo.file.error;

/**
 * Created by negrero on 04/07/2016.
 */
public class ExceptionContentCertificateOpen extends Exception {
    public ExceptionContentCertificateOpen() {
        super();
    }

    public ExceptionContentCertificateOpen(String message) {
        super(message);
    }

    public ExceptionContentCertificateOpen(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionContentCertificateOpen(Throwable cause) {
        super(cause);
    }

    protected ExceptionContentCertificateOpen(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

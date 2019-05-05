package org.tacografo.file.error;

/**
 * Created by negrero on 04/07/2016.
 */
public class ExceptionContentCertificateHash extends Exception {


    public ExceptionContentCertificateHash() {
        super();
    }

    public ExceptionContentCertificateHash(String message) {
        super(message);
    }

    public ExceptionContentCertificateHash(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionContentCertificateHash(Throwable cause) {
        super(cause);
    }

    protected ExceptionContentCertificateHash(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

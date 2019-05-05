package com.tachographStructure.file.error;

/**
 * Created by negrero on 04/07/2016.
 */
public class ExceptionSignatureHash extends Exception {

    public ExceptionSignatureHash() {
        super();
    }

    public ExceptionSignatureHash(String message) {
        super(message);
    }

    public ExceptionSignatureHash(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionSignatureHash(Throwable cause) {
        super(cause);
    }

    protected ExceptionSignatureHash(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

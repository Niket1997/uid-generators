package org.niket.exceptions;

public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException() {
        super("VALIDATION FAILED");
    }

    public ValidationFailedException(String message) {
        super(message);
    }
}

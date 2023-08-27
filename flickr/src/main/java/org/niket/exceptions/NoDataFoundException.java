package org.niket.exceptions;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException() {
        super("no data found in the table");
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}

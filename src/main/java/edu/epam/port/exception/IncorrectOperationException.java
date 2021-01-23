package edu.epam.port.exception;

public class IncorrectOperationException extends Exception{
    public IncorrectOperationException() {
    }

    public IncorrectOperationException(String message) {
        super(message);
    }

    public IncorrectOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectOperationException(Throwable cause) {
        super(cause);
    }
}

package org.sattv.exception;

public class InvalidInputException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *
     *  This exception is used through out the system in case any of the user input
     *                is not allowed in the system
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

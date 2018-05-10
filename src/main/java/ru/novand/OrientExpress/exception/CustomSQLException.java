package ru.novand.OrientExpress.exception;

/*
 * A custom sql exception to be thrown.
 */

public class CustomSQLException extends RuntimeException {
    public CustomSQLException(String message)
    {

    }
    public CustomSQLException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
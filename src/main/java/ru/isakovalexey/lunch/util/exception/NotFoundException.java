package ru.isakovalexey.lunch.util.exception;

/**
 * Created by user on 30.05.2017.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

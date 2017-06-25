package ru.isakovalexey.lunch.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {
    private static final String EXCEPTION_COMMON_NOT_FOUND = "exception.common.notFound";

    //  http://stackoverflow.com/a/22358422/548473
    public NotFoundException(String message) {
        super(EXCEPTION_COMMON_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}

package com.test.util;

/**
 * User: Nikitos
 * Date: 07.11.2009
 * Time: 23:14:01
 * Base exception for use in project
 */
public class BaseException extends Exception {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

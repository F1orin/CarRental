/*
 * SessionTimeoutException.java 2014/04/21
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.exceptions;

/**
 * Custom exception class for handling the session timeouts
 *
 * @author Florin
 */
public class SessionTimeoutException extends Exception {

    public SessionTimeoutException(String message) {
        super(message);
    }

    public SessionTimeoutException(Throwable cause) {
        super(cause);
    }

}

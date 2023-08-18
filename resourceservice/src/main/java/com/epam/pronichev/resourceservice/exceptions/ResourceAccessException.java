package com.epam.pronichev.resourceservice.exceptions;

public class ResourceAccessException extends RuntimeException {

    public ResourceAccessException(String message) {
        super(message);
    }
}
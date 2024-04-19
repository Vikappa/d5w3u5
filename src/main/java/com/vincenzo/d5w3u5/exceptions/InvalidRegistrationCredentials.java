package com.vincenzo.d5w3u5.exceptions;

public class InvalidRegistrationCredentials extends RuntimeException {
    public InvalidRegistrationCredentials(String message) {
        super(message);
    }
}

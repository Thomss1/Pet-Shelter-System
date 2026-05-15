package com.petshelter.petsheltersystem;

public class InvalidPetException extends Exception {

    public InvalidPetException(String message) {
        super(message);
    }

    public InvalidPetException(String message, Throwable cause) {
        super(message, cause);
    }
}

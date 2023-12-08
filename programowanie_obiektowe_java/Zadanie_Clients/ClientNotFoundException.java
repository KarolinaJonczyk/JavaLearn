package com.capgemini.programowanie.obiektowe;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String error_message) {
        super(error_message);
    }
}

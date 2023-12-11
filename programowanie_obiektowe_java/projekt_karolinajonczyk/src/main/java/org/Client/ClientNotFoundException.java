package org.Client;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String errorMessage) {
        super(errorMessage != null && !errorMessage.isEmpty() ? errorMessage : "Client not found!");
    }
}


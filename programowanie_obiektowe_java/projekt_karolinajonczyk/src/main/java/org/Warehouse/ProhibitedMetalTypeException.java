package org.Warehouse;

public class ProhibitedMetalTypeException extends RuntimeException {
    public ProhibitedMetalTypeException(String error_metal_message) {
        super(error_metal_message);
    }
}

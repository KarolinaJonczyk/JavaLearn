package org.Warehouse;

public class FullWarehouseException extends RuntimeException {
    public FullWarehouseException(String errorMessage) {
        super((errorMessage != null && !errorMessage.isEmpty()) ? errorMessage : "Warehouse is full!");
    }
}

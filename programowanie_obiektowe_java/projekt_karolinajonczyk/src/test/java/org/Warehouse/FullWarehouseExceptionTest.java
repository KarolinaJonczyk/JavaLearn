package org.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FullWarehouseExceptionTest {
    @Test
    void TestErrorMessage() {
        String errorMessage = "Warehouse is full!";
        FullWarehouseException exception = new FullWarehouseException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void TestNullMessage() {
        FullWarehouseException exception = new FullWarehouseException(null);

        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }


    @Test
    void TestEmptyMessage() {
        FullWarehouseException exception = new FullWarehouseException("");

        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }


}
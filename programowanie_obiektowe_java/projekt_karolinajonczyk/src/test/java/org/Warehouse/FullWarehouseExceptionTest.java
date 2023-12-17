package org.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FullWarehouseExceptionTest {
    @Test
    void shouldSetErrorMessageWhenCreatingException() {
        String errorMessage = "Warehouse is full!";
        FullWarehouseException exception = new FullWarehouseException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void shouldSetDefaultMessageWhenCreatingExceptionWithNullMessage() {
        FullWarehouseException exception = new FullWarehouseException(null);

        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }


    @Test
    void shouldSetDefaultMessageWhenCreatingExceptionWithEmptyMessage() {
        FullWarehouseException exception = new FullWarehouseException("");

        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }


}
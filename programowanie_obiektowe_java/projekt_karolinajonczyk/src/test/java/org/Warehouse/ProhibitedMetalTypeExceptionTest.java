package org.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProhibitedMetalTypeExceptionTest {
    @Test
    void shouldSetErrorMessageWhenCreatingExceptionWithMessage() {
        String errorMessage = "Prohibited metal type!";
        ProhibitedMetalTypeException exception = new ProhibitedMetalTypeException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void shouldSetNullMessageWhenCreatingExceptionWithNullMessage() {
        ProhibitedMetalTypeException exception = new ProhibitedMetalTypeException(null);

        assertNull(exception.getMessage());
    }


    @Test
    void shouldSetEmptyMessageWhenCreatingExceptionWithEmptyMessage() {
        ProhibitedMetalTypeException exception = new ProhibitedMetalTypeException("");
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().isEmpty());
    }

}
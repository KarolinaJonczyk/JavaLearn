package org.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProhibitedMetalTypeExceptionTest {
    @Test
    void TestErrorMessage() {
        String errorMessage = "Prohibited metal type!";
        ProhibitedMetalTypeException exception = new ProhibitedMetalTypeException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void TestNullMessage() {
        ProhibitedMetalTypeException exception = new ProhibitedMetalTypeException(null);

        assertNull(exception.getMessage());
    }


    @Test
    void TestEmptyMessage() {
        ProhibitedMetalTypeException exception = new ProhibitedMetalTypeException("");
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().isEmpty());
    }

}
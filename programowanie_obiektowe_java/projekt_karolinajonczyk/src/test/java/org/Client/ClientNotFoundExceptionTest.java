package org.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientNotFoundExceptionTest {

    //test sprawdza, czy konstruktor klasy  ClientNotFoundExceptionpoprawnie
    // pooprawnie ustawia przekazany komunikat błędu.
    @Test
    void shouldSetErrorMessageInConstructor() {
        String errorMessage = "Client not found!";
        ClientNotFoundException exception = new ClientNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    // test spawdza czy konstrutor klasy ClientNotFoundException ustawia domyślny komunikat,
// gdy dostarczony komunikat jest pusty (jest długości zero).
    @Test
    void shouldSetDefaultMessageForEmptyMessage() {
        ClientNotFoundException exception = new ClientNotFoundException("");

        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }
// test spawdza czy konstrutor klasy ClientnotFoundException ustawia domyślny komunikat,
// gdy dostarczony komunikat jest null.
    @Test
    void shouldSetDefaultMessageForNullMessage() {
        ClientNotFoundException exception = new ClientNotFoundException(null);

        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }

}
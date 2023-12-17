package org.Client;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ClassClientsTest {


    @Test
    void shouldGetClientsInMapAfterAddingClients() {
        ClassClients clients = new ClassClients();
        String clientId1 = clients.createNewClient("Eric", "Clapton");
        String clientId2 = clients.createNewClient("Carlos", "Santana");
        Map<String,ClientInformations> clientsInMap = clients.getClientsInMap();
        assertFalse(clientsInMap.isEmpty());

        assertTrue(clientsInMap.containsKey(clientId1));
        assertTrue(clientsInMap.containsKey(clientId2));

        assertEquals("Eric", clientsInMap.get(clientId1).getFirstName());
        assertEquals("Clapton", clientsInMap.get(clientId1).getLastName());

        assertEquals("Carlos", clientsInMap.get(clientId2).getFirstName());
        assertEquals("Santana", clientsInMap.get(clientId2).getLastName());
    }


    @Test
    void shouldCreateNewClientWithCorrectInformation() {
        ClassClients clients = new ClassClients();

        String clientId = clients.createNewClient("Billy", "Idol");

        assertTrue(clients.getClientsInMap().containsKey(clientId));

        ClientInformations clientInfo = clients.getClientsInMap().get(clientId);
        assertEquals("Billy", clientInfo.getFirstName());
        assertEquals("Idol", clientInfo.getLastName());
        assertFalse(clientInfo.isPremium());
        assertNotNull(clientInfo.getCreationDate());
    }

    @Test
    void shouldActivatePremiumAccountForClient() throws ClientNotFoundException {
        ClassClients clients = new ClassClients();
        String clientId = clients.createNewClient("Steven", "Tyler");
        clients.activatePremiumAccount(clientId);
        assertTrue(clients.getClientsInMap().get(clientId).isPremium());
    }

    @Test
    void shouldGetClientFullName() throws ClientNotFoundException {
        ClassClients clients = new ClassClients();
        String clientId = clients.createNewClient("Steve", "Stevens");
        assertEquals("Steve Stevens", clients.getClientFullName(clientId));
    }
    @Test
    void shouldThrowExceptionForNonExistentClientFullName() {
        ClassClients clients = new ClassClients();
        assertThrows(ClientNotFoundException.class, () -> clients.getClientFullName("nonExistentClientId"));
    }

    @Test
    void shouldGetClientCreationDate() throws ClientNotFoundException {
        ClassClients clients = new ClassClients();
        String clientId = clients.createNewClient("Brian", "May");
        LocalDate expectedCreationDate = LocalDate.now();
        LocalDate actualCreationDate = clients.getClientCreationDate(clientId);
        assertEquals(expectedCreationDate, actualCreationDate);
        assertNotNull(clients.getClientCreationDate(clientId));
    }

@Test
void shouldReturnTrueForPremiumClientAfterActivation() throws ClientNotFoundException {
    ClassClients clients = new ClassClients();
    String clientId = clients.createNewClient("Jimmy", "Page");
    System.out.println("Before premium activation: " + clients.getClientsInMap().get(clientId));
    clients.activatePremiumAccount(clientId);
    System.out.println("After premium activation: " + clients.getClientsInMap().get(clientId));
    assertTrue(clients.isPremiumClient(clientId));
}
    @Test
    void shouldGetCorrectNumberOfClients() {
        ClassClients clients = new ClassClients();
        clients.createNewClient("Billy", "Idol");
        clients.createNewClient("Jimmy", "Page");
        clients.createNewClient("Joe", "Perry");
        clients.createNewClient("Ian", "Gillan");
        assertEquals(4, clients.getNumberOfClients());

    }

    @Test
    void shouldGetCorrectNumberOfPremimClients() {
        ClassClients clients = new ClassClients();
        String premiumClientA = clients.createNewClient("Lars", "Ulrich");
        clients.activatePremiumAccount(premiumClientA);
        String premiumClientB = clients.createNewClient("Axl", "Rose");
        clients.activatePremiumAccount(premiumClientB);
        clients.createNewClient("Sebastian", "Bach");
        clients.createNewClient("David", "Gilmour");
        assertEquals(2, clients.getNumberOfPremiumClients());
        clients.createNewClient("Ringo", "Starr");
        String premiumClientC = clients.createNewClient("Mick", "Jagger");
        clients.activatePremiumAccount(premiumClientC);
        assertEquals(3, clients.getNumberOfPremiumClients());


    }
}
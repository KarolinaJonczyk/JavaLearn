package org.Warehouse;
import org.Client.ClientNotFoundException;
import org.Client.ClassClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClassWarehouseTest {
    private ClassWarehouse warehouse;
    private ClassClients clients;

    @BeforeEach
    void setUp() {
        clients = new ClassClients();
        warehouse = new ClassWarehouse(clients);
    }
    @Test
    void addMetalIngot() throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        String clientId = clients.createNewClient("Paul", "McCartney");
        SupportedMetalType metalType = SupportedMetalType.COPPER;
        warehouse.addMetalIngot(clientId, metalType, 50);
        assertTrue(warehouse.getMetalTypesToMassStoredByClient(clientId).containsKey(metalType));
        assertEquals(50, warehouse.getMetalTypesToMassStoredByClient(clientId).get(metalType));
    }

    @Test
    void addMetalIngotThrowsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> warehouse.addMetalIngot("nonexistentClient", SupportedMetalType.COPPER, 30));
    }

    @Test
    void addMetalIngotThrowsFullWarehouseException() throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        String clientId = clients.createNewClient("Keith", "Richards");

        for (int i = 0; i < 3; i++) {
            warehouse.addMetalIngot(clientId, SupportedMetalType.COPPER, 100000);
        }
        assertThrows(FullWarehouseException.class, () -> warehouse.addMetalIngot(clientId, SupportedMetalType.COPPER, 1000));
    }
    @Test
    void getMetalTypesToMassStoredByClient() throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        String clientId = clients.createNewClient("Jimmy", "Page");
        SupportedMetalType metalTYpe = SupportedMetalType.IRON;
        warehouse.addMetalIngot(clientId, metalTYpe, 88);
        assertEquals(1, warehouse.getMetalTypesToMassStoredByClient(clientId).size());
        assertTrue(warehouse.getMetalTypesToMassStoredByClient(clientId).containsKey(metalTYpe));
        assertEquals(88, warehouse.getMetalTypesToMassStoredByClient(clientId).get(metalTYpe));
    }

    @Test
    void getTotalVolumeOccupiedByClient() throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        String clientId = clients.createNewClient("Alex", "Kapranos");
        SupportedMetalType metalTypeA = SupportedMetalType.COPPER;
        SupportedMetalType metalTypeB = SupportedMetalType.GOLD;
        warehouse.addMetalIngot(clientId, metalTypeB, 70);
        warehouse.addMetalIngot(clientId, metalTypeA, 700);
        assertEquals(770, warehouse.getTotalVolumeOccupiedByClient(clientId));
    }
    @Test
    void getTotalVolumeOccupiedByClientWithoutStoredMetal() throws ClientNotFoundException {
        String clientId = clients.createNewClient("Carlos", "Santana");
        assertEquals(0, warehouse.getTotalVolumeOccupiedByClient(clientId));
    }

    @Test
    void getStoredMetalTypesByClient() throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        String clientId = clients.createNewClient("Tommy", "Emmanuel");
        SupportedMetalType metalTypeA = SupportedMetalType.COPPER;
        SupportedMetalType metalTypeB = SupportedMetalType.SILVER;
        warehouse.addMetalIngot(clientId, metalTypeA, 100);
        warehouse.addMetalIngot(clientId, metalTypeB, 90);
        assertEquals(2, warehouse.getStoredMetalTypesByClient(clientId).size());
        assertTrue(warehouse.getStoredMetalTypesByClient(clientId).contains(metalTypeA));
        assertTrue(warehouse.getStoredMetalTypesByClient(clientId).contains(metalTypeB));

        SupportedMetalType metalTypeC = SupportedMetalType.GOLD;
        warehouse.addMetalIngot(clientId, metalTypeC, 95);
        assertEquals(3, warehouse.getStoredMetalTypesByClient(clientId).size());
        assertTrue(warehouse.getStoredMetalTypesByClient(clientId).contains(metalTypeC));
    }
}
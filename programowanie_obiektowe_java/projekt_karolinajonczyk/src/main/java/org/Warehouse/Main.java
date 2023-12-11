package org.Warehouse;

import org.Client.ClassClients;
import org.Client.ClientNotFoundException;

public class Main {

    public static void main(String[] args) {
        ClassClients classClients = new ClassClients();

        ClassWarehouse classWarehouse = new ClassWarehouse(classClients);

        try {
            String clientId = classClients.createNewClient("Alice", "Cooper");
            classClients.activatePremiumAccount(clientId);
            classWarehouse.addMetalIngot(clientId, SupportedMetalType.COPPER, 100.0);
            classWarehouse.addMetalIngot(clientId, SupportedMetalType.IRON, 150.0);

            System.out.println("Metal types and masses stored by client:");
            System.out.println(classWarehouse.getMetalTypesToMassStoredByClient(clientId));

            System.out.println("Total volume occupied by client: " + classWarehouse.getTotalVolumeOccupiedByClient(clientId));

            System.out.println("Stored metal types by client: " + classWarehouse.getStoredMetalTypesByClient(clientId));
        } catch (ClientNotFoundException | ProhibitedMetalTypeException | FullWarehouseException e) {
            e.printStackTrace();
        }
    }
}

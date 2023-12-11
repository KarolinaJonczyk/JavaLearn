package org.Warehouse;

import org.Client.ClientNotFoundException;
import org.Client.ClassClients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ClassWarehouse implements Warehouse {

    private final Map<String, Map<SupportedMetalType, Double>> warehouseStorage = new HashMap<>();
    private final ClassClients classClients;

    public ClassWarehouse(ClassClients classClients) {
        this.classClients = classClients;
    }

    @Override
    public void addMetalIngot(String clientId, SupportedMetalType metalType, double mass)
            throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        if (!classClients.getClientsInMap().containsKey(clientId)) {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }

        if (isWarehouseFull(clientId)) {
            throw new FullWarehouseException("Warehouse is full for client: " + clientId);
        }

        warehouseStorage.computeIfAbsent(clientId, k -> new HashMap<>())
                .merge(metalType, mass, Double::sum);
    }

    @Override
    public Map<SupportedMetalType, Double> getMetalTypesToMassStoredByClient(String clientId) {
        return warehouseStorage.getOrDefault(clientId, new HashMap<>());
    }

    @Override
    public double getTotalVolumeOccupiedByClient(String clientId) {
        return warehouseStorage.getOrDefault(clientId, new HashMap<>())
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public List<SupportedMetalType> getStoredMetalTypesByClient(String clientId) {
        return List.copyOf(warehouseStorage.getOrDefault(clientId, new HashMap<>()).keySet());
    }

    //moje dodatkowe prywatne metody: sprawdzanie czy magazyn klienta jest pełny, zliczanie ilu klientów nie ma magazynu,
    // czy magazyn klienta jest pusty, nalicza karę za przepełniony magazyn, wypełnianie magazynu na max jednym materiałem
    // usuwanie danego materiału z magazynu, sprawdzanie jaki materiał występuje w magazynie w największej ilości
    // waga wszystkich magazynowanych obiektów przez wszystkich klientów.
    private boolean isMetalTypeAllowed(SupportedMetalType metalType) {
          Set<SupportedMetalType> allowedMetalTypes = Set.of(
                SupportedMetalType.COPPER,
                SupportedMetalType.IRON,
                SupportedMetalType.SILVER,
                SupportedMetalType.GOLD
        );

        return allowedMetalTypes.contains(metalType);
    }

    private static final double MAX_WAREHOUSE_CAPACITY = 300000; //max pojemność w kg magazynu na klienta
    private boolean isWarehouseFull(String clientId) {
        if (warehouseStorage.containsKey(clientId)) {
            double totalVolume = getTotalVolumeOccupiedByClient(clientId);
            return totalVolume >= MAX_WAREHOUSE_CAPACITY;
        } else {
            return false;
        }
    }

    private int getNumberOfClientsWithoutWarehouse() {
        long clientsWithoutWarehouse = classClients.getClientsInMap().keySet().stream()
                .filter(clientId -> !warehouseStorage.containsKey(clientId)).count();
        return (int) clientsWithoutWarehouse;
    }

    private boolean isClientWarehouseEmpty(String clientId) {
        return warehouseStorage.getOrDefault(clientId, Map.of()).isEmpty();
    }
    private double calculateExcessPenalty(String clientId) {
        double totalVolume = getTotalVolumeOccupiedByClient(clientId);
        double excessVolume = Math.max(0, totalVolume - MAX_WAREHOUSE_CAPACITY);

        // Obliczanie kary za każde 100kg ponad maksymalną pojemność - 50 zł kary na 100kg przekroczonej masy
        return Math.ceil(excessVolume / 100) * 50;
    }

    private void fillWarehouseToMax(String clientId, SupportedMetalType metalType) throws ProhibitedMetalTypeException, FullWarehouseException {
        if (!isMetalTypeAllowed(metalType)) {
            throw new ProhibitedMetalTypeException("Prohibited metal type: " + metalType);
        }
        if (isWarehouseFull(clientId)) {
            throw new FullWarehouseException("Warehouse is already full for client: " + clientId);
        }
        double currentVolume = getTotalVolumeOccupiedByClient(clientId);
        double remainingCapacity = MAX_WAREHOUSE_CAPACITY - currentVolume;
        double requiredMass = Math.min(metalType.getDensity() * remainingCapacity, MAX_WAREHOUSE_CAPACITY);
        addMetalIngot(clientId, metalType, requiredMass);
    }

    private void removeMetalTypeFromWarehouse(String clientId, SupportedMetalType metalType) throws ClientNotFoundException {
        if (!warehouseStorage.containsKey(clientId)) {
            throw new ClientNotFoundException("Client not found in warehouse: " + clientId);
        }
        Map<SupportedMetalType, Double> clientWarehouse = warehouseStorage.get(clientId);
        clientWarehouse.remove(metalType);
    }

    private SupportedMetalType getMostAbundantMetalType(String clientId) throws ClientNotFoundException {
        if (!warehouseStorage.containsKey(clientId)) {
            throw new ClientNotFoundException("Client not found in warehouse: " + clientId);
        }
        Map<SupportedMetalType, Double> clientWarehouse = warehouseStorage.get(clientId);
        if (clientWarehouse.isEmpty()) {
            return null;
        }
        return clientWarehouse.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private double getTotalWarehouseWeight() {
        double totalWeight = 0.0;

        for (Map<SupportedMetalType, Double> clientWarehouse : warehouseStorage.values()) {
            for (double weight : clientWarehouse.values()) {
                totalWeight += weight;
            }
        }

        return totalWeight;
    }

}

package com.capgemini.programowanie.obiektowe;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class ClassClients implements Clients{

    //utworzona zostaje HashMap-a do przechowywania klientów w "bazie".
    private final Map<String, ClientInformations> clientsInMap = new HashMap <>();
    //ten kawałek jest dodatkowy do moich dodatkowych prywatnych metod z końca pliku/kodu
    private int totalPremiumPurchases = 0;



    // ustalane są metody publiczne (z interfejsu Clients) i prywatne (moje dodatkowe)
    @Override
    public String createNewClient(String firstName, String lastName){
        System.out.println("Creating new client: " + firstName + " " + lastName);
        LocalDate creationDate = LocalDate.now(); //pobieranie aktualnej daty
        String clientId = generateClientID(firstName, lastName);
        clientsInMap.put(clientId, new ClientInformations(firstName, lastName, creationDate, false));
        System.out.println("Created client info: " + clientId);
        return clientId;
    }

    private String generateClientID(String firstName, String lastName) {
        String letters = getTwo(firstName) + getTwo(lastName);  //pobranie 2 liter początkowych z imiemia i nazwiska klienta
        String numbers = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8); //UUID do 8 miejsca
        return (letters + numbers).toUpperCase(); //chcemy mieć ID z wielkich liter
    }
    // implementacja metody, która pobiera nam 2 pierwsze litery z dowolnego słowa
    private String getTwo(String name) {
        return name.substring(0, Math.min(name.length(), 2)).toUpperCase();
    }

    private static class ClientInformations {
        private String firstName;
        private String lastName;
        private LocalDate creationDate;
        private boolean premium;
        private LocalDate endDate;
        private int premiumPurchases;

        private PremiumPeriod premiumPeriod;



        public ClientInformations(String firstName, String lastName, LocalDate creationDate, boolean premium) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.creationDate = creationDate;
            this.premium = premium;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public LocalDate getCreationDate() {
            return creationDate;
        }

        public boolean isPremium() {
            return premium;
        }

        public void setPremium(boolean premium) {
            this.premium = premium;
        }

        public void setPremiumEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public LocalDate getPremiumEndDate() {
            return endDate;
        }

        public int getPremiumPurchases() {
            return premiumPurchases;
        }

        public void incrementPremiumPurchases() {
            premiumPurchases++;
        }
        public PremiumPeriod getPremiumPeriod() {
            return premiumPeriod;
        }

        public void setPremiumPeriod(PremiumPeriod premiumPeriod) {
            this.premiumPeriod = premiumPeriod;
        }

    }
    @Override
    public String activatePremiumAccount(String clientId) throws  ClientNotFoundException{
        if (clientsInMap.containsKey(clientId)) {
            ClientInformations clientInfo = clientsInMap.get(clientId);
            clientInfo.setPremium(true);
            System.out.println("Activating premium account for client with ID: " + clientId);
            return clientId;

        }else{
            throw new ClientNotFoundException("Client not found in premium base: " + clientId);
        }
    }

    @Override
    public String getClientFullName(String clientId) throws ClientNotFoundException{
        if (clientsInMap.containsKey(clientId)) {
            ClientInformations clientInfo = clientsInMap.get(clientId);
            return clientInfo.getFirstName() + " " + clientInfo.getLastName();
        }else{
        throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }

    @Override
    public LocalDate getClientCreationDate(String clientId) throws ClientNotFoundException {
        if (clientsInMap.containsKey(clientId)) {
            return clientsInMap.get(clientId).getCreationDate();
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }

    @Override
    public boolean isPremiumClient(String clientId) throws ClientNotFoundException{
        if (clientsInMap.containsKey(clientId)) {
            return  clientsInMap.get(clientId).isPremium();
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }

    @Override
    public int getNumberOfClients() {
        return  clientsInMap.size(); //wielkosć hashmapy to ilość klientów w "bazie"
    }

    @Override
    public int getNumberOfPremiumClients() {
        return (int) clientsInMap.values().stream().filter(ClientInformations::isPremium).count();
    }

    // tutaj zamieszczam dodatkowe moje metody prywatne (nie ma ich w Clients.java). Są to: deaktywowanie premium, aktualizacja statusu klienta
   // ustawianie okresu trwania premium (1mies/6mies/12mies/nielimietowane), wyliczania do kiedy trwa premium, usuwanie klienta z bazy
    // zliczanie ilu klientów ma plan podstawowy (bez premium), zliczanie ile razy klient kupił premium
    private boolean deactivatePremiumAccount(String clientId) throws ClientNotFoundException {
        if (clientsInMap.containsKey(clientId)) {
            ClientInformations clientInfo = clientsInMap.get(clientId);
            clientInfo.setPremium(false);
            return true;
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }

    private boolean updateClientInfo(String clientId, String newFirstName, String newLastName) throws ClientNotFoundException {
        if (clientsInMap.containsKey(clientId)) {
            ClientInformations clientInfo = clientsInMap.get(clientId);
            clientInfo.setFirstName(newFirstName);
            clientInfo.setLastName(newLastName);
            return true;
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }

    private void setPremiumPeriod(String clientId, PremiumPeriod premiumPeriod) throws ClientNotFoundException {
        if (clientsInMap.containsKey(clientId)) {
            ClientInformations clientInfo = clientsInMap.get(clientId);
            switch (premiumPeriod) {
                case ONE_MONTH:
                    setPremiumEndDate(clientInfo, clientInfo.getCreationDate().plusMonths(1));
                    break;
                case SIX_MONTHS:
                    setPremiumEndDate(clientInfo, clientInfo.getCreationDate().plusMonths(6));
                    break;
                case ONE_YEAR:
                    setPremiumEndDate(clientInfo, clientInfo.getCreationDate().plusYears(1));
                    break;
                case UNLIMITED:
                    setPremiumEndDate(clientInfo, LocalDate.MAX);
                    break;
                default:
                    throw new UnsupportedOperationException("Wrong premium period: " + premiumPeriod + ". Choose 1m/6m/1y/unlimited.");
            }
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }



    private void setPremiumEndDate(ClientInformations clientInfo, LocalDate endDate) {
        clientInfo.setPremiumEndDate(endDate);
    }

    private LocalDate calculatePremiumEndDate(ClientInformations clientInfo) {
        if (clientInfo.isPremium()) {
            LocalDate currentEndDate = clientInfo.getPremiumEndDate();
            return (currentEndDate != null && currentEndDate.isAfter(LocalDate.now())) ? currentEndDate : LocalDate.now();
        }
        return LocalDate.now();
    }

    private boolean removeClient(String clientId) throws ClientNotFoundException {
        if (clientsInMap.containsKey(clientId)) {
            clientsInMap.remove(clientId);
            return true;
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }

    private int getNumberOfBasicClients() {
        return getNumberOfClients() - getNumberOfPremiumClients();
    }

    private int getNumberOfPremiumPurchases(String clientId) throws ClientNotFoundException {
        if (clientsInMap.containsKey(clientId)) {
            return clientsInMap.get(clientId).getPremiumPurchases();
        } else {
            throw new ClientNotFoundException("Client not found in base: " + clientId);
        }
    }
    private int getTotalPremiumPurchases() {
        return totalPremiumPurchases;
    }
    private int getNumberOfUnlimitedPremiumClients() {
        return (int) clientsInMap.values().stream()
                .filter(ClientInformations::isPremium)
                .filter(client -> client.getPremiumPeriod() == PremiumPeriod.UNLIMITED)
                .count();
    }


    private enum PremiumPeriod {
        ONE_MONTH,
        SIX_MONTHS,
        ONE_YEAR,
        UNLIMITED
    }



}

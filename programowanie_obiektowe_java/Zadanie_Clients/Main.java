package com.capgemini.programowanie.obiektowe;

import java.time.LocalDate;


//testuje 3 klientów

public class Main {

    public static void main(String[] args) {
        ClassClients clients = new ClassClients();

        String bonjoviId = clients.createNewClient("John", "Bon Jovi");
        String mccartneyId = clients.createNewClient("Paul", "McCartney");
        String jonesId = clients.createNewClient("John Paul", "Jones");

        clients.activatePremiumAccount(bonjoviId);
        clients.activatePremiumAccount(mccartneyId);

        System.out.println("Full Name: " + clients.getClientFullName(bonjoviId));
        System.out.println("Full Name: " + clients.getClientFullName(mccartneyId));
        System.out.println("Full Name: " + clients.getClientFullName(jonesId));

        System.out.println("Creation Date: " + clients.getClientCreationDate(bonjoviId));
        System.out.println("Creation Date: " + clients.getClientCreationDate(mccartneyId));
        System.out.println("Creation Date: " + clients.getClientCreationDate(jonesId));

        System.out.println("Client " + bonjoviId +" is Premium: " + clients.isPremiumClient(bonjoviId));
        System.out.println("Client " + mccartneyId +" is Premium: " + clients.isPremiumClient(mccartneyId));
        System.out.println("Client " + jonesId + " is Premium: " + clients.isPremiumClient(jonesId));

        System.out.println("Number of Clients: " + clients.getNumberOfClients());
        System.out.println("Number of Premium Clients: " + clients.getNumberOfPremiumClients());
    }
}

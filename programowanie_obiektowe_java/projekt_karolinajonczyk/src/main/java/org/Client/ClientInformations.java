package org.Client;

import java.time.LocalDate;

public class ClientInformations {

    public enum PremiumPeriod {
        ONE_MONTH,
        SIX_MONTHS,
        ONE_YEAR,
        UNLIMITED
    }
    private String firstName;
    private String lastName;
    private final LocalDate creationDate;
    private boolean premium;
    private LocalDate endDate;
    private int premiumPurchases;

    public PremiumPeriod premiumPeriod;



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

    private void incrementPremiumPurchases() {
        premiumPurchases++;
    }
    public PremiumPeriod getPremiumPeriod() {
        return premiumPeriod;
    }

    private void setPremiumPeriod(PremiumPeriod premiumPeriod) {
        this.premiumPeriod = premiumPeriod;
    }

}


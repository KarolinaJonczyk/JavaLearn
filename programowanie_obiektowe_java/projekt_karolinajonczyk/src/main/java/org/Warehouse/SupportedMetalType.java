package org.Warehouse;

public enum SupportedMetalType {
    COPPER(8960),
    TIN(7260),
    IRON(7870),
    LEAD(11300),
    SILVER(10500),
    TUNGSTEN(19300),
    GOLD(19300),
    PLATINUM(21500);

    private final int density;

    SupportedMetalType(int density) {
        if (density <= 0) {
            throw new IllegalArgumentException("Density must be a positive value");
        }
        this.density = density;
    }
    /**
     * @return Metal density kg/m^3.
     */
    public double getDensity() {
        return density;
    }
}

package org.Warehouse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupportedMetalTypeTest {

    @Test
    void shouldReturnCorrectDensityForMetals() {
        assertEquals(8960, SupportedMetalType.COPPER.getDensity());
        assertEquals(7260, SupportedMetalType.TIN.getDensity());
        assertEquals(7870, SupportedMetalType.IRON.getDensity());
        assertEquals(11300, SupportedMetalType.LEAD.getDensity());
        assertEquals(10500, SupportedMetalType.SILVER.getDensity());
        assertEquals(19300, SupportedMetalType.TUNGSTEN.getDensity());
        assertEquals(19300, SupportedMetalType.GOLD.getDensity());
        assertEquals(21500, SupportedMetalType.PLATINUM.getDensity());
    }


    @Test
    void shouldReturnArrayOfSupportedMetalTypes() {
        SupportedMetalType[] values = SupportedMetalType.values();
        assertEquals(8, values.length);
        assertSame(SupportedMetalType.COPPER, values[0]);
        assertSame(SupportedMetalType.TIN, values[1]);
        assertSame(SupportedMetalType.IRON, values[2]);
        assertSame(SupportedMetalType.LEAD, values[3]);
        assertSame(SupportedMetalType.SILVER, values[4]);
        assertSame(SupportedMetalType.TUNGSTEN, values[5]);
        assertSame(SupportedMetalType.GOLD, values[6]);
        assertSame(SupportedMetalType.PLATINUM, values[7]);
    }

    @Test
    void shouldReturnCorrectSupportedMetalTypeForValidName() {
        assertSame(SupportedMetalType.COPPER, SupportedMetalType.valueOf("COPPER"));
        assertSame(SupportedMetalType.TIN, SupportedMetalType.valueOf("TIN"));
        assertSame(SupportedMetalType.IRON, SupportedMetalType.valueOf("IRON"));
        assertSame(SupportedMetalType.LEAD, SupportedMetalType.valueOf("LEAD"));
        assertSame(SupportedMetalType.SILVER, SupportedMetalType.valueOf("SILVER"));
        assertSame(SupportedMetalType.TUNGSTEN, SupportedMetalType.valueOf("TUNGSTEN"));
        assertSame(SupportedMetalType.GOLD, SupportedMetalType.valueOf("GOLD"));
        assertSame(SupportedMetalType.PLATINUM, SupportedMetalType.valueOf("PLATINUM"));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionForWhitespaceMetalName() {
        assertThrows(IllegalArgumentException.class, () -> SupportedMetalType.valueOf(" "));
    }
    @Test
    void shouldThrowNullPointerExceptionForNullMetalName() {
        assertThrows(NullPointerException.class, () -> SupportedMetalType.valueOf(null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForEmptyMetalName() {
        assertThrows(IllegalArgumentException.class, () -> SupportedMetalType.valueOf(""));
    }
    @Test
    void shouldThrowIllegalArgumentExceptionForInvalidMetalName() {
        assertThrows(IllegalArgumentException.class, () -> SupportedMetalType.valueOf("RHODIUM"));
        assertThrows(IllegalArgumentException.class, () -> SupportedMetalType.valueOf("OSMIUM"));
        assertThrows(IllegalArgumentException.class, () -> SupportedMetalType.valueOf("TELLURIUM"));
        assertThrows(IllegalArgumentException.class, () -> SupportedMetalType.valueOf("PALLADIUM"));



    }


}
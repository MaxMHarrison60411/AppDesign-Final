package tests;

import models.Unicorn;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnicornTest {
    private Unicorn unicorn;
    
    @Before
    public void setUp() {
        unicorn = new Unicorn("Luna", 200, 750.0, "Silver", 150);
    }
    
    @Test
    public void testUnicornCreation() {
        assertNotNull("Unicorn should be created", unicorn);
        assertEquals("Luna", unicorn.getName());
        assertEquals(200, unicorn.getAge());
        assertEquals(750.0, unicorn.getPowerLevel(), 0.01);
        assertEquals("Unicorn", unicorn.getType());
    }
    
    @Test
    public void testHornColorGetterSetter() {
        assertEquals("Silver", unicorn.getHornColor());
        unicorn.setHornColor("Gold");
        assertEquals("Gold", unicorn.getHornColor());
    }
    
    @Test
    public void testHealingPowerGetterSetter() {
        assertEquals(150, unicorn.getHealingPower());
        unicorn.setHealingPower(200);
        assertEquals(200, unicorn.getHealingPower());
    }
    
    @Test
    public void testAbilityName() {
        assertEquals("Divine Healing", unicorn.getAbilityName());
    }
    
    @Test
    public void testAbilityDescription() {
        String description = unicorn.getAbilityDescription();
        assertTrue(description.contains("150"));
        assertTrue(description.contains("Silver"));
    }
    
    @Test
    public void testSetName() {
        unicorn.setName("Celestia");
        assertEquals("Celestia", unicorn.getName());
    }
}
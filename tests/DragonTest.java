package tests;

import models.Dragon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DragonTest {
    private Dragon dragon;
    
    @Before
    public void setUp() {
        dragon = new Dragon("Smaug", 500, 950.5, "Fire", 25);
    }
    
    @Test
    public void testDragonCreation() {
        assertNotNull("Dragon should be created", dragon);
        assertEquals("Smaug", dragon.getName());
        assertEquals(500, dragon.getAge());
        assertEquals(950.5, dragon.getPowerLevel(), 0.01);
        assertEquals("Dragon", dragon.getType());
    }
    
    @Test
    public void testBreathTypeGetterSetter() {
        assertEquals("Fire", dragon.getBreathType());
        dragon.setBreathType("Ice");
        assertEquals("Ice", dragon.getBreathType());
    }
    
    @Test
    public void testWingSpanGetterSetter() {
        assertEquals(25, dragon.getWingSpan());
        dragon.setWingSpan(30);
        assertEquals(30, dragon.getWingSpan());
    }
    
    @Test
    public void testAbilityName() {
        assertEquals("Fire Breath", dragon.getAbilityName());
    }
    
    @Test
    public void testAbilityDescription() {
        String description = dragon.getAbilityDescription();
        assertTrue(description.contains("Fire"));
        assertTrue(description.contains("25"));
    }
    
    @Test
    public void testSetAge() {
        dragon.setAge(600);
        assertEquals(600, dragon.getAge());
    }
    
    @Test
    public void testSetAgeNegative() {
        dragon.setAge(-50);
        assertEquals(500, dragon.getAge()); // Should not change
    }
    
    @Test
    public void testSetPowerLevel() {
        dragon.setPowerLevel(1000.0);
        assertEquals(1000.0, dragon.getPowerLevel(), 0.01);
    }
    
    @Test
    public void testSetPowerLevelNegative() {
        dragon.setPowerLevel(-100);
        assertEquals(950.5, dragon.getPowerLevel(), 0.01); // Should not change
    }
}
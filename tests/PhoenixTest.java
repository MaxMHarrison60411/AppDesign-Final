package tests;

import models.Phoenix;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PhoenixTest {
    private Phoenix phoenix;
    
    @Before
    public void setUp() {
        phoenix = new Phoenix("Fawkes", 1000, 880.0, "Crimson", 5);
    }
    
    @Test
    public void testPhoenixCreation() {
        assertNotNull("Phoenix should be created", phoenix);
        assertEquals("Fawkes", phoenix.getName());
        assertEquals(1000, phoenix.getAge());
        assertEquals(880.0, phoenix.getPowerLevel(), 0.01);
        assertEquals("Phoenix", phoenix.getType());
    }
    
    @Test
    public void testFlameColorGetterSetter() {
        assertEquals("Crimson", phoenix.getFlameColor());
        phoenix.setFlameColor("Azure");
        assertEquals("Azure", phoenix.getFlameColor());
    }
    
    @Test
    public void testRebirthCountGetterSetter() {
        assertEquals(5, phoenix.getRebirthCount());
        phoenix.setRebirthCount(6);
        assertEquals(6, phoenix.getRebirthCount());
    }
    
    @Test
    public void testAbilityName() {
        assertEquals("Rebirth from Ashes", phoenix.getAbilityName());
    }
    
    @Test
    public void testAbilityDescription() {
        String description = phoenix.getAbilityDescription();
        assertTrue(description.contains("Crimson"));
        assertTrue(description.contains("5"));
    }
    
    @Test
    public void testToString() {
        String str = phoenix.toString();
        assertTrue(str.contains("Fawkes"));
        assertTrue(str.contains("Phoenix"));
        assertTrue(str.contains("1000"));
    }
}
package tests;

import managers.CreatureManager;
import models.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;

public class IntegrationTest {
    private CreatureManager manager;
    private String testFilename = "integration_test.dat";
    
    @Before
    public void setUp() {
        manager = new CreatureManager();
    }
    
    @After
    public void tearDown() {
        File testFile = new File(testFilename);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    public void testCompleteWorkflow() {
        // Add creatures
        Dragon dragon = new Dragon("FireBreather", 400, 920.0, "Fire", 22);
        Unicorn unicorn = new Unicorn("Moonlight", 180, 730.0, "Silver", 140);
        Phoenix phoenix = new Phoenix("Ember", 900, 870.0, "Red", 4);
        
        manager.addCreature(dragon);
        manager.addCreature(unicorn);
        manager.addCreature(phoenix);
        assertEquals(3, manager.getCreatureCount());
        
        // Test filtering
        assertEquals(1, manager.filterByType("Dragon").size());
        
        // Test statistics
        assertTrue(manager.getAverageAge() > 0);
        assertNotNull(manager.getMostPowerful());
        
        // Test sorting
        manager.sortByAge();
        assertEquals(180, manager.getCreatures().get(0).getAge());
        
        // Test file operations
        manager.saveToFile(testFilename);
        
        CreatureManager newManager = new CreatureManager();
        newManager.loadFromFile(testFilename);
        assertEquals(3, newManager.getCreatureCount());
        
        // Test modification
        Creature found = newManager.findCreature("FireBreather");
        found.setAge(500);
        assertEquals(500, found.getAge());
        
        // Test removal
        assertTrue(newManager.removeCreature("Moonlight"));
        assertEquals(2, newManager.getCreatureCount());
    }
    
    @Test
    public void testPolymorphismWithAbilities() {
        manager.addCreature(new Dragon("Poly1", 100, 500.0, "Ice", 10));
        manager.addCreature(new Unicorn("Poly2", 50, 400.0, "Gold", 80));
        manager.addCreature(new Phoenix("Poly3", 200, 600.0, "Blue", 2));
        
        // All creatures can execute abilities through polymorphism
        for (Creature creature : manager.getCreatures()) {
            assertNotNull(creature.getAbilityName());
            assertNotNull(creature.getAbilityDescription());
        }
    }
}
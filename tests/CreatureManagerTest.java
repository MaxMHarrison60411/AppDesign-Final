package tests;

import managers.CreatureManager;
import models.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import java.io.File;

public class CreatureManagerTest {
    private CreatureManager manager;
    private Dragon dragon;
    private Unicorn unicorn;
    private Phoenix phoenix;
    
    @Before
    public void setUp() {
        manager = new CreatureManager();
        dragon = new Dragon("Draco", 300, 900.0, "Fire", 20);
        unicorn = new Unicorn("Star", 150, 700.0, "Gold", 120);
        phoenix = new Phoenix("Blaze", 800, 850.0, "Orange", 3);
    }
    
    @After
    public void tearDown() {
        // Clean up test files
        File testFile = new File("test_creatures.dat");
        if (testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    public void testAddCreature() {
        manager.addCreature(dragon);
        assertEquals(1, manager.getCreatureCount());
    }
    
    @Test
    public void testAddMultipleCreatures() {
        manager.addCreature(dragon);
        manager.addCreature(unicorn);
        manager.addCreature(phoenix);
        assertEquals(3, manager.getCreatureCount());
    }
    
    @Test
    public void testAddNullCreature() {
        manager.addCreature(null);
        assertEquals(0, manager.getCreatureCount());
    }
    
    @Test
    public void testRemoveCreature() {
        manager.addCreature(dragon);
        assertTrue(manager.removeCreature("Draco"));
        assertEquals(0, manager.getCreatureCount());
    }
    
    @Test
    public void testRemoveNonExistentCreature() {
        manager.addCreature(dragon);
        assertFalse(manager.removeCreature("NonExistent"));
        assertEquals(1, manager.getCreatureCount());
    }
    
    @Test
    public void testFindCreature() {
        manager.addCreature(dragon);
        Creature found = manager.findCreature("Draco");
        assertNotNull(found);
        assertEquals("Draco", found.getName());
    }
    
    @Test
    public void testFindCreatureCaseInsensitive() {
        manager.addCreature(dragon);
        Creature found = manager.findCreature("draco");
        assertNotNull(found);
    }
    
    @Test
    public void testFindNonExistentCreature() {
        manager.addCreature(dragon);
        Creature found = manager.findCreature("Ghost");
        assertNull(found);
    }
    
    @Test
    public void testFilterByType() {
        manager.addCreature(dragon);
        manager.addCreature(unicorn);
        manager.addCreature(phoenix);
        
        List<Creature> dragons = manager.filterByType("Dragon");
        assertEquals(1, dragons.size());
        assertEquals("Dragon", dragons.get(0).getType());
    }
    
    @Test
    public void testFilterByTypeMultiple() {
        manager.addCreature(dragon);
        manager.addCreature(new Dragon("Smaug", 500, 950.0, "Ice", 25));
        manager.addCreature(unicorn);
        
        List<Creature> dragons = manager.filterByType("Dragon");
        assertEquals(2, dragons.size());
    }
    
    @Test
    public void testFilterByAgeRange() {
        manager.addCreature(dragon);   // age 300
        manager.addCreature(unicorn);  // age 150
        manager.addCreature(phoenix);  // age 800
        
        List<Creature> filtered = manager.filterByAgeRange(100, 400);
        assertEquals(2, filtered.size());
    }
    
    @Test
    public void testFilterByAgeRangeNoMatches() {
        manager.addCreature(dragon);
        List<Creature> filtered = manager.filterByAgeRange(1000, 2000);
        assertEquals(0, filtered.size());
    }
    
    @Test
    public void testSortByAge() {
        manager.addCreature(phoenix);  // age 800
        manager.addCreature(unicorn);  // age 150
        manager.addCreature(dragon);   // age 300
        
        manager.sortByAge();
        List<Creature> creatures = manager.getCreatures();
        
        assertEquals(150, creatures.get(0).getAge());
        assertEquals(300, creatures.get(1).getAge());
        assertEquals(800, creatures.get(2).getAge());
    }
    
    @Test
    public void testSortByPowerLevel() {
        manager.addCreature(dragon);   // 900
        manager.addCreature(unicorn);  // 700
        manager.addCreature(phoenix);  // 850
        
        manager.sortByPowerLevel();
        List<Creature> creatures = manager.getCreatures();
        
        assertEquals(900.0, creatures.get(0).getPowerLevel(), 0.01);
        assertEquals(850.0, creatures.get(1).getPowerLevel(), 0.01);
        assertEquals(700.0, creatures.get(2).getPowerLevel(), 0.01);
    }
    
    @Test
    public void testSortByName() {
        manager.addCreature(phoenix);  // Blaze
        manager.addCreature(dragon);   // Draco
        manager.addCreature(unicorn);  // Star
        
        manager.sortByName();
        List<Creature> creatures = manager.getCreatures();
        
        assertEquals("Blaze", creatures.get(0).getName());
        assertEquals("Draco", creatures.get(1).getName());
        assertEquals("Star", creatures.get(2).getName());
    }
    
    @Test
    public void testGetAverageAge() {
        manager.addCreature(dragon);   // 300
        manager.addCreature(unicorn);  // 150
        manager.addCreature(phoenix);  // 800
        
        double avgAge = manager.getAverageAge();
        assertEquals(416.67, avgAge, 0.01);
    }
    
    @Test
    public void testGetAverageAgeEmptyList() {
        double avgAge = manager.getAverageAge();
        assertEquals(0.0, avgAge, 0.01);
    }
    
    @Test
    public void testGetAveragePowerLevel() {
        manager.addCreature(dragon);   // 900
        manager.addCreature(unicorn);  // 700
        manager.addCreature(phoenix);  // 850
        
        double avgPower = manager.getAveragePowerLevel();
        assertEquals(816.67, avgPower, 0.01);
    }
    
    @Test
    public void testGetTypeDistribution() {
        manager.addCreature(dragon);
        manager.addCreature(new Dragon("Smaug", 500, 950.0, "Ice", 25));
        manager.addCreature(unicorn);
        manager.addCreature(phoenix);
        
        Map<String, Long> distribution = manager.getTypeDistribution();
        
        assertEquals(3, distribution.size());
        assertEquals(Long.valueOf(2), distribution.get("Dragon"));
        assertEquals(Long.valueOf(1), distribution.get("Unicorn"));
        assertEquals(Long.valueOf(1), distribution.get("Phoenix"));
    }
    
    @Test
    public void testGetMostPowerful() {
        manager.addCreature(dragon);   // 900
        manager.addCreature(unicorn);  // 700
        manager.addCreature(phoenix);  // 850
        
        Creature mostPowerful = manager.getMostPowerful();
        assertNotNull(mostPowerful);
        assertEquals("Draco", mostPowerful.getName());
        assertEquals(900.0, mostPowerful.getPowerLevel(), 0.01);
    }
    
    @Test
    public void testGetMostPowerfulEmptyList() {
        Creature mostPowerful = manager.getMostPowerful();
        assertNull(mostPowerful);
    }
    
    @Test
    public void testGetOldest() {
        manager.addCreature(dragon);   // 300
        manager.addCreature(unicorn);  // 150
        manager.addCreature(phoenix);  // 800
        
        Creature oldest = manager.getOldest();
        assertNotNull(oldest);
        assertEquals("Blaze", oldest.getName());
        assertEquals(800, oldest.getAge());
    }
    
    @Test
    public void testGetOldestEmptyList() {
        Creature oldest = manager.getOldest();
        assertNull(oldest);
    }
    
    @Test
    public void testSaveAndLoadFromFile() {
        manager.addCreature(dragon);
        manager.addCreature(unicorn);
        manager.addCreature(phoenix);
        
        String filename = "test_creatures.dat";
        manager.saveToFile(filename);
        
        CreatureManager newManager = new CreatureManager();
        newManager.loadFromFile(filename);
        
        assertEquals(3, newManager.getCreatureCount());
    }
    
    @Test
    public void testLoadFromNonExistentFile() {
        manager.loadFromFile("nonexistent.dat");
        assertEquals(0, manager.getCreatureCount());
    }
    
    @Test
    public void testGetCreatures() {
        manager.addCreature(dragon);
        manager.addCreature(unicorn);
        
        List<Creature> creatures = manager.getCreatures();
        assertEquals(2, creatures.size());
        
        // Verify it's a copy (encapsulation)
        creatures.clear();
        assertEquals(2, manager.getCreatureCount());
    }
}

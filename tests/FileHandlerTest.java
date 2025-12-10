package tests;

import utils.FileHandler;
import models.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class FileHandlerTest {
    private FileHandler fileHandler;
    private List<Creature> creatures;
    private String testFilename = "test_file_handler.dat";
    
    @Before
    public void setUp() {
        fileHandler = new FileHandler();
        creatures = new ArrayList<>();
        creatures.add(new Dragon("TestDragon", 100, 500.0, "Fire", 15));
        creatures.add(new Unicorn("TestUnicorn", 50, 400.0, "White", 100));
    }
    
    @After
    public void tearDown() {
        File testFile = new File(testFilename);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    public void testSaveCreatures() {
        fileHandler.saveCreatures(creatures, testFilename);
        File file = new File(testFilename);
        assertTrue("File should be created", file.exists());
    }
    
    @Test
    public void testLoadCreatures() {
        fileHandler.saveCreatures(creatures, testFilename);
        List<Creature> loaded = fileHandler.loadCreatures(testFilename);
        
        assertNotNull(loaded);
        assertEquals(2, loaded.size());
    }
    
    @Test
    public void testLoadNonExistentFile() {
        List<Creature> loaded = fileHandler.loadCreatures("nonexistent.dat");
        assertNotNull(loaded);
        assertEquals(0, loaded.size());
    }
    
    @Test
    public void testSaveEmptyList() {
        List<Creature> emptyList = new ArrayList<>();
        fileHandler.saveCreatures(emptyList, testFilename);
        
        List<Creature> loaded = fileHandler.loadCreatures(testFilename);
        assertEquals(0, loaded.size());
    }
}
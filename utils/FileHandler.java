package utils;

import models.Creature;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    
    public void saveCreatures(List<Creature> creatures, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(creatures);
            System.out.println("✓ Successfully saved " + creatures.size() + " creatures to " + filename);
        } catch (IOException e) {
            System.err.println("✗ Error saving creatures: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Creature> loadCreatures(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return (List<Creature>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("✗ File not found: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("✗ Error loading creatures: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

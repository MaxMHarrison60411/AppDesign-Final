package managers;

import models.*;
import utils.FileHandler;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreatureManager {
    private List<Creature> creatures;
    private FileHandler fileHandler;
    
    public CreatureManager() {
        this.creatures = new ArrayList<>();
        this.fileHandler = new FileHandler();
    }
    
    // CRUD Operations
    public void addCreature(Creature creature) {
        if (creature != null) {
            creatures.add(creature);
            System.out.println("✓ " + creature.getName() + " has been added successfully!");
        }
    }
    
    public boolean removeCreature(String name) {
        return creatures.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }
    
    public Creature findCreature(String name) {
        return creatures.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    
    public void displayAllCreatures() {
        if (creatures.isEmpty()) {
            System.out.println("No creatures in the system.");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ALL CREATURES IN THE SYSTEM");
        System.out.println("═══════════════════════════════════════════════════");
        
        for (int i = 0; i < creatures.size(); i++) {
            System.out.println((i + 1) + ". " + creatures.get(i).toString());
        }
        System.out.println("═══════════════════════════════════════════════════");
    }
    
    // Filtering
    public List<Creature> filterByType(String type) {
        return creatures.stream()
                .filter(c -> c.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
    public List<Creature> filterByAgeRange(int minAge, int maxAge) {
        return creatures.stream()
                .filter(c -> c.getAge() >= minAge && c.getAge() <= maxAge)
                .collect(Collectors.toList());
    }
    
    // Sorting
    public void sortByAge() {
        creatures.sort(Comparator.comparingInt(Creature::getAge));
    }
    
    public void sortByPowerLevel() {
        creatures.sort(Comparator.comparingDouble(Creature::getPowerLevel).reversed());
    }
    
    public void sortByName() {
        creatures.sort(Comparator.comparing(Creature::getName));
    }
    
    // Statistics
    public double getAverageAge() {
        return creatures.stream()
                .mapToInt(Creature::getAge)
                .average()
                .orElse(0.0);
    }
    
    public double getAveragePowerLevel() {
        return creatures.stream()
                .mapToDouble(Creature::getPowerLevel)
                .average()
                .orElse(0.0);
    }
    
    public Map<String, Long> getTypeDistribution() {
        return creatures.stream()
                .collect(Collectors.groupingBy(Creature::getType, Collectors.counting()));
    }
    
    public Creature getMostPowerful() {
        return creatures.stream()
                .max(Comparator.comparingDouble(Creature::getPowerLevel))
                .orElse(null);
    }
    
    public Creature getOldest() {
        return creatures.stream()
                .max(Comparator.comparingInt(Creature::getAge))
                .orElse(null);
    }
    
    public void displayStatistics() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║          CREATURE STATISTICS                  ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.printf("  Total Creatures: %d%n", creatures.size());
        System.out.printf("  Average Age: %.2f years%n", getAverageAge());
        System.out.printf("  Average Power Level: %.2f%n", getAveragePowerLevel());
        
        System.out.println("\n  Type Distribution:");
        getTypeDistribution().forEach((type, count) -> 
            System.out.printf("    - %s: %d%n", type, count));
        
        Creature mostPowerful = getMostPowerful();
        if (mostPowerful != null) {
            System.out.printf("\n  Most Powerful: %s (%.2f)%n", 
                mostPowerful.getName(), mostPowerful.getPowerLevel());
        }
        
        Creature oldest = getOldest();
        if (oldest != null) {
            System.out.printf("  Oldest: %s (%d years)%n", 
                oldest.getName(), oldest.getAge());
        }
        System.out.println("╚═══════════════════════════════════════════════╝");
    }
    
    // Multi-threading for abilities
    public void executeAllAbilitiesParallel() {
        if (creatures.isEmpty()) {
            System.out.println("No creatures to execute abilities.");
            return;
        }
        
        System.out.println("\n⚡ Executing all creature abilities in parallel...\n");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (Creature creature : creatures) {
            executor.submit(() -> {
                creature.executeAbility();
                try {
                    Thread.sleep(500); // Simulate ability execution time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all tasks to complete
        }
        System.out.println("\n✓ All abilities executed!\n");
    }
    
    // File Operations
    public void saveToFile(String filename) {
        fileHandler.saveCreatures(creatures, filename);
    }
    
    public void loadFromFile(String filename) {
        List<Creature> loaded = fileHandler.loadCreatures(filename);
        if (loaded != null) {
            creatures = loaded;
            System.out.println("✓ Loaded " + creatures.size() + " creatures from file.");
        }
    }
    
    public List<Creature> getCreatures() {
        return new ArrayList<>(creatures);
    }
    
    public int getCreatureCount() {
        return creatures.size();
    }
}
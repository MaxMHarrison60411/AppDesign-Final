package main;

import managers.CreatureManager;
import models.*;
import java.util.*;

public class FantasyCreatureApp {
    private static CreatureManager manager = new CreatureManager();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean running = true;
        
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   FANTASY CREATURE MANAGEMENT SYSTEM          ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        while (running) {
            displayMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1: addCreatureMenu(); break;
                    case 2: removeCreatureMenu(); break;
                    case 3: displayCreatureMenu(); break;
                    case 4: modifyCreatureMenu(); break;
                    case 5: filterMenu(); break;
                    case 6: sortMenu(); break;
                    case 7: manager.displayStatistics(); break;
                    case 8: manager.executeAllAbilitiesParallel(); break;
                    case 9: fileMenu(); break;
                    case 0: 
                        running = false;
                        System.out.println("\n👋 Thank you for using the Fantasy Creature Management System!");
                        break;
                    default:
                        System.out.println("✗ Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("✗ An error occurred: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n═══════════════ MAIN MENU ═══════════════");
        System.out.println("1. Add Creature");
        System.out.println("2. Remove Creature");
        System.out.println("3. Display Creatures");
        System.out.println("4. Modify Creature");
        System.out.println("5. Filter Creatures");
        System.out.println("6. Sort Creatures");
        System.out.println("7. View Statistics");
        System.out.println("8. Execute All Abilities (Parallel)");
        System.out.println("9. File Operations");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void addCreatureMenu() {
        System.out.println("\n--- Add Creature ---");
        System.out.println("1. Dragon");
        System.out.println("2. Unicorn");
        System.out.println("3. Phoenix");
        System.out.print("Choose creature type: ");
        
        try {
            int type = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter power level: ");
            double powerLevel = Double.parseDouble(scanner.nextLine());
            
            switch (type) {
                case 1:
                    System.out.print("Enter breath type (Fire/Ice/Lightning): ");
                    String breathType = scanner.nextLine();
                    System.out.print("Enter wing span (meters): ");
                    int wingSpan = Integer.parseInt(scanner.nextLine());
                    manager.addCreature(new Dragon(name, age, powerLevel, breathType, wingSpan));
                    break;
                case 2:
                    System.out.print("Enter horn color: ");
                    String hornColor = scanner.nextLine();
                    System.out.print("Enter healing power: ");
                    int healingPower = Integer.parseInt(scanner.nextLine());
                    manager.addCreature(new Unicorn(name, age, powerLevel, hornColor, healingPower));
                    break;
                case 3:
                    System.out.print("Enter flame color: ");
                    String flameColor = scanner.nextLine();
                    System.out.print("Enter rebirth count: ");
                    int rebirthCount = Integer.parseInt(scanner.nextLine());
                    manager.addCreature(new Phoenix(name, age, powerLevel, flameColor, rebirthCount));
                    break;
                default:
                    System.out.println("✗ Invalid creature type.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input. Please enter valid numbers.");
        }
    }
    
    private static void removeCreatureMenu() {
        System.out.print("\nEnter creature name to remove: ");
        String name = scanner.nextLine();
        
        if (manager.removeCreature(name)) {
            System.out.println("✓ Creature removed successfully!");
        } else {
            System.out.println("✗ Creature not found.");
        }
    }
    
    private static void displayCreatureMenu() {
        System.out.println("\n--- Display Options ---");
        System.out.println("1. Display All Creatures");
        System.out.println("2. Display Specific Creature");
        System.out.print("Choose option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                manager.displayAllCreatures();
            } else if (choice == 2) {
                System.out.print("Enter creature name: ");
                String name = scanner.nextLine();
                Creature creature = manager.findCreature(name);
                if (creature != null) {
                    creature.displayInfo();
                } else {
                    System.out.println("✗ Creature not found.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input.");
        }
    }
    
    private static void modifyCreatureMenu() {
        System.out.print("\nEnter creature name to modify: ");
        String name = scanner.nextLine();
        Creature creature = manager.findCreature(name);
        
        if (creature == null) {
            System.out.println("✗ Creature not found.");
            return;
        }
        
        System.out.println("\nWhat would you like to modify?");
        System.out.println("1. Age");
        System.out.println("2. Power Level");
        System.out.print("Choose option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                System.out.print("Enter new age: ");
                int newAge = Integer.parseInt(scanner.nextLine());
                creature.setAge(newAge);
                System.out.println("✓ Age updated successfully!");
            } else if (choice == 2) {
                System.out.print("Enter new power level: ");
                double newPower = Double.parseDouble(scanner.nextLine());
                creature.setPowerLevel(newPower);
                System.out.println("✓ Power level updated successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input.");
        }
    }
    
    private static void filterMenu() {
        System.out.println("\n--- Filter Options ---");
        System.out.println("1. Filter by Type");
        System.out.println("2. Filter by Age Range");
        System.out.print("Choose option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                System.out.print("Enter type (Dragon/Unicorn/Phoenix): ");
                String type = scanner.nextLine();
                List<Creature> filtered = manager.filterByType(type);
                displayFilteredList(filtered);
            } else if (choice == 2) {
                System.out.print("Enter minimum age: ");
                int minAge = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter maximum age: ");
                int maxAge = Integer.parseInt(scanner.nextLine());
                List<Creature> filtered = manager.filterByAgeRange(minAge, maxAge);
                displayFilteredList(filtered);
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input.");
        }
    }
    
    private static void displayFilteredList(List<Creature> creatures) {
        if (creatures.isEmpty()) {
            System.out.println("No creatures match the filter.");
        } else {
            System.out.println("\nFiltered Results:");
            creatures.forEach(c -> System.out.println("  - " + c.toString()));
        }
    }
    
    private static void sortMenu() {
        System.out.println("\n--- Sort Options ---");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Age");
        System.out.println("3. Sort by Power Level");
        System.out.print("Choose option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1: manager.sortByName(); break;
                case 2: manager.sortByAge(); break;
                case 3: manager.sortByPowerLevel(); break;
                default: System.out.println("✗ Invalid choice."); return;
            }
            
            System.out.println("✓ Creatures sorted!");
            manager.displayAllCreatures();
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input.");
        }
    }
    
    private static void fileMenu() {
        System.out.println("\n--- File Operations ---");
        System.out.println("1. Save to File");
        System.out.println("2. Load from File");
        System.out.print("Choose option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter filename: ");
            String filename = scanner.nextLine();
            
            if (choice == 1) {
                manager.saveToFile(filename);
            } else if (choice == 2) {
                manager.loadFromFile(filename);
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input.");
        }
    }
}
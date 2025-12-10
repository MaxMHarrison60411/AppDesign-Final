package models;

import interfaces.Ability;
import java.io.Serializable;

public abstract class Creature implements Ability, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private String type;
    private double powerLevel;
    
    public Creature(String name, int age, String type, double powerLevel) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.powerLevel = powerLevel;
    }
    
    // Getters and Setters (Encapsulation)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getPowerLevel() {
        return powerLevel;
    }
    
    public void setPowerLevel(double powerLevel) {
        if (powerLevel >= 0) {
            this.powerLevel = powerLevel;
        }
    }
    
    // Abstract method to be implemented by subclasses
    public abstract void displayInfo();
    
    @Override
    public String toString() {
        return String.format("Name: %s, Type: %s, Age: %d, Power Level: %.2f", 
                           name, type, age, powerLevel);
    }
}
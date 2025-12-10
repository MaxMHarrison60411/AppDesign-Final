package models;

public class Unicorn extends Creature {
    private String hornColor;
    private int healingPower;
    
    public Unicorn(String name, int age, double powerLevel, String hornColor, int healingPower) {
        super(name, age, "Unicorn", powerLevel);
        this.hornColor = hornColor;
        this.healingPower = healingPower;
    }
    
    public String getHornColor() {
        return hornColor;
    }
    
    public void setHornColor(String hornColor) {
        this.hornColor = hornColor;
    }
    
    public int getHealingPower() {
        return healingPower;
    }
    
    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }
    
    @Override
    public void executeAbility() {
        System.out.println("🦄 " + getName() + " channels healing energy through its " + hornColor + " horn!");
        System.out.println("   Restores " + healingPower + " HP to all allies!");
    }
    
    @Override
    public String getAbilityName() {
        return "Divine Healing";
    }
    
    @Override
    public String getAbilityDescription() {
        return "Heals " + healingPower + " HP with " + hornColor + " horn magic";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         UNICORN DETAILS              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  Name: " + getName());
        System.out.println("  Age: " + getAge() + " years");
        System.out.println("  Power Level: " + getPowerLevel());
        System.out.println("  Horn Color: " + hornColor);
        System.out.println("  Healing Power: " + healingPower + " HP");
        System.out.println("  Ability: " + getAbilityName());
    }
}
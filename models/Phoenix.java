package models;

public class Phoenix extends Creature {
    private int rebirthCount;
    private String flameColor;
    
    public Phoenix(String name, int age, double powerLevel, String flameColor, int rebirthCount) {
        super(name, age, "Phoenix", powerLevel);
        this.flameColor = flameColor;
        this.rebirthCount = rebirthCount;
    }
    
    public int getRebirthCount() {
        return rebirthCount;
    }
    
    public void setRebirthCount(int rebirthCount) {
        this.rebirthCount = rebirthCount;
    }
    
    public String getFlameColor() {
        return flameColor;
    }
    
    public void setFlameColor(String flameColor) {
        this.flameColor = flameColor;
    }
    
    @Override
    public void executeAbility() {
        System.out.println("🔥 " + getName() + " erupts in " + flameColor + " flames!");
        System.out.println("   Rising from the ashes! Rebirth #" + rebirthCount);
    }
    
    @Override
    public String getAbilityName() {
        return "Rebirth from Ashes";
    }
    
    @Override
    public String getAbilityDescription() {
        return "Resurrects with " + flameColor + " flames (Reborn " + rebirthCount + " times)";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         PHOENIX DETAILS              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  Name: " + getName());
        System.out.println("  Age: " + getAge() + " years");
        System.out.println("  Power Level: " + getPowerLevel());
        System.out.println("  Flame Color: " + flameColor);
        System.out.println("  Rebirth Count: " + rebirthCount);
        System.out.println("  Ability: " + getAbilityName());
    }
}
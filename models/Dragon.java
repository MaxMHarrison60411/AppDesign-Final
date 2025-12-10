package models;

public class Dragon extends Creature {
    private String breathType;
    private int wingSpan;
    
    public Dragon(String name, int age, double powerLevel, String breathType, int wingSpan) {
        super(name, age, "Dragon", powerLevel);
        this.breathType = breathType;
        this.wingSpan = wingSpan;
    }
    
    public String getBreathType() {
        return breathType;
    }
    
    public void setBreathType(String breathType) {
        this.breathType = breathType;
    }
    
    public int getWingSpan() {
        return wingSpan;
    }
    
    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }
    
    @Override
    public void executeAbility() {
        System.out.println("🐉 " + getName() + " unleashes a devastating " + breathType + " breath!");
        System.out.println("   The flames engulf everything in a " + wingSpan + "m radius!");
    }
    
    @Override
    public String getAbilityName() {
        return breathType + " Breath";
    }
    
    @Override
    public String getAbilityDescription() {
        return "Breathes powerful " + breathType + " with " + wingSpan + "m wingspan";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║          DRAGON DETAILS              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  Name: " + getName());
        System.out.println("  Age: " + getAge() + " years");
        System.out.println("  Power Level: " + getPowerLevel());
        System.out.println("  Breath Type: " + breathType);
        System.out.println("  Wing Span: " + wingSpan + "m");
        System.out.println("  Ability: " + getAbilityName());
    }
}
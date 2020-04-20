package Entities.Player;

import java.io.Serializable;

public class PlayerStats implements Serializable {
    public int attributePoints = 0;

    public int strength;
    public int dexterity;
    public int intelligence;

    public PlayerStats(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }
}

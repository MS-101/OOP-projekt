package Entities.Player;

import java.io.Serializable;

/**
 * Contains player stats data such as available attribute points and attributes.
 */

public class PlayerStats implements Serializable {
    public int attributePoints = 0;

    public int strength;
    public int dexterity;
    public int intelligence;

    /**
     * Sets the starting player attributes.
     *
     * @param strength Starting strength attribute.
     * @param dexterity Starting dexterity attribute.
     * @param intelligence Starting intelligence attribute.
     */

    public PlayerStats(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }
}

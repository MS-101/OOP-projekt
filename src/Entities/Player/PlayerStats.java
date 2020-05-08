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

    /**
     * Increases player's strength and max hp.
     *
     * @param myMercenary Player whose attribute is upgraded.
     */

    public void increaseStrength(Mercenary myMercenary) {
        this.attributePoints--;
        this.strength++;

        myMercenary.maxHp += 5;
        myMercenary.hp += 5;
    }

    /**
     * Increases player's dexterity.
     *
     * @param myMercenary Player whose attribute is upgraded.
     */

    public void increaseDexterity(Mercenary myMercenary) {
        this.attributePoints--;
        this.dexterity++;
    }

    /**
     * Increases player's intelligence and max mp.
     *
     * @param myMercenary Player whose attribute is upgraded.
     */

    public void increaseIntelligence(Mercenary myMercenary) {
        this.attributePoints--;
        this.intelligence++;

        myMercenary.maxMp += 5;
        myMercenary.mp += 5;
    }
}

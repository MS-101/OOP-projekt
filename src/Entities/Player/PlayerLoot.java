package Entities.Player;

import java.io.Serializable;

/**
 * Contains player loot data such as gold and exp.
 * It also contains methods for adding gold and exp to player loot and paying gold.
 */

public class PlayerLoot implements Serializable {
    public int gold;
    public int exp;

    /**
     * This constructor sets the player gold to starting amount and exp to 0.
     *
     * @param startingGold Starting amount of gold.
     */

    public PlayerLoot(int startingGold) {
        this.gold = startingGold;
        this.exp = 0;
    }

    /**
     * Adds exp to player loot.
     *
     * @param exp Amount of gained exp.
     */

    public void addExp(int exp) {
        this.exp += exp;
    }

    /**
     * Adds gold to player loot.
     *
     * @param gold Amount of gained gold.
     */

    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
     * Substracts gold from player loot.
     *
     * @param payment Amount of paid gold.
     */

    public void payGold(int payment) {
        this.gold -= payment;
    }
}

package Entities.Player;

import Entities.Entity;
import Items.Weapons.*;

/**
 * Child class of entity. It represents the player character.
 * In addition of setting the parent entity values,
 * it also contains player specific data such as
 * PlayerConsumables, PlayerStats, PlayerLoot and PlayerSkills.
 * Includes data affiliated with player level.
 */

public class Mercenary extends Entity {
    public PlayerConsumables consumables;
    public PlayerStats stats;
    public PlayerLoot loot;
    public PlayerSkills skills;

    public int lvl, maxLvl = 6;
    public int lvlRequirement;
    int lvlRequirementInc;

    /**
     * Mercenary constructor sets the player name and starting player stats.
     *
     * @param username Name of the mercenary is set to player username.
     */

    public Mercenary(String username) {
        this.setName(username);

        reset();
    }

    /**
     * Resets the player stats to starting stats.
     * Used by mercenary constructor and also used when the player dies.
     */

    public void reset() {
        setMaxHp(200);
        setMaxMp(100);
        setBasePhysDmg(4, 5);
        setBasePiercDmg(0, 0);
        setBaseArmor(0);

        setLvl(0);
        setLvlRequirement(100, 30);

        this.consumables = new PlayerConsumables(3, 0);
        this.skills = new PlayerSkills(1, 0, 0);
        this.stats = new PlayerStats(10, 10, 10);
        this.loot = new PlayerLoot(50);

        setWeapon(new Copper_Dagger());
        setArmor(null);
    }

    /**
     * Sets the current player level.
     *
     * @param lvl Value used to set current player level.
     */

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    /**
     * Sets the starting level requirement and level requirement increment.
     *
     * @param lvlRequirement Amount of exp required for level up.
     * @param lvlRequirementInc Value of exp requirement increment used after leveling up.
     */

    public void setLvlRequirement(int lvlRequirement, int lvlRequirementInc) {
        this.lvlRequirement = lvlRequirement;
        this.lvlRequirementInc = lvlRequirementInc;
    }

    /**
     * Checks if player has enough exp for level up, and if it does it increases player level by 1
     * and substracts the level requirement from player exp.
     * After each level up the level requirement is increased by level requirement increment.
     * Player can't level up to a higher level than max level.
     *
     * @return Returns true if player has leveled up and false if player didn't level up.
     */

    public boolean checkLevelUp() {
        if (this.lvl < this.maxLvl && this.loot.exp >= this.lvlRequirement) {
            this.lvl++;
            this.stats.attributePoints += 2;
            this.skills.skillPoints += 1;

            if (this.lvl < this.maxLvl) {
                this.loot.exp -= this.lvlRequirement;
                this.lvlRequirement += this.lvlRequirementInc;
            } else {
                this.loot.exp = 0;
                this.lvlRequirement = 0;
            }

            return true;
        }

        return false;
    }
}

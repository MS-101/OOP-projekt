package Entities.Monsters;

import Entities.Entity;

/**
 * Child class of entity. It is a parent class of all monsters.
 * Contains information such as exp gain and gold gain.
 * This class doesn't have a constructor but it contains methods,
 * that allow its child classes to set the values of this class.
 */

public class Monster extends Entity {
    public int exp;
    public int lowGold, highGold;

    /**
     * Sets loot gained after slaying this monster.
     *
     * @param exp Experience gained after slaying this monster.
     * @param lowGold Lower bound of gold gained after slaying this monster.
     * @param highGold Higher bound of gold gained after slaying this monster.
     */

    void setLoot(int exp, int lowGold, int highGold) {
        this.exp = exp;
        this.lowGold = lowGold;
        this.highGold = highGold;
    }

    /**
     * Creates copy of this monster.
     * It is used for monster loot tables.
     *
     * @return Returns monster copy.
     */

    public Monster getCopy() {
        Monster newMonster = new Monster();

        newMonster.setMaxHp(this.maxHp);
        newMonster.setMaxMp(this.maxMp);
        newMonster.setBasePhysDmg(this.lowPhysDmg, this.highPhysDmg);
        newMonster.setBasePiercDmg(this.lowPiercDmg, this.highPiercDmg);
        newMonster.setBaseArmor(this.baseArmor);
        newMonster.setName(this.name);

        newMonster.setLoot(this.exp, this.lowGold, this.highGold);

        return newMonster;
    }
}

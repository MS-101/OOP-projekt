package Entities.Monsters;

import Entities.Entity;

public class Monster extends Entity {
    public int exp;
    public int lowGold, highGold;

    void setLoot(int exp, int lowGold, int highGold) {
        this.exp = exp;
        this.lowGold = lowGold;
        this.highGold = highGold;
    }

    public Monster getCopy() {
        Monster newMonster = new Monster();

        newMonster.setMaxHp(this.maxHp);
        newMonster.setMaxMp(this.maxMp);
        newMonster.setBasePhysicalDmg(this.lowPhysicalDmg, this.highPhysicalDmg);
        newMonster.setBasePiercingDmg(this.lowPiercingDmg, this.highPiercingDmg);
        newMonster.setBaseArmor(this.baseArmor);
        newMonster.setName(this.name);

        newMonster.setLoot(this.exp, this.lowGold, this.highGold);

        return newMonster;
    }
}

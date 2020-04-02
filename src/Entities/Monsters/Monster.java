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
}

package Items.Weapons;

import Items.Item;

public class Weapon extends Item {
    public int lowPhysicalDmg, highPhysicalDmg;
    public int lowPiercingDmg, highPiercingDmg;

    public void setPhysicalDmg(int lowPhysicalDmg, int highPhysicalDmg) {
        this.lowPhysicalDmg = lowPhysicalDmg;
        this.highPhysicalDmg = highPhysicalDmg;
    }

    public void setPiercingDmg(int lowPiercingDmg, int highPiercingDmg) {
        this.lowPiercingDmg = lowPiercingDmg;
        this.highPiercingDmg = highPiercingDmg;
    }
}

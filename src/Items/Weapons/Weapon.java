package Items.Weapons;

import Items.Item;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
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

    public void copy(Weapon weaponCopy) {
        super.copy(weaponCopy);
        weaponCopy.setPhysicalDmg(this.lowPhysicalDmg, this.highPhysicalDmg);
        weaponCopy.setPiercingDmg(this.lowPiercingDmg, this.highPiercingDmg);
    }

    public Weapon getCopy() {
        Weapon weaponCopy = new Weapon();
        this.copy(weaponCopy);

        return weaponCopy;
    }
}

package Items.Weapons;

import Items.Item;

public class Weapon extends Item {
    public int lowPhysDmg, highPhysDmg;
    public int lowPiercDmg, highPiercDmg;

    public void setPhysicalDmg(int lowPhysicalDmg, int highPhysicalDmg) {
        this.lowPhysDmg = lowPhysicalDmg;
        this.highPhysDmg = highPhysicalDmg;
    }

    public void setPiercingDmg(int lowPiercingDmg, int highPiercingDmg) {
        this.lowPiercDmg = lowPiercingDmg;
        this.highPiercDmg = highPiercingDmg;
    }

    public void copy(Weapon weaponCopy) {
        super.copy(weaponCopy);
        weaponCopy.setPhysicalDmg(this.lowPhysDmg, this.highPhysDmg);
        weaponCopy.setPiercingDmg(this.lowPiercDmg, this.highPiercDmg);
    }

    public Weapon getCopy() {
        Weapon weaponCopy = new Weapon();
        this.copy(weaponCopy);

        return weaponCopy;
    }
}

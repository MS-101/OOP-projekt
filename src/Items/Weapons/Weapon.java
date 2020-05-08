package Items.Weapons;

import Items.Item;

/**
 * Child class of item. It increases entity damage.
 * Weapon can deal physical and piercing damage.
 * Physical damage can be reduced by receiver's armor.
 * Piercing damage ignores armor.
 */

public class Weapon extends Item {
    public int lowPhysDmg, highPhysDmg;
    public int lowPiercDmg, highPiercDmg;

    /**
     * This method is used to set the range of physical damage of weapon.
     * Physical damage is lowered by receiver's armor.
     * Physical damage is influenced by player strength.
     * Used in weapon constructors.
     *
     * @param lowPhysDmg Lower bound of weapon physical damage.
     * @param highPhysDmg Higher bound of weapon physical damage.
     */

    public void setPhysicalDmg(int lowPhysDmg, int highPhysDmg) {
        this.lowPhysDmg = lowPhysDmg;
        this.highPhysDmg = highPhysDmg;
    }

    /**
     * This method is used to set the range of piercing damage of weapon.
     * Piercing damage ignores armor.
     * Physical damage is influenced by player dexterity.
     * Used in weapon constructors.
     *
     * @param lowPiercDmg Lower bound of weapon physical damage.
     * @param highPiercDmg Higher bound of weapon piercing damage.
     */

    public void setPiercingDmg(int lowPiercDmg, int highPiercDmg) {
        this.lowPiercDmg = lowPiercDmg;
        this.highPiercDmg = highPiercDmg;
    }

    /**
     * Sets the values of weapon copy.
     *
     * @param weaponCopy Copy of weapon.
     */

    public void copy(Weapon weaponCopy) {
        super.copy(weaponCopy);
        weaponCopy.setPhysicalDmg(this.lowPhysDmg, this.highPhysDmg);
        weaponCopy.setPiercingDmg(this.lowPiercDmg, this.highPiercDmg);
    }

    /**
     * Creates a copy of weapon.
     *
     * @return Returns copy of weapon.
     */

    public Weapon getCopy() {
        Weapon weaponCopy = new Weapon();
        this.copy(weaponCopy);

        return weaponCopy;
    }
}

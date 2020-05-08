package Items.Armor;

import Items.Item;

/**
 * Child class of item. It decreases received physical damage.
 * Armor is ignored by piercing damage.
 */

public class Armor extends Item {
    public int armorVal;

    /**
     * Sets the armor value of armor.
     *
     * @param armorVal Value used for setting armor value.
     */

    void setArmor(int armorVal) {
        this.armorVal = armorVal;
    }

    /**
     * Sets the values of armor copy.
     * It is used in forge loot table.
     *
     * @param armorCopy Copy of armor.
     */

    public void copy(Armor armorCopy) {
        super.copy(armorCopy);
        armorCopy.setArmor(this.armorVal);
    }

    /**
     * Creates a copy of armor.
     * It is used in forge loot table.
     *
     * @return Returns copy of armor.
     */

    public Armor getCopy() {
        Armor armorCopy = new Armor();
        this.copy(armorCopy);

        return armorCopy;
    }
}

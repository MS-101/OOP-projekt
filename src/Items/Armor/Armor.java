package Items.Armor;

import Items.Item;

public class Armor extends Item {
    public int armorVal;

    void setArmor(int armorVal) {
        this.armorVal = armorVal;
    }

    public void copy(Armor armorCopy) {
        super.copy(armorCopy);
        armorCopy.setArmor(this.armorVal);
    }

    public Armor getCopy() {
        Armor armorCopy = new Armor();
        this.copy(armorCopy);

        return armorCopy;
    }
}

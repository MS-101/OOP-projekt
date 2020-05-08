package Items.Armor;

/**
 * As of now this is the only available armor.
 * Thus there is nothing special about it.
 */

public class Leather_Armor extends Armor {
    public Leather_Armor() {
        setArmor(8);
        setCost(200, 100);
        setDurability(100);
        setName("Leather Armor");
    }
}

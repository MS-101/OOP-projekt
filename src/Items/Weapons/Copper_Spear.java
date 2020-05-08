package Items.Weapons;

/**
 * This weapon deals high piercing damage and no physical damage.
 * Deals less total damage than similar weapons,
 * but all damage dealt by this weapon ignores armor.
 * This weapon is strong against armored monsters.
 */

public class Copper_Spear extends Weapon {
    public Copper_Spear() {
        setPhysicalDmg(0, 0);
        setPiercingDmg(11, 13);
        setCost(120, 80);
        setDurability(60);
        setName("Copper Spear");
    }
}

package Items.Weapons;

/**
 * Savage weapon, that deals high amount of physical damage, but no piercing damage.
 * Deals higher damage than similar weapons,
 * but all damage dealt by this weapon can be reduced by armor.
 * This weapon is strong against light monsters.
 */

public class Copper_Axe extends  Weapon {
    public Copper_Axe() {
        setPhysicalDmg(16, 20);
        setPiercingDmg(0, 0);
        setCost(120, 80);
        setDurability(60);
        setName("Copper Axe");
    }
}

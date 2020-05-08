package Items.Weapons;

/**
 * Balanced weapon. It deals decent amount of physical and some piercing damage.
 * This weapon is strong against all monsters.
 */

public class Copper_Gladius extends Weapon {
    public Copper_Gladius() {
        setPhysicalDmg(10, 11);
        setPiercingDmg(4, 5);
        setCost(120, 80);
        setDurability(60);
        setName("Copper Gladius");
    }
}

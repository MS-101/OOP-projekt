package Items.Weapons;

/**
 * Weakest and cheapest weapon with low durability.
 * You start the game with this weapon equipped.
 */

public class Copper_Dagger extends Weapon {
    public Copper_Dagger() {
        setPhysicalDmg(5, 6);
        setPiercingDmg(3, 4);
        setCost(60, 40);
        setDurability(40);
        setName("Copper Dagger");
    }
}

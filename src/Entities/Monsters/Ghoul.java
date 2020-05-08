package Entities.Monsters;

/**
 * This is the most common and the weakest monster.
 * Has no armor and deals only physical damage.
 */

public class Ghoul extends Monster {
    public Ghoul() {
        setMaxHp(40);
        setMaxMp(0);
        setBasePhysDmg(7, 9);
        setBasePiercDmg(0, 0);
        setBaseArmor(0);
        setName("Ghoul");

        setLoot(10, 15, 20);
    }
}

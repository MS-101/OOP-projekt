package Entities.Monsters;

/**
 * This is the rarest and the strongest monster.
 * Has high hp, armor and deals large amount of damage.
 * They only deal physical damage.
 */

public class Giant extends Monster {
    public Giant() {
        setMaxHp(100);
        setMaxMp(0);
        setBasePhysDmg(15, 25);
        setBasePiercDmg(0, 0);
        setBaseArmor(8);
        setName("Giant");

        setLoot(40, 40, 60);
    }
}

package Entities.Monsters;

/**
 * Moderately rare monster, has less hp than alghoul but deals more damage.
 * Has decent amount of health and deals high amount of piercing damage.
 */

public class Giant_Spider extends Monster {
    public Giant_Spider() {
        setMaxHp(50);
        setMaxMp(0);
        setBasePhysDmg(4, 6);
        setBasePiercDmg(9, 11);
        setBaseArmor(0);
        setName("Giant Spider");

        setLoot(15, 20, 25);
    }
}

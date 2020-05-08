package Entities.Monsters;

/**
 * Moderately rare monster, it is a stronger version of ghoul.
 * Has higher hp than ghoul and has base armor and also deals piercing damage.
 */

public class Alghoul extends  Monster {
    public Alghoul() {
        setMaxHp(60);
        setMaxMp(0);
        setBasePhysDmg(7, 9);
        setBasePiercDmg(3, 5);
        setBaseArmor(3);
        setName("Alghoul");

        setLoot(15, 20, 25);
    }
}

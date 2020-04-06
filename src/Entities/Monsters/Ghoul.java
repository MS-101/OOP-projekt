package Entities.Monsters;

public class Ghoul extends Monster {
    public Ghoul() {
        setMaxHp(40);
        setMaxMp(0);
        setBasePhysicalDmg(7, 9);
        setBasePiercingDmg(0, 0);
        setBaseArmor(0);
        setName("Ghoul");

        setLoot(10, 15, 20);
    }
}

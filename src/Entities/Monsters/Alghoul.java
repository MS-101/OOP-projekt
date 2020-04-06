package Entities.Monsters;

public class Alghoul extends  Monster {
    public Alghoul() {
        setMaxHp(60);
        setMaxMp(0);
        setBasePhysicalDmg(7, 9);
        setBasePiercingDmg(3, 5);
        setBaseArmor(3);
        setName("Alghoul");

        setLoot(15, 20, 25);
    }
}

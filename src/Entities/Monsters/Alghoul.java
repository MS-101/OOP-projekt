package Entities.Monsters;

public class Alghoul extends  Monster {
    public Alghoul() {
        setMaxHp(70);
        setMaxMp(0);
        setBasePhysicalDmg(14, 16);
        setBasePiercingDmg(4, 6);
        setBaseArmor(5);
        setName("Alghoul");

        setLoot(20, 20, 24);
    }
}

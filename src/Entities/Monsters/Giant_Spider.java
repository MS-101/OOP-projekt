package Entities.Monsters;

public class Giant_Spider extends Monster {
    public Giant_Spider() {
        setMaxHp(50);
        setMaxMp(0);
        setBasePhysicalDmg(4, 6);
        setBasePiercingDmg(9, 11);
        setBaseArmor(0);
        setName("Giant Spider");

        setLoot(15, 20, 25);
    }
}

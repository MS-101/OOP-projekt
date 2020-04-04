package Entities.Monsters;

public class Giant_Spider extends Monster {
    public Giant_Spider() {
        setMaxHp(80);
        setMaxMp(0);
        setBasePhysicalDmg(8, 12);
        setBasePiercingDmg(8, 12);
        setBaseArmor(0);
        setName("Giant");

        setLoot(20, 20, 24);
    }
}

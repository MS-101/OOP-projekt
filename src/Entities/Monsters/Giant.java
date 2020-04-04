package Entities.Monsters;

public class Giant extends Monster {
    public Giant() {
        setMaxHp(120);
        setMaxMp(0);
        setBasePhysicalDmg(25, 40);
        setBasePiercingDmg(0, 0);
        setBaseArmor(15);
        setName("Giant");

        setLoot(50, 50, 70);
    }
}

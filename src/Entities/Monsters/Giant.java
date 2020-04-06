package Entities.Monsters;

public class Giant extends Monster {
    public Giant() {
        setMaxHp(100);
        setMaxMp(0);
        setBasePhysicalDmg(15, 25);
        setBasePiercingDmg(0, 0);
        setBaseArmor(8);
        setName("Giant");

        setLoot(50, 40, 60);
    }
}

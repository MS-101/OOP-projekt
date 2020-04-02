public class Ghoul extends Monster {
    public Ghoul() {
        setMaxHp(50);
        setMaxMp(0);
        setBaseDmg(7, 9);
        setBaseArmor(0);
        setName("Ghoul");

        setLoot(10, 8, 12);
    }
}

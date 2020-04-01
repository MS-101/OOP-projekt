public class Ghoul extends Entity {
    public Ghoul() {
        this.maxHp = 50;
        this.hp = maxHp;

        this.maxMp = 0;
        this.mp = maxMp;

        this.lowDmg = 7;
        this.highDmg = 9;
        this.armor = 0;

        this.name = "Ghoul";
    }
}

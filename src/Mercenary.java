public class Mercenary extends  Entity {
    void initialise() {
        this.maxHp = 400;
        this.hp = maxHp;
        this.lowDmg = 10;
        this.highDmg = 12;
        this.armor = 0;
        this.name = "Knight";
    }
}

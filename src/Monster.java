public class Monster extends  Entity {
    int exp;
    int lowGold, highGold;

    void setLoot(int exp, int lowGold, int highGold) {
        this.exp = exp;
        this.lowGold = lowGold;
        this.highGold = highGold;
    }
}

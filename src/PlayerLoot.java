public class PlayerLoot {
    int gold;
    int exp;

    public PlayerLoot(int startingGold) {
        this.gold = startingGold;
        this.exp = 0;
    }

    void addExp(int exp) {
        this.exp += exp;
    }

    void addGold(int gold) {
        this.gold += gold;
    }
}

package Entities.Player;

public class PlayerLoot {
    public int gold;
    public int exp;

    public PlayerLoot(int startingGold) {
        this.gold = startingGold;
        this.exp = 0;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void payGold(int payment) {
        this.gold -= payment;
    }
}

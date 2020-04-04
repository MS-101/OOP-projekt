package Entities.Player;

public class PlayerConsumables {
    public int hpPotions_amount;
    public int hpPotions_maxAmount;
    public int hpPotion_heal;

    public int mpPotions_amount;
    public int mpPotions_maxAmount;
    public int mpPotion_heal;

    public PlayerConsumables(int hpPotions_amount, int mpPotions_amount) {
        this.hpPotions_amount = hpPotions_amount;
        this.mpPotions_amount = mpPotions_amount;

        this.hpPotions_maxAmount = 10;
        this.mpPotions_maxAmount = 10;

        this.hpPotion_heal = 60;
        this.mpPotion_heal = 40;
    }

    public void addHpPotions(int amount) {
        this.hpPotions_amount += amount;
    }

    public void  addMpPotions(int amount) {
        this.mpPotions_amount += amount;
    }
}

package Entities.Player;

public class PlayerConsumables {
    public int hpPotions_amount;
    public int hpPotion_heal;

    public int mpPotions_amount;
    public int mpPotion_heal;

    public PlayerConsumables(int hpPotions_amount, int mpPotions_amount, int hpPotion_heal, int mpPotion_heal) {
        this.hpPotions_amount = hpPotions_amount;
        this.mpPotions_amount = mpPotions_amount;

        this.hpPotion_heal = hpPotion_heal;
        this.mpPotion_heal = mpPotion_heal;
    }
}
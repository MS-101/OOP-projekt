public class PlayerConsumables {
    int hpPotions_amount;
    int hpPotion_heal;

    int mpPotions_amount;
    int mpPotion_heal;

    public PlayerConsumables(int hpPotions_amount, int mpPotions_amount, int hpPotion_heal, int mpPotion_heal) {
        this.hpPotions_amount = hpPotions_amount;
        this.mpPotions_amount = mpPotions_amount;

        this.hpPotion_heal = hpPotion_heal;
        this.mpPotion_heal = mpPotion_heal;
    }
}

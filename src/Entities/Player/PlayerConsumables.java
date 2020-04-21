package Entities.Player;

import Consumables.HpPotion;
import Consumables.MpPotion;

import java.io.Serializable;

public class PlayerConsumables implements Serializable {
    public HpPotion hpPotionData = new HpPotion();
    public MpPotion mpPotionData = new MpPotion();

    public int hpPotions_amount;
    public int hpPotions_maxAmount;

    public int mpPotions_amount;
    public int mpPotions_maxAmount;

    public PlayerConsumables(int hpPotions_amount, int mpPotions_amount) {
        this.hpPotions_amount = hpPotions_amount;
        this.mpPotions_amount = mpPotions_amount;

        this.hpPotions_maxAmount = 10;
        this.mpPotions_maxAmount = 10;
    }

    public void useHpPotion(Mercenary user) {
        hpPotions_amount--;
        hpPotionData.use(user);
    }

    public void useMpPotion(Mercenary user) {
        hpPotions_amount--;
        hpPotionData.use(user);
    }

    public void addHpPotions(int amount) {
        this.hpPotions_amount += amount;
    }

    public void  addMpPotions(int amount) {
        this.mpPotions_amount += amount;
    }
}

package Entities.Player;

import Consumables.HpPotion;
import Consumables.MpPotion;

import java.io.Serializable;

/**
 * Contains player consumables data such as current amount and maximum amount of consumables.
 * It also contains methods for using and adding consumables.
 */

public class PlayerConsumables implements Serializable {
    public HpPotion hpPotionData = new HpPotion();
    public MpPotion mpPotionData = new MpPotion();

    public int hpPotions_amount;
    public int hpPotions_maxAmount;

    public int mpPotions_amount;
    public int mpPotions_maxAmount;

    /**
     * This constructor sets the current and maximum amount of consumables.
     *
     * @param hpPotions_amount Amount of starting hp potions.
     * @param mpPotions_amount Amount of starting mp potions.
     */

    public PlayerConsumables(int hpPotions_amount, int mpPotions_amount) {
        this.hpPotions_amount = hpPotions_amount;
        this.mpPotions_amount = mpPotions_amount;

        this.hpPotions_maxAmount = 10;
        this.mpPotions_maxAmount = 10;
    }

    /**
     * Decreases current hp potion amount and makes the user use hp potion.
     *
     * @param user Mercenary that used hp potion.
     */

    public void useHpPotion(Mercenary user) {
        hpPotions_amount--;
        hpPotionData.use(user);
    }

    /**
     * Decreases current mp potion amount and makes the user use mp potion.
     *
     * @param user Mercenary that used mp potion.
     */

    public void useMpPotion(Mercenary user) {
        mpPotions_amount--;
        mpPotionData.use(user);
    }

    /**
     * Increases the current amount of hp potions.
     *
     * @param amount Amount of hp potions to add.
     */

    public void addHpPotions(int amount) {
        this.hpPotions_amount += amount;
    }

    /**
     * Increases the current amount of mp potions.
     *
     * @param amount Amount of mp potions to add.
     */

    public void  addMpPotions(int amount) {
        this.mpPotions_amount += amount;
    }
}

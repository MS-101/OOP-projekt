package Environments;

import Consumables.HpPotion;
import Consumables.MpPotion;
import Entities.Player.Mercenary;
import Entities.Player.PlayerConsumables;

import java.io.Serializable;

/**
 * You can buy consumables here.
 */

public class Market implements Serializable {

    /**
     * This method makes the customer pay for hp potion
     * and gives him 1 hp potion.
     *
     * @param customer Player that is buying the consumable.
     */

    public void buyHpPotion(Mercenary customer) {
        PlayerConsumables myConsumables = customer.consumables;
        HpPotion hpPotionData = new HpPotion();

        int payment = hpPotionData.cost;

        customer.loot.payGold(payment);
        myConsumables.addHpPotions(1);
    }

    /**
     * This method makes the customer pay for mp potion
     * and gives him 1 mp potion.
     *
     * @param customer Player that is buying the consumable.
     */

    public void buyMpPotion(Mercenary customer) {
        PlayerConsumables myConsumables = customer.consumables;
        MpPotion mpPotionData = new MpPotion();

        int payment = mpPotionData.cost;

        customer.loot.payGold(payment);
        myConsumables.addMpPotions(1);
    }
}

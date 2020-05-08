package Consumables;

import Entities.Entity;
import Entities.Player.Mercenary;

/**
 * This consumable heals hp when used.
 */

public class HpPotion extends Consumable {
    public int hpHeal = 80;

    /**
     * This constructor sets the cost of this item.
     */

    public HpPotion() {
        cost = 20;
    }

    /**
     * Heals user's hp.
     *
     * @param user Mercenary that used this consumable.
     */

    public void use(Entity user) {
        user.healHp(hpHeal);
    }
}

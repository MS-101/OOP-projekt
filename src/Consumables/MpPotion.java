package Consumables;

import Entities.Entity;

/**
 * This consumable heals mp when used.
 */

public class MpPotion extends Consumable {
    public int mpHeal = 40;

    /**
     * This constructor sets the cost of this item.
     */

    public MpPotion() {
        cost = 20;
    }

    /**
     * Heals user's mp.
     *
     * @param user Mercenary that used this consumable.
     */

    public void use(Entity user) {
        user.healMp(mpHeal);
    }
}

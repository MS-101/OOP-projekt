package Consumables;

import Entities.Entity;

public class MpPotion extends Consumable {
    public int mpHeal = 40;

    public MpPotion() {
        cost = 20;
    }

    public void use(Entity user) {
        user.healMp(mpHeal);
    }
}

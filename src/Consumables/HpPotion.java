package Consumables;

import Entities.Entity;
import Entities.Player.Mercenary;

public class HpPotion extends Consumable {
    public int hpHeal = 80;

    public HpPotion() {
        cost = 20;
    }

    public void use(Entity user) {
        user.healHp(hpHeal);
    }
}

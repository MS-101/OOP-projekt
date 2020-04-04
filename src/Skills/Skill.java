package Skills;

import Entities.Entity;

public class Skill {
    int curLvl, maxLvl;
    int manaCost;

    public void cast(Entity caster) {
        caster.mp -= manaCost;
        System.out.println("You spent " + this.manaCost + " mana.");
    }
}

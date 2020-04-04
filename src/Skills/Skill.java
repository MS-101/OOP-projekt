package Skills;

import Entities.Entity;

public class Skill {
    public int curLvl, maxLvl;
    public int manaCost;

    public void cast(Entity caster) {
        caster.mp -= manaCost;
        System.out.println("You spent " + this.manaCost + " mana.");
    }
}

package Skills;

import Entities.Entity;

public class Skill {
    public int curLvl, maxLvl;
    public int mpCost;

    public void cast(Entity caster) {
        caster.mp -= mpCost;
        System.out.println("You spent " + this.mpCost + " mana.");
    }
}

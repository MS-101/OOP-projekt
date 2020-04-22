package Skills;

import Entities.Entity;
import Entities.Player.Mercenary;

public class Heal extends Skill {
    public int healAmount;

    public Heal(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.mpCost = 30;

        this.healAmount = 80;
    }

    public void upgrade() {
        if (this.curLvl > 0) {
            this.healAmount += 10;
        }

        this.curLvl++;
    }

    public void cast(Entity caster) {
        super.cast(caster);

        if (caster instanceof Mercenary) {
            healAmount *= (double)((Mercenary) caster).stats.intelligence / 10;
        }

        caster.healHp(healAmount);
    }
}

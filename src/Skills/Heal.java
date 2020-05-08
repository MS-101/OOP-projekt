package Skills;

import Entities.Entity;
import Entities.Player.Mercenary;

/**
 * This skill heals caster.
 */

public class Heal extends Skill {
    public int healAmount;

    /**
     * This constructor sets current and maximum level,
     * amount of health healed and mana cost of this ability.
     *
     * @param curLvl Value used for setting the current level of this ability.
     */

    public Heal(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.mpCost = 30;

        this.healAmount = 80;
    }

    /**
     * Spends 1 skill point and increases current level and power of this ability.
     */

    public void upgrade(Mercenary myMercenary) {
        myMercenary.skills.skillPoints--;

        if (this.curLvl > 0) {
            this.healAmount += 10;
        }

        this.curLvl++;
    }

    /**
     * This method allows the caster to use this ability on themselves.
     *
     * @param caster Entity that casted this ability.
     */

    public void cast(Entity caster) {
        super.cast(caster);

        if (caster instanceof Mercenary) {
            healAmount *= (double)((Mercenary) caster).stats.intelligence / 10;
        }

        caster.healHp(healAmount);
    }
}

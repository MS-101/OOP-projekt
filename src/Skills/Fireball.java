package Skills;

import Entities.Entity;
import Entities.Player.Mercenary;

import java.util.Random;

/**
 * This skill deals high amount of spell damage to a single target.
 */

public class Fireball extends Skill {
    public int lowSpellDmg, highSpellDmg;

    /**
     * This constructor sets current and maximum level,
     * range of spell damage and mana cost of this ability.
     *
     * @param curLvl Value used for setting the current level of this ability.
     */

    public Fireball(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.mpCost = 30;

        this.lowSpellDmg = 35;
        this.highSpellDmg = 45;
    }

    /**
     * Spends 1 skill point and increases current level and power of this ability.
     */

    public void upgrade(Mercenary myMercenary) {
        myMercenary.skills.skillPoints--;

        if (this.curLvl > 0) {
            this.lowSpellDmg += 10;
            this.highSpellDmg += 10;
        }

        this.curLvl++;
    }

    /**
     * This method allows the caster to use this ability on a target.
     *
     * @param caster Entity that casted this ability.
     * @param target Entity that is targeted by this ability.
     */

    public void cast(Entity caster, Entity target) {
        super.cast(caster);

        int spellDamage;
        Random randomNumber = new Random();

        spellDamage = randomNumber.nextInt((this.highSpellDmg - this.lowSpellDmg) + 1) + lowSpellDmg;

        if (caster instanceof Mercenary) {
            spellDamage *= (double)((Mercenary) caster).stats.intelligence / 10;
        }

        target.hp -= spellDamage;
    }
}

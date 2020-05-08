package Skills;

import Entities.Entity;
import Entities.Monsters.Monster;
import Entities.Player.Mercenary;

import java.util.ArrayList;
import java.util.Random;

/**
 * This skill deals spell damage to multiple targets.
 */

public class Flamestrike extends Skill {
    public int lowSpellDmg, highSpellDmg;

    /**
     * This constructor sets current and maximum level,
     * range of spell damage and mana cost of this ability.
     *
     * @param curLvl Value used for setting the current level of this ability.
     */

    public Flamestrike(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.mpCost = 40;

        this.lowSpellDmg = 20;
        this.highSpellDmg = 25;
    }

    /**
     * Increases current level and power of this ability.
     */

    public void upgrade() {
        if (this.curLvl > 0) {
            this.lowSpellDmg += 5;
            this.highSpellDmg += 5;
        }

        this.curLvl++;
    }

    /**
     * This method allows the caster to use this ability on a targets.
     *
     * @param caster Entity that casted this ability.
     * @param targets Entities that are targeted by this ability.
     */

    public void cast(Entity caster, ArrayList<Monster> targets) {
        super.cast(caster);

        int i, spellDamage;
        Random randomNumber = new Random();

        for (i = 0; i < targets.size(); i++) {
            Entity selectedTarget = targets.get(i);

            spellDamage = randomNumber.nextInt((this.highSpellDmg - this.lowSpellDmg) + 1) + lowSpellDmg;

            if (caster instanceof Mercenary) {
                spellDamage *= (double)((Mercenary) caster).stats.intelligence / 10;
            }

            selectedTarget.hp -= spellDamage;
        }
    }
}

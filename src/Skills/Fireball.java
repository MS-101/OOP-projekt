package Skills;

import Entities.Entity;
import Entities.Player.Mercenary;

import java.util.Random;

public class Fireball extends Skill {
    public int lowSpellDmg, highSpellDmg;

    public Fireball(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.mpCost = 30;

        this.lowSpellDmg = 35;
        this.highSpellDmg = 45;
    }

    public void upgrade() {
        if (this.curLvl > 0) {
            this.lowSpellDmg += 10;
            this.highSpellDmg += 10;
        }

        this.curLvl++;
    }

    public void cast(Entity caster, Entity target) {
        super.cast(caster);

        int i, spellDamage;
        Random randomNumber = new Random();

        spellDamage = randomNumber.nextInt((this.highSpellDmg - this.lowSpellDmg) + 1) + lowSpellDmg;

        if (caster instanceof Mercenary) {
            spellDamage *= (double)((Mercenary) caster).stats.intelligence / 10;
        }

        target.hp -= spellDamage;

        System.out.println("Fireball deals " + spellDamage + " damage to " + target.name + ".");
        if (target.hp < 0) {
            System.out.println(target.name + " has died.");
        }
        System.out.println();
    }
}
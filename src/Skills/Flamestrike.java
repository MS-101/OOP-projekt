package Skills;

import Entities.Entity;
import Entities.Player.Mercenary;

import java.util.ArrayList;
import java.util.Random;

public class Flamestrike extends Skill {
    int lowSpellDmg, highSpellDmg;

    public Flamestrike(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.manaCost = 40;

        this.lowSpellDmg = 15;
        this.highSpellDmg = 20;
    }

    public void upgrade() {
        if (this.curLvl > 0) {
            this.lowSpellDmg += 5;
            this.highSpellDmg += 5;
        }

        this.curLvl++;
    }

    public void cast(Entity caster, ArrayList<Entity> targets) {
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

            System.out.println("Flamestrike deals " + spellDamage + " damage to " + selectedTarget.name + " <" + i+1 + ">.");
            if (selectedTarget.hp < 0) {
                System.out.println(selectedTarget.name + " has died.");
            }
            System.out.println();
        }
    }
}

package Skills;

import Entities.Entity;
import Entities.Monsters.Monster;
import Entities.Player.Mercenary;

import java.util.ArrayList;
import java.util.Random;

public class Flamestrike extends Skill {
    public int lowSpellDmg, highSpellDmg;

    public Flamestrike(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.mpCost = 40;

        this.lowSpellDmg = 20;
        this.highSpellDmg = 25;
    }

    public void upgrade() {
        if (this.curLvl > 0) {
            this.lowSpellDmg += 5;
            this.highSpellDmg += 5;
        }

        this.curLvl++;
    }

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

            int opponentIndex = i + 1;

            System.out.println("Flamestrike deals " + spellDamage + " damage to " + selectedTarget.name + " <" + opponentIndex + ">.");
            if (selectedTarget.hp < 0) {
                System.out.println(selectedTarget.name + " has died.");
            }
        }

        System.out.println();
    }
}

package Skills;

import Entities.Entity;

public class Heal extends Skill {
    int healAmount;

    public Heal(int curLvl) {
        this.curLvl = curLvl;
        this.maxLvl = 3;
        this.manaCost = 30;

        this.healAmount = 50;
    }

    public void upgrade() {
        if (this.curLvl > 0) {
            this.healAmount += 10;
        }

        this.curLvl++;
    }

    public void cast(Entity caster) {
        super.cast(caster);

        int prevHp, effectiveHeal;

        prevHp = caster.hp;
        caster.healHp(healAmount);
        effectiveHeal = caster.hp - prevHp;

        System.out.println("You healed for " + effectiveHeal + " hp.");
        System.out.println();
    }
}

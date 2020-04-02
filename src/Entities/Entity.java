package Entities;

import Entities.Player.*;
import Items.Weapons.Weapon;
import Items.Armor.Armor;

import java.util.Random;

public class Entity {
    public int maxHp, hp;
    public int maxMp, mp;
    int lowPhysicalDmg, highPhysicalDmg;
    int lowPiercingDmg, highPiercingDmg;
    int baseArmor;
    public String name;

    Weapon weapon;
    Armor armor;

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
        this.mp = maxMp;
    }

    public void setBasePhysicalDmg(int lowPhysicalDmg, int highPhysicalDmg) {
        this.lowPhysicalDmg = lowPhysicalDmg;
        this.highPhysicalDmg = highPhysicalDmg;
    }

    public void setBasePiercingDmg(int lowPiercingDmg, int highPiercingDmg) {
        this.lowPiercingDmg = lowPiercingDmg;
        this.highPiercingDmg = highPiercingDmg;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean attack(Entity receiver) {
        int inflictedPhysicalDmg, remainingPhysicalDmg, absorbedPhysicalDmg;
        int inflictedPiercingDmg;
        int totalArmor, totalDmg;
        double armorReduction;
        Random randomNumber = new Random();

        totalArmor = receiver.baseArmor;

        if (receiver.armor != null) {
            totalArmor += receiver.armor.armorVal;
        }

        inflictedPhysicalDmg = randomNumber.nextInt((this.highPhysicalDmg - this.lowPhysicalDmg) + 1) + lowPhysicalDmg;

        if (this.weapon != null) {
            inflictedPhysicalDmg += randomNumber.nextInt((this.weapon.highPhysicalDmg - this.weapon.lowPhysicalDmg) + 1) + this.weapon.lowPhysicalDmg;
        }

        if (this instanceof Mercenary) {
            inflictedPhysicalDmg *= ((Mercenary) this).stats.strength / 10;
        }

        armorReduction = 0.3;

        remainingPhysicalDmg = inflictedPhysicalDmg - totalArmor;

        if (remainingPhysicalDmg > 0) {
            absorbedPhysicalDmg = totalArmor;

            inflictedPhysicalDmg = (int)(armorReduction * absorbedPhysicalDmg) + remainingPhysicalDmg;
        } else {
            absorbedPhysicalDmg = inflictedPhysicalDmg;

            inflictedPhysicalDmg = (int)(armorReduction * absorbedPhysicalDmg);
        }

        inflictedPiercingDmg = randomNumber.nextInt((this.highPiercingDmg - this.lowPiercingDmg) + 1) + this.lowPiercingDmg;

        if (this.weapon != null) {
            inflictedPiercingDmg += randomNumber.nextInt((this.weapon.highPiercingDmg - this.weapon.lowPiercingDmg) + 1) + this.weapon.lowPiercingDmg;
        }

        if (this instanceof Mercenary) {
            inflictedPiercingDmg *= ((Mercenary) this).stats.dexterity / 10;
        }

        totalDmg = inflictedPhysicalDmg + inflictedPiercingDmg;

        receiver.hp -= totalDmg;
        System.out.println(this.name + " deals " + totalDmg + " damage to " + receiver.name + ".");

        if (receiver.hp > 0) {
            System.out.println(receiver.name + " has " + receiver.hp + "/" + receiver.maxHp + " hp remaining.");
            System.out.println();
        } else {
            System.out.println(receiver.name + " has died.");
            System.out.println();
        }

        return true;
    }
}

package Entities;

import Combat.DamageHandler;
import Entities.Player.*;
import Items.Weapons.Weapon;
import Items.Armor.Armor;

import java.io.Serializable;
import java.util.Random;

public class Entity implements Serializable {
    public int maxHp, hp;
    public int maxMp, mp;
    public int lowPhysDmg, highPhysDmg;
    public int lowPiercDmg, highPiercDmg;
    public int baseArmor;
    public String name;

    public Weapon weapon;
    public Armor armor;

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
        this.mp = maxMp;
    }

    public void setBasePhysicalDmg(int lowPhysicalDmg, int highPhysicalDmg) {
        this.lowPhysDmg = lowPhysicalDmg;
        this.highPhysDmg = highPhysicalDmg;
    }

    public void setBasePiercingDmg(int lowPiercingDmg, int highPiercingDmg) {
        this.lowPiercDmg = lowPiercingDmg;
        this.highPiercDmg = highPiercingDmg;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void healHp(int amount) {
        this.hp += amount;

        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }

    public void healMp(int amount) {
        this.mp += amount;

        if (this.mp > this.maxMp) {
            this.mp = this.maxMp;
        }
    }

    //returns true when critical damage was used
    public boolean attack(Entity receiver) {
        int physDmg, piercDmg, totalDmg;
        boolean critUsed = false;
        DamageHandler myDmgHandler = new DamageHandler();

        if (myDmgHandler.tryDodge()) {
            return false;
        }

        physDmg = myDmgHandler.calcPhysDmg(this.weapon, this.lowPhysDmg, this.highPhysDmg);
        physDmg = myDmgHandler.absorbDmg(receiver.armor, receiver.baseArmor, physDmg);
        if (this instanceof Mercenary) {
            physDmg = myDmgHandler.applyStr(((Mercenary) this).stats.strength, physDmg);
        }

        piercDmg = myDmgHandler.calcPiercDmg(this.weapon, this.lowPiercDmg, this.highPiercDmg);
        if (this instanceof Mercenary) {
            piercDmg = myDmgHandler.applyDex(((Mercenary) this).stats.dexterity, piercDmg);
        }

        totalDmg = physDmg + piercDmg;

        if (myDmgHandler.tryCrit()) {
            totalDmg = myDmgHandler.calcCritDmg(totalDmg);
            critUsed = true;
        }

        if (totalDmg < 1) {
            totalDmg = 1;
        }

        receiver.hp -= totalDmg;

        if (this instanceof Mercenary && this.weapon != null) {
            this.weapon.durability--;

            if (this.weapon.durability == 0) {
                this.setWeapon(null);
            }
        }

        if (receiver instanceof Mercenary && receiver.armor != null) {
            receiver.armor.durability--;

            if (receiver.armor.durability == 0) {
                receiver.setArmor(null);
            }
        }

        return critUsed;
    }
}

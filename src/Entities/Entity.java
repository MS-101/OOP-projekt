package Entities;

import Entities.Player.*;
import Items.Weapons.Weapon;
import Items.Armor.Armor;

import java.io.Serializable;
import java.util.Random;

public class Entity implements Serializable {
    public int maxHp, hp;
    public int maxMp, mp;
    public int lowPhysicalDmg, highPhysicalDmg;
    public int lowPiercingDmg, highPiercingDmg;
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

    public void healAll(int amount) {
        healHp(amount);
        healMp(amount);
    }

    public boolean attack(Entity receiver) {
        int inflictedPhysicalDmg, remainingPhysicalDmg, absorbedPhysicalDmg;
        int inflictedPiercingDmg;
        int basePhysicalDmg = 0, basePiercingDmg = 0;
        int weaponPhysicalDmg = 0, weaponPiercingDmg = 0, equipmentArmor;
        int totalArmor, totalDmg;
        double armorReduction;
        Random randomNumber = new Random();

        totalArmor = receiver.baseArmor;

        if (receiver.armor != null) {
            equipmentArmor = receiver.armor.armorVal;

            if (receiver instanceof Mercenary && receiver.armor.durability <= 0.2 * receiver.armor.maxDurability) {
                equipmentArmor *= 0.5;
            }

            totalArmor += equipmentArmor;
        }

        basePhysicalDmg = randomNumber.nextInt((this.highPhysicalDmg - this.lowPhysicalDmg) + 1) + lowPhysicalDmg;

        if (this.weapon != null) {
            weaponPhysicalDmg = randomNumber.nextInt((this.weapon.highPhysicalDmg - this.weapon.lowPhysicalDmg) + 1) + this.weapon.lowPhysicalDmg;

            if (this instanceof  Mercenary && this.weapon.durability <= 0.2 * this.weapon.maxDurability) {
                weaponPhysicalDmg *= 0.5;
            }
        }

        inflictedPhysicalDmg = basePhysicalDmg + weaponPhysicalDmg;

        if (this instanceof Mercenary) {
            inflictedPhysicalDmg *= (double)((Mercenary) this).stats.strength / 10;
        }

        if (totalArmor != 0) {
            armorReduction = 0.3;

            remainingPhysicalDmg = inflictedPhysicalDmg - totalArmor;

            if (remainingPhysicalDmg > 0) {
                absorbedPhysicalDmg = totalArmor;

                inflictedPhysicalDmg = (int)(armorReduction * absorbedPhysicalDmg) + remainingPhysicalDmg;
            } else {
                absorbedPhysicalDmg = inflictedPhysicalDmg;

                inflictedPhysicalDmg = (int)(armorReduction * absorbedPhysicalDmg);
            }
        }

        basePiercingDmg = randomNumber.nextInt((this.highPiercingDmg - this.lowPiercingDmg) + 1) + this.lowPiercingDmg;

        if (this.weapon != null) {
            weaponPiercingDmg = randomNumber.nextInt((this.weapon.highPiercingDmg - this.weapon.lowPiercingDmg) + 1) + this.weapon.lowPiercingDmg;

            if (this instanceof  Mercenary && this.weapon.durability <= 0.2 * this.weapon.maxDurability) {
                weaponPiercingDmg *= 0.5;
            }
        }

        inflictedPiercingDmg = basePiercingDmg + weaponPiercingDmg;

        if (this instanceof Mercenary) {
            inflictedPiercingDmg *= (double)((Mercenary) this).stats.dexterity / 10;
        }

        totalDmg = inflictedPhysicalDmg + inflictedPiercingDmg;

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

        return true;
    }
}

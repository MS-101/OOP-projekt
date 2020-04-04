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

            if (this instanceof Mercenary && this.armor.durability <= 0.2 * this.weapon.maxDurability) {
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
            inflictedPhysicalDmg *= ((Mercenary) this).stats.strength / 10;
        }

        if (receiver.armor != null) {
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

        if (this instanceof Mercenary && this.weapon != null) {
            this.weapon.durability--;

            if (this.weapon.durability == 0) {
                System.out.println("Your weapon just broke!");
                System.out.println();

                this.setWeapon(null);
            } else if (this.weapon.durability < 0.2 * this.weapon.maxDurability) {
                System.out.println("You should repair your weapon if you don't want to break it [" + this.weapon.durability + "/" + this.weapon.maxDurability +  " durability]");
                System.out.println();
            } else if (this.weapon.durability < 0.4 * this.weapon.maxDurability) {
                System.out.println("Your weapon could use some repairs... [" + this.weapon.durability + "/" + this.weapon.maxDurability +  " durability]");
                System.out.println();
            }
        }

        if (receiver instanceof Mercenary && this.armor != null) {
            receiver.armor.durability--;

            if (receiver.armor.durability == 0) {
                System.out.println("Your armor just broke!");
                System.out.println();

                receiver.setArmor(null);
            } else if (receiver.armor.durability < 0.2 * receiver.armor.maxDurability) {
                System.out.println("You should repair your armor if you don't want to break it [" + receiver.weapon.durability + "/" + receiver.weapon.maxDurability +  " durability]");
                System.out.println();
            } else if (receiver.weapon.durability < 0.4 * receiver.weapon.maxDurability) {
                System.out.println("Your weapon could use some repairs... [" + receiver.weapon.durability + "/" + receiver.weapon.maxDurability +  " durability]");
                System.out.println();
            }
        }

        return true;
    }
}

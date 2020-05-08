package Entities;

import Combat.DamageHandler;
import Entities.Player.*;
import Items.Weapons.Weapon;
import Items.Armor.Armor;

import java.io.Serializable;

/**
 * This class is a parent class of all monsters and player.
 * Contains information such as hp, mp, base armor, base damage and name of entity.
 * This class doesn't have a constructor but it contains methods,
 * that allow its child classes to set the values of this class.
 */

public class Entity implements Serializable {
    public int maxHp, hp;
    public int maxMp, mp;
    public int lowPhysDmg, highPhysDmg;
    public int lowPiercDmg, highPiercDmg;
    public int baseArmor;
    public String name;

    public Weapon weapon;
    public Armor armor;

    /**
     * This method is used to set the max hp of entity.
     * It also sets the current hp to max hp.
     * Used in monster and mercenary constructors.
     *
     * @param maxHp This is the the value used for setting hp.
     */

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    /**
     * This method is used to set the max mp of entity.
     * It also sets the current mp to max mp.
     * Used in monster and mercenary constructors.
     *
     * @param maxMp This is the the value used for setting mp.
     */

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
        this.mp = maxMp;
    }

    /**
     * This method is used to set the range of base physical damage of entity.
     * Physical damage is lowered by receiver's armor.
     * Physical damage is influenced by player strength.
     * Used in monster and mercenary constructors.
     *
     * @param lowPhysDmg Lower bound of base physical damage.
     * @param highPhysDmg Higher bound of base physical damage.
     */

    public void setBasePhysDmg(int lowPhysDmg, int highPhysDmg) {
        this.lowPhysDmg = lowPhysDmg;
        this.highPhysDmg = highPhysDmg;
    }

    /**
     * This method is used to set the range of base piercing damage of entity.
     * Piercing damage ignores receiver's armor.
     * Piercing damage is influenced by player dexterity.
     * Used in monster and mercenary constructors.
     *
     * @param lowPiercDmg Lower bound of base physical damage.
     * @param highPiercDmg Higher bound of base piercing damage.
     */

    public void setBasePiercDmg(int lowPiercDmg, int highPiercDmg) {
        this.lowPiercDmg = lowPiercDmg;
        this.highPiercDmg = highPiercDmg;
    }

    /**
     * This method is used to set the base armor of entity.
     * Armor reduces received physical damage.
     * Used in monster and mercenary constructors.
     *
     * @param baseArmor Value used for setting base armor.
     */

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    /**
     * This method is used to set the name of entity.
     * Used in monster and mercenary constructors.
     *
     * @param name String used for naming the entity.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to equip entity with weapon.
     * For example it is used when buying weapon from forge.
     *
     * @param weapon Weapon that will be equipped.
     */

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * This method is used to equip entity with armor.
     * For example it is used when buying armor from forge.
     *
     * @param armor Armor that will be equipped.
     */

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * This method is used to heal hp of entity.
     * For example it is called by hp potion.
     *
     * @param amount Amount of hp to be healed.
     */

    public void healHp(int amount) {
        this.hp += amount;

        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }

    /**
     * This method is used to heal mp of entity.
     * For example it is called by mp potion.
     *
     * @param amount Amount of mp to be healed.
     */

    public void healMp(int amount) {
        this.mp += amount;

        if (this.mp > this.maxMp) {
            this.mp = this.maxMp;
        }
    }

    /**
     * This method is used to make entity attack other entities.
     * Uses damage handler for damage calculation.
     * Attacker has a chance to miss attack and a chance to deal critical attack.
     * If the attacker is mercenary, damage dealt is influenced by player stats.
     * If the attacker is mercenary, weapon takes durability damage.
     * IF the receiver is mercenary, armor takes durability damage.
     *
     * @param receiver Entity that receives damage.
     * @return boolean Returns true if it is a critical attack was used and false it is a regular attack.
     */

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

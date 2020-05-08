package Combat;

import Items.Armor.Armor;
import Items.Weapons.Weapon;

import java.util.Random;

/**
 * This class is used for calculating damage.
 */

public class DamageHandler {

    /**
     * Attempts to dodge an attack.
     *
     * @return Returns true if dodge was successful or false when unsuccessful.
     */

    public boolean tryDodge() {
        Random random = new Random();

        return (random.nextInt(100) < 5);
    }

    /**
     * Attempts to deal critical damage.
     *
     * @return Returns true if critical attack was successful or false when unsuccessful.
     */

    public boolean tryCrit() {
        Random random = new Random();

        return (random.nextInt(100) < 5);
    }

    /**
     * Multiplies damage with critical modifier.
     *
     * @param dmg Damage that is modified.
     * @return Returns critical damage.
     */

    public int calcCritDmg(int dmg) {
        double critMod = 1.5;

        dmg = (int)(dmg * critMod);

        return dmg;
    }

    /**
     * Calculates physical damage.
     *
     * @param myWeapon Weapon used for attacking.
     * @param lowPhysDmg Lower bound of base physical damage.
     * @param highPhysDmg Higher bound of base physical damage.
     * @return Returns physical damage.
     */

    public int calcPhysDmg(Weapon myWeapon, int lowPhysDmg, int highPhysDmg) {
        Random random = new Random();
        int basePhysDmg, weaponPhysDmg, totalPhysDmg;

        basePhysDmg = random.nextInt((highPhysDmg - lowPhysDmg) + 1) + lowPhysDmg;

        if (myWeapon != null) {
            weaponPhysDmg = random.nextInt((myWeapon.highPhysDmg - myWeapon.lowPhysDmg) + 1) + myWeapon.lowPhysDmg;

            if (myWeapon.durability <= 0.2 * myWeapon.maxDurability) {
                weaponPhysDmg *= 0.5;
            }
        } else {
            weaponPhysDmg = 0;
        }

        totalPhysDmg = basePhysDmg + weaponPhysDmg;

        return totalPhysDmg;
    }

    /**
     * Calculates piercing damage.
     *
     * @param myWeapon Weapon used for attacking.
     * @param lowPiercDmg Lower bound of base piercing damage.
     * @param highPiercDmg Higher bound of base piercing damage.
     * @return Returns piercing damage.
     */

    public int calcPiercDmg(Weapon myWeapon, int lowPiercDmg, int highPiercDmg) {
        Random random = new Random();
        int basePiercDmg, weaponPiercDmg, totalPiercDmg;

        basePiercDmg = random.nextInt((highPiercDmg - lowPiercDmg) + 1) + lowPiercDmg;

        if (myWeapon != null) {
            weaponPiercDmg = random.nextInt((myWeapon.highPiercDmg - myWeapon.lowPiercDmg) + 1) + myWeapon.lowPiercDmg;

            if (myWeapon.durability <= 0.2 * myWeapon.maxDurability) {
                weaponPiercDmg *= 0.5;
            }
        } else {
            weaponPiercDmg = 0;
        }

        totalPiercDmg = basePiercDmg + weaponPiercDmg;

        return totalPiercDmg;
    }

    /**
     * Increases damage with strength attribute.
     *
     * @param str Player strength.
     * @param dmg Player physical damage.
     * @return Returns modified physical damage.
     */

    public int applyStr(int str, int dmg) {
        dmg *= (double)str / 10;

        return dmg;
    }

    /**
     * Increases damage with dexterity attribute.
     *
     * @param dex Player dexterity.
     * @param dmg Player piercing damage.
     * @return Returns modified piercing damage.
     */

    public int applyDex(int dex, int dmg) {
        dmg *= (double)dex / 10;

        return dmg;
    }

    /**
     * Absorbs physical damage with armor.
     *
     * @param myArmor Armor used to absorb damage.
     * @param baseArmor Base armor of receiver.
     * @param dmg Physical damage dealt by attacker.
     * @return Returns decreased physical damage.
     */

    public int absorbDmg(Armor myArmor, int baseArmor, int dmg) {
        int totalArmor, equipmentArmor, remainingPhysDmg, absorbedPhysDmg;
        double armorReduction = 0.3; //armor can never absorb 100% damage

        if (myArmor != null) {
            equipmentArmor = myArmor.armorVal;

            if (myArmor.durability <= 0.2 * myArmor.maxDurability) {
                equipmentArmor *= 0.5;
            }
        } else {
            equipmentArmor = 0;
        }

        totalArmor = baseArmor + equipmentArmor;

        if (totalArmor != 0) {
            remainingPhysDmg = dmg - totalArmor;

            if (remainingPhysDmg > 0) {
                absorbedPhysDmg = totalArmor;

                dmg = (int)(armorReduction * absorbedPhysDmg) + remainingPhysDmg;
            } else {
                absorbedPhysDmg = dmg;

                dmg = (int)(armorReduction * absorbedPhysDmg);
            }
        }

        return dmg;
    }
}

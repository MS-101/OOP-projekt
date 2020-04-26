package Combat;

import Entities.Player.Mercenary;
import Items.Armor.Armor;
import Items.Weapons.Weapon;

import java.util.Random;

public class DamageHandler {
    public boolean tryDodge() {
        Random random = new Random();

        return (random.nextInt(100) < 5);
    }

    public boolean tryCrit() {
        Random random = new Random();

        return (random.nextInt(100) < 5);
    }

    public int calcCritDmg(int dmg) {
        double critMod = 1.5;

        dmg = (int)(dmg * critMod);

        return dmg;
    }

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

    public int applyStr(int str, int dmg) {
        dmg *= (double)str / 10;

        return dmg;
    }

    public int applyDex(int dex, int dmg) {
        dmg *= (double)dex / 10;

        return dmg;
    }

    public int absorbDmg(Armor myArmor, int baseArmor, int dmg) {
        int totalArmor, equipmentArmor, remainingPhysDmg, absorbedPhysDmg;
        double armorReduction = 0.3;

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

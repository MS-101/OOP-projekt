import java.util.Random;

public class Entity {
    int maxHp, hp;
    int maxMp, mp;
    int lowDmg, highDmg;
    int armor;
    String name;

    //later i will add equipped weapons and armor here

    boolean attack(Entity receiver) {
        int inflictedDmg, remainingDmg, absorbedDmg, realDmg;
        double armorReduction;
        Random randomNumber = new Random();

        inflictedDmg = randomNumber.nextInt((highDmg - lowDmg) + 1) + lowDmg;

        if (this instanceof  Mercenary) {
            inflictedDmg *= ((Mercenary) this).stats.strength / 10;
        }

        armorReduction = 0.3;

        remainingDmg = inflictedDmg - receiver.armor;

        if (remainingDmg > 0) {
            absorbedDmg = receiver.armor;

            realDmg = (int)(armorReduction * absorbedDmg) + remainingDmg;
        } else {
            absorbedDmg = inflictedDmg;

            realDmg = (int)(armorReduction * absorbedDmg);
        }

        receiver.hp -= realDmg;
        System.out.println(this.name + " deals " + realDmg + " damage to " + receiver.name + ".");

        if (receiver.hp > 0) {
            System.out.println(receiver.name + " has " + receiver.hp + "/" + receiver.maxHp + " hp remaining.");
            System.out.println();
        } else {
            System.out.println(receiver.name + " has died.");
        }

        return true;
    }
}

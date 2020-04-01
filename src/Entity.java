public class Entity {
    int maxHp;
    int hp;
    int dmg;
    int armor;
    String name;

    //later i will add equipped weapons and armor here

    void attack(Entity receiver) {
        int remainingDmg, absorbedDmg, realDmg;
        double armorReduction;

        armorReduction = 0.3;

        remainingDmg = this.dmg - receiver.armor;

        if (remainingDmg > 0) {
            absorbedDmg = receiver.armor;

            realDmg = (int)(armorReduction * absorbedDmg) + remainingDmg;
        } else {
            absorbedDmg = receiver.dmg;

            realDmg = (int)(armorReduction * absorbedDmg);
        }

        receiver.hp -= realDmg;
        System.out.println(this.name + " deals " + this.dmg + " damage to " + receiver.name + ".");

        if (receiver.hp > 0) {
            System.out.println(receiver.name + " has " + receiver.hp + " hitpoints remaining.");
            System.out.println();
        } else {
            System.out.println(receiver.name + " has died.");
        }
    }
}

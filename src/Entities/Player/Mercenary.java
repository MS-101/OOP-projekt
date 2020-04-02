package Entities.Player;

import Entities.Entity;

public class Mercenary extends Entity {
    public PlayerConsumables consumables;
    public PlayerStats stats;
    public PlayerLoot loot;

    public Mercenary() {
        setMaxHp(200);
        setMaxMp(100);
        setBasePhysicalDmg(10, 12);
        setBasePiercingDmg(0, 0);
        setBaseArmor(0);
        setName("Entities.Player.Mercenary");

        this.stats = new PlayerStats(10, 10, 10);
        this.consumables = new PlayerConsumables(3, 0, 60, 40);
        this.loot = new PlayerLoot(50);
    }

    public boolean useHpPotion() {
        if (hp < maxHp) {
            int prevHp, healedHp;

            prevHp = hp;

            this.consumables.hpPotions_amount--;
            this.hp += this.consumables.hpPotion_heal;

            if (this.hp > this.maxHp) {
                this.hp = this.maxHp;
            }

            healedHp = hp - prevHp;

            System.out.println("You used a health potion. You healed for " + healedHp + " hp.");
            System.out.println("You have " + hp + "/" + maxHp + " hp.");

            return true;
        } else {
            System.out.println("You have max hp!");
            return false;
        }
    }

    public boolean useMpPotion() {
        if (mp < maxMp) {
            int prevMp, healedMp;

            prevMp = mp;

            this.consumables.mpPotions_amount--;
            this.mp += this.consumables.mpPotion_heal;

            if (this.mp > this.maxMp) {
                this.mp = this.maxMp;
            }

            healedMp = mp - prevMp;

            System.out.println("You used a mana potion. You healed for " + healedMp + " mp.");
            System.out.println("You have " + mp + "/" + maxMp + " mp remaining.");
            System.out.println();

            return true;
        } else {
            System.out.println("You have max mp!");
            return false;
        }
    }
}

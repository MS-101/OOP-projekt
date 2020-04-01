import java.util.Random;

public class Mercenary extends  Entity {
    int hpPotions_amount;
    int hpPotion_heal;

    int mpPotions_amount;
    int mpPotion_heal;

    PlayerStats stats;

    public Mercenary() {
        this.maxHp = 200;
        this.hp = maxHp;
        this.maxMp = 100;
        this.mp = maxMp;
        this.lowDmg = 10;
        this.highDmg = 12;
        this.armor = 0;
        this.name = "Knight";

        this.stats = new PlayerStats(10, 10, 10);

        this.hpPotions_amount = 3;
        this.mpPotions_amount = 0;
    }

    boolean useHpPotion() {
        if (hpPotions_amount > 0) {
            this.hp += hpPotion_heal;

            if (this.hp > this.maxHp) {
                this.hp = this.maxHp;
            }

            return true;
        } else {
            System.out.println("You ran out of health potions!");
            return false;
        }
    }

    boolean useMpPotion() {
        if (mpPotions_amount > 0) {
            this.mp += mpPotion_heal;

            if (this.mp > this.maxMp) {
                this.mp = this.maxMp;
            }

            return true;
        } else {
            System.out.println("You ran out of mana potions!");
            return false;
        }
    }
}

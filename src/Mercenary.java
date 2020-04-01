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
        this.name = "Mercenary";

        this.stats = new PlayerStats(10, 10, 10);

        this.hpPotions_amount = 3;
        this.mpPotions_amount = 0;

        this.hpPotion_heal = 60;
        this.mpPotion_heal = 40;
    }

    boolean useHpPotion() {
        if (hp < maxHp) {
            int prevHp, healedHp;

            prevHp = hp;

            this.hpPotions_amount--;
            this.hp += hpPotion_heal;

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

    boolean useMpPotion() {
        if (mp < maxMp) {
            int prevMp, healedMp;

            prevMp = mp;

            this.mpPotions_amount--;
            this.mp += mpPotion_heal;

            if (this.mp > this.maxMp) {
                this.mp = this.maxMp;
            }

            healedMp = mp - prevMp;

            System.out.println("You used a mana potion. You healed for " + healedMp + " mp.");
            System.out.println("You have " + mp + "/" + maxMp + " mp remaining.");

            return true;
        } else {
            System.out.println("You have max mp!");
            return false;
        }
    }
}

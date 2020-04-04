package Entities.Player;

import Entities.Entity;
import Items.Item;
import Items.Weapons.Copper_Dagger;
import java.util.ArrayList;

public class Mercenary extends Entity {
    public PlayerConsumables consumables;
    public PlayerStats stats;
    public PlayerLoot loot;
    public PlayerSkills skills;

    public int lvl = 0, maxLvl = 6;
    public int lvlRequirement;
    public int attributePoints = 0, skillPoints = 0;
    int lvlRequirementInc;

    public Mercenary() {
        setMaxHp(200);
        setMaxMp(100);
        setBasePhysicalDmg(4, 5);
        setBasePiercingDmg(0, 0);
        setBaseArmor(0);
        setName("Mercenary");

        setLvlRequirement(100, 30);

        this.stats = new PlayerStats(10, 10, 10);
        this.consumables = new PlayerConsumables(3, 0);
        this.loot = new PlayerLoot(50);
        this.skills = new PlayerSkills(1, 0, 0);

        setWeapon(new Copper_Dagger());
    }

    public void setLvlRequirement(int lvlRequirement, int lvlRequirementInc) {
        this.lvlRequirement = lvlRequirement;
        this.lvlRequirementInc = lvlRequirementInc;
    }

    public void checkLevelUp() {
        while (this.lvl < this.maxLvl && this.loot.exp >= this.lvlRequirement) {
            System.out.println("LEVEL UP!");

            this.lvl++;
            this.attributePoints += 2;
            this.skillPoints += 1;

            if (this.lvl < this.maxLvl) {
                this.loot.exp -= this.lvlRequirement;
                this.lvlRequirement += this.lvlRequirementInc;
            } else {
                this.loot.exp = 0;
                this.lvlRequirement = 0;
            }
        }
    }

    public boolean useHpPotion() {
        if (hp < maxHp) {
            int prevHp, healedHp;

            prevHp = hp;

            this.consumables.hpPotions_amount--;
            this.healHp(this.consumables.hpPotion_heal);

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
            this.healHp(this.consumables.mpPotion_heal);

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

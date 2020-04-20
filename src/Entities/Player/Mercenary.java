package Entities.Player;

import Entities.Entity;
import Items.Weapons.*;

public class Mercenary extends Entity {
    public PlayerConsumables consumables;
    public PlayerStats stats;
    public PlayerLoot loot;
    public PlayerSkills skills;

    public int lvl = 0, maxLvl = 6;
    public int lvlRequirement;
    int lvlRequirementInc;

    public Mercenary() {
        reset();
    }

    public void reset() {
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
            this.stats.attributePoints += 2;
            this.skills.skillPoints += 1;

            if (this.lvl < this.maxLvl) {
                this.loot.exp -= this.lvlRequirement;
                this.lvlRequirement += this.lvlRequirementInc;
            } else {
                this.loot.exp = 0;
                this.lvlRequirement = 0;
            }
        }
    }

    public void useHpPotion() {
        this.consumables.hpPotions_amount--;
        this.healHp(this.consumables.hpPotion_heal);
    }

    public void useMpPotion() {
        this.consumables.mpPotions_amount--;
        this.healMp(this.consumables.mpPotion_heal);
    }
}

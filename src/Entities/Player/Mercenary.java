package Entities.Player;

import Entities.Entity;
import Items.Weapons.*;

public class Mercenary extends Entity {
    public PlayerConsumables consumables;
    public PlayerStats stats;
    public PlayerLoot loot;
    public PlayerSkills skills;

    public int lvl, maxLvl = 6;
    public int lvlRequirement;
    int lvlRequirementInc;

    public Mercenary(String username) {
        this.setName(username);

        reset();
    }

    public void reset() {
        setMaxHp(200);
        setMaxMp(100);
        setBasePhysicalDmg(4, 5);
        setBasePiercingDmg(0, 0);
        setBaseArmor(0);

        setLvl(0);
        setLvlRequirement(100, 30);

        this.consumables = new PlayerConsumables(3, 0);
        this.skills = new PlayerSkills(1, 0, 0);
        this.stats = new PlayerStats(10, 10, 10);
        this.loot = new PlayerLoot(50);

        setWeapon(new Copper_Dagger());
        setArmor(null);
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setLvlRequirement(int lvlRequirement, int lvlRequirementInc) {
        this.lvlRequirement = lvlRequirement;
        this.lvlRequirementInc = lvlRequirementInc;
    }

    public boolean checkLevelUp() {
        if (this.lvl < this.maxLvl && this.loot.exp >= this.lvlRequirement) {
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

            return true;
        }

        return false;
    }
}

package Entities.Player;

import Skills.*;

import java.io.Serializable;

/**
 * Contains player skills data such as available skill points and skills.
 */

public class PlayerSkills implements Serializable {
    public int skillPoints;

    public Fireball fireball;
    public Flamestrike flamestrike;
    public Heal heal;

    /**
     * This constructor sets the starting skill levels of player.
     *
     * @param fireballLvl Starting level of fireball skill.
     * @param flamestrikeLvl Starting level of flamestrike skill.
     * @param healLvl Starting level of heal skill.
     */

    public PlayerSkills(int fireballLvl, int flamestrikeLvl, int healLvl) {
        fireball = new Fireball(fireballLvl);
        flamestrike = new Flamestrike(flamestrikeLvl);
        heal = new Heal(healLvl);
    }
}

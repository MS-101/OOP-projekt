package Entities.Player;

import Skills.*;

import java.io.Serializable;

public class PlayerSkills implements Serializable {
    public Fireball fireball;
    public Flamestrike flamestrike;
    public Heal heal;

    public PlayerSkills(int fireballLvl, int flamestrikeLvl, int healLvl) {
        fireball = new Fireball(fireballLvl);
        flamestrike = new Flamestrike(flamestrikeLvl);
        heal = new Heal(healLvl);
    }
}

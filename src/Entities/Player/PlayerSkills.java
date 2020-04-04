package Entities.Player;

import Skills.*;

public class PlayerSkills {
    public Fireball fireball;
    public Flamestrike flamestrike;
    public Heal heal;

    public PlayerSkills(int fireballLvl, int flamestrikeLvl, int healLvl) {
        fireball = new Fireball(fireballLvl);
        flamestrike = new Flamestrike(flamestrikeLvl);
        heal = new Heal(healLvl);
    }
}

package Entities.Player;

import Skills.*;

public class PlayerSkills {
    Fireball fireball;
    Flamestrike flamestrike;
    Heal heal;

    public PlayerSkills(int fireballLvl, int flamestrikeLvl, int healLvl) {
        fireball = new Fireball(fireballLvl);
        flamestrike = new Flamestrike(flamestrikeLvl);
        heal = new Heal(healLvl);
    }


}

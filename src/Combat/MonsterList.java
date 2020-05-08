package Combat;

import Entities.Monsters.*;

import java.util.ArrayList;

/**
 * This class is an ArrayList of Monsters.
 * It is used as a loot table of monsters for generation of random opponents.
 * @param <Monster>
 */

public class MonsterList<Monster> extends ArrayList<Object> {

    /**
     * This constructor calls generateMonsters function
     */

    public MonsterList() {
        super();
        generateMonsters();
    }

    /**
     * This method adds Monsters to loot table.
     * The higher amount of monsters it contains compared to the size of this class,
     * the higher the probability of encountering this monster in combat is.
     * For example there are 45 ghouls in this list and there are 100 monsters in total
     * meaning that there is 45 % chance of picking a ghoul from this list.
     */

    public void generateMonsters() {
        int i;

        for (i = 0; i < 45; i++) {
            this.add(new Ghoul());
        }
        for (i = 0; i < 25; i++) {
            this.add(new Alghoul());
        }
        for (i = 0; i < 25; i++) {
            this.add(new Giant_Spider());
        }
        for (i = 0; i < 5; i++) {
            this.add(new Giant());
        }
    }
}
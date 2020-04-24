package Combat;

import Entities.Monsters.*;

import java.util.ArrayList;

public class MonsterList<Monster> extends ArrayList<Object> {
    public MonsterList() {
        super();
        generateMonsters();
    }

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

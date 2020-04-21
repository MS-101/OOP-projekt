package Enviroments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import Entities.Player.Mercenary;
import Entities.Monsters.*;
import MySystem.*;

public class Forest {
    ArrayList<Monster> monsterLootTable;

    public Forest() {
        int i;
        monsterLootTable = new ArrayList<Monster>();

        for (i = 0; i < 45; i++) {
            monsterLootTable.add(new Ghoul());
        }
        for (i = 0; i < 25; i++) {
            monsterLootTable.add(new Alghoul());
        }
        for (i = 0; i < 25; i++) {
            monsterLootTable.add(new Giant_Spider());
        }
        for (i = 0; i < 5; i++) {
            monsterLootTable.add(new Giant());
        }
    }

    /*
    Random randomNumber = new Random();
    ArrayList<Monster> opponents = new ArrayList<Monster>();

    int monsterCount = randomNumber.nextInt(3) + 1;

    for (i = 0; i < monsterCount; i++) {
        int randomIndex = randomNumber.nextInt(monsterLootTable.size());

        Monster newMonster = this.monsterLootTable.get(randomIndex).getCopy();

        opponents.add(newMonster);
    }

    new Combat(accountsFile, myHashtable, player, opponents);
    */
}

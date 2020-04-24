package Combat;

import Entities.Monsters.Monster;
import Entities.Player.Mercenary;
import GUI.Game.Controllers.ForestController;

import java.util.ArrayList;
import java.util.Random;

public class Combat {
    Mercenary player;
    ForestController assignedController;

    public ArrayList<Monster> opponents = new ArrayList<>();

    public Combat(Mercenary player, ForestController assignedController) {
        this.player = player;
        this.assignedController = assignedController;

        generateOpponents();
    }

    protected void generateOpponents() {
        int i;
        Random randomNumber = new Random();
        MonsterList<Monster> myMonsters = new MonsterList<Monster>();
        int monsterCount = randomNumber.nextInt(3) + 1;

        for (i = 0; i < monsterCount; i++) {
            int randomIndex = randomNumber.nextInt(myMonsters.size());

            Monster newMonster = ((Monster)myMonsters.get(randomIndex)).getCopy();

            this.opponents.add(newMonster);
        }
    }

    public void opponentTurn() {

    }
}

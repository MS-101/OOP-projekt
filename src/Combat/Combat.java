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
    int turn;

    public Combat(Mercenary player, ForestController assignedController) {
        this.player = player;
        this.assignedController = assignedController;
        this.turn = 1;

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

    public void useAttack(int opponentIndex) {
        Monster target = opponents.get(opponentIndex);

        int prevHp = target.hp;
        player.attack(target);
        int damage = prevHp - target.hp;

        assignedController.sendMessage(player.name + " deals " + damage + " damage to " + target.name + ".");
        if (target.hp <= 0) {
            killOpponent(opponentIndex);
        }

        assignedController.updateForest_monsterHBox();
        assignedController.updatePlayer_weapon();

        if (opponents.size() > 0) {
            opponentsTurn();
        } else {
            victory();
        }
    }

    public void useFireball(int opponentIndex) {
        Monster target = opponents.get(opponentIndex);

        int prevHp = target.hp;
        player.skills.fireball.cast(player, target);
        int damage = prevHp - target.hp;

        assignedController.sendMessage(player.name + " casts a spell and deals " + damage + " damage to " + target.name + ".");
        if (target.hp <= 0) {
            killOpponent(opponentIndex);
        }

        assignedController.updateForest_monsterHBox();
        assignedController.updatePlayer_mp();
        assignedController.updatePlayer_mpPotions();

        if (opponents.size() > 0) {
            opponentsTurn();
        } else {
            victory();
        }
    }

    public void killOpponent(int opponentIndex) {
        Monster target = opponents.get(opponentIndex);

        Random randomNumber = new Random();

        opponents.remove(opponentIndex);

        int gainedExp = target.exp;
        int gainedGold = randomNumber.nextInt((target.highGold - target.lowGold) + 1) + target.lowGold;

        player.loot.addExp(gainedExp);
        assignedController.updatePlayer_exp();
        assignedController.updatePlayer_nameLevel();
        player.loot.addGold(gainedGold);
        assignedController.updatePlayer_gold();

        assignedController.sendMessage(target.name + " has died! [" + gainedExp + " exp, " + gainedGold + " gold]");
    }

    public void opponentsTurn() {
        int i;

        assignedController.disableCombatButtons();

        for (i = 0; i < opponents.size(); i++) {
            Monster attacker = opponents.get(i);

            int prevHp = player.hp;
            attacker.attack(player);
            int damage = prevHp - player.hp;

            assignedController.sendMessage(attacker.name + " deals " + damage + " damage to " + player.name + ".");

            assignedController.updatePlayer_hp();
            assignedController.updatePlayer_hpPotions();
            assignedController.updatePlayer_armor();

            if (player.hp <= 0) {
                assignedController.sendMessage(player.name + " has died!");
                defeat();
                return;
            }
        }

        assignedController.enableCombatButtons();
        nextTurn();
    }

    public void nextTurn() {
        turn++;

        assignedController.sendMessage("");
        assignedController.sendMessage("TURN " + turn + ":");
    }

    public void victory() {
        assignedController.sendMessage("");
        assignedController.sendMessage("You are victorious!");

        assignedController.setForestButtons();
    }

    public void defeat() {
        assignedController.sendMessage("");
        assignedController.sendMessage("You were defeated!");
        assignedController.sendMessage("A new adventurer takes your place.");

        assignedController.setForestButtons();
        assignedController.clearMonsters();
        assignedController.resetMercenary();
    }
}

package Combat;

import Entities.Monsters.Monster;
import Entities.Player.Mercenary;
import Entities.Player.PlayerConsumables;
import GUI.Game.Controllers.ForestController;

import java.io.IOException;
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

    public void useAttack(int opponentIndex) throws IOException {
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

    public void useFireball(int opponentIndex) throws IOException {
        Monster target = opponents.get(opponentIndex);

        int prevHp = target.hp;
        player.skills.fireball.cast(player, target);
        int damage = prevHp - target.hp;

        assignedController.sendMessage(player.name + " casts a spell [Fireball] and deals " + damage + " damage to " + target.name + ".");
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

    public void useFlamestrike() throws IOException {
        ArrayList<Integer> prevHp = new ArrayList<>();
        int opponentIndex;

        Monster pickedOpponent;
        for (opponentIndex = 0; opponentIndex < opponents.size(); opponentIndex++) {
            pickedOpponent = opponents.get(opponentIndex);
            prevHp.add(pickedOpponent.hp);
        }

        player.skills.flamestrike.cast(player, opponents);

        for (opponentIndex = 0; opponentIndex < opponents.size(); opponentIndex++) {
            pickedOpponent = opponents.get(opponentIndex);
            int damage = prevHp.get(opponentIndex) - pickedOpponent.hp;

            assignedController.sendMessage(player.name + " casts a spell [Flamestrike] and deals " + damage + " damage to " + pickedOpponent.name + ".");

            if (pickedOpponent.hp <= 0) {
                killOpponent(opponentIndex);
                opponentIndex--;
            }
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

    public void useHeal() throws IOException {
        int prevHp = player.hp;
        player.skills.heal.cast(player);
        int healAmount = player.hp - prevHp;

        assignedController.sendMessage(player.name + " casts a spell [Heal] and restores " + healAmount + " hp.");

        assignedController.updatePlayer_hp();
        assignedController.updatePlayer_hpPotions();
        assignedController.updatePlayer_mp();
        assignedController.updatePlayer_mpPotions();

        opponentsTurn();
    }

    public void useHpPotion() throws IOException {
        int prevHp = player.hp;
        player.consumables.useHpPotion(player);
        int healAmount = player.hp - prevHp;

        assignedController.sendMessage(player.name + " drank hp potion and restored " + healAmount + " hp.");

        assignedController.updatePlayer_hp();
        assignedController.updatePlayer_hpPotions();

        opponentsTurn();
    }

    public void useMpPotion() throws IOException {
        int prevMp = player.mp;
        player.consumables.useMpPotion(player);
        int healAmount = player.mp - prevMp;

        assignedController.sendMessage(player.name + " drank mp potion and restored " + healAmount + " mp.");

        assignedController.updatePlayer_mp();
        assignedController.updatePlayer_mpPotions();

        opponentsTurn();
    }

    public void killOpponent(int opponentIndex) {
        Monster target = opponents.get(opponentIndex);

        Random randomNumber = new Random();

        opponents.remove(opponentIndex);

        int gainedExp = target.exp;
        int gainedGold = randomNumber.nextInt((target.highGold - target.lowGold) + 1) + target.lowGold;

        player.loot.addExp(gainedExp);
        player.loot.addGold(gainedGold);

        assignedController.sendMessage(target.name + " has died! [" + gainedExp + " exp, " + gainedGold + " gold]");

        while (player.checkLevelUp()) {
            assignedController.sendMessage("LEVEL UP!");
        }

        assignedController.updatePlayer_exp();
        assignedController.updatePlayer_nameLevel();
        assignedController.updatePlayer_gold();
    }

    public void opponentsTurn() throws IOException {
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

    public void victory() throws IOException {
        assignedController.sendMessage("");
        assignedController.sendMessage("You are victorious!");

        assignedController.setForestButtons();

        assignedController.saveGame();
    }

    public void defeat() throws IOException {
        assignedController.sendMessage("");
        assignedController.sendMessage("You were defeated!");
        assignedController.sendMessage("A new adventurer takes your place.");

        assignedController.setForestButtons();
        assignedController.clearMonsters();

        assignedController.resetMercenary();
        assignedController.saveGame();
    }

    public void flee() throws IOException {
        Random random = new Random();

        int diceRoll = random.nextInt(100);

        System.out.println(diceRoll);

        if (diceRoll <= 70) {
            assignedController.sendMessage("You have fled the battle!");

            assignedController.setForestButtons();
            assignedController.clearMonsters();

            assignedController.saveGame();
        } else {
            assignedController.sendMessage("You have failed to escape your pursuers!");
            opponentsTurn();
        }
    }
}

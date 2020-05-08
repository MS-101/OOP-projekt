package Combat;

import Entities.Monsters.Monster;
import Entities.Player.Mercenary;
import Entities.Player.PlayerConsumables;
import GUI.Game.Controllers.ForestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles combat between player and randomly generated monsters.
 * Player always starts first in each turn and after each player action remaining monsters attack the player.
 * Combat is ended after player flees the combat, dies or defeats all monsters.
 * Combat also prints appropriate messages to controllers combat log.
 */

public class Combat {
    Mercenary player;
    ForestController assignedController;

    public ArrayList<Monster> opponents = new ArrayList<>();
    int turn;

    /**
     * This constructor generates random opponents and assigns player
     * and forest controller to this class.
     *
     * @param player Player that entered the combat.
     * @param assignedController Controller of forest from which the combat is initialized.
     */

    public Combat(Mercenary player, ForestController assignedController) {
        this.player = player;
        this.assignedController = assignedController;
        this.turn = 1;

        generateOpponents();
    }

    /**
     * This method generates random opponents using MonsterList class
     */

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

    /**
     * This method is used for making player attack monster.
     * It also prints out messages about the attack into combat log.
     *
     * @param opponentIndex Index of targeted opponent in monster list.
     */

    public void useAttack(int opponentIndex) {
        Monster target = opponents.get(opponentIndex);

        boolean hadWeapon, critUsed;

        if (player.weapon != null) {
            hadWeapon = true;
        } else {
            hadWeapon = false;
        }

        int prevHp = target.hp;
        critUsed = player.attack(target);
        int dmg = prevHp - target.hp;

        if (dmg > 0) {
            if (critUsed) {
                assignedController.sendMessage(player.name + " attacks and deals " + dmg + " damage to " + target.name + ". [CRITICAL DAMAGE]");
            } else {
                assignedController.sendMessage(player.name + " attacks and deals " + dmg + " damage to " + target.name + ".");
            }
        } else {
            assignedController.sendMessage(player.name + " attacks and misses " + target.name + ".");
        }

        if (target.hp <= 0) {
            killOpponent(opponentIndex);
        }

        if (hadWeapon && player.weapon == null) {
            assignedController.sendMessage("Your weapon just broke!");
        }

        assignedController.updateForest_monsterHBox();
        assignedController.updatePlayer_weapon();

        if (opponents.size() > 0) {
            opponentsTurn();
        } else {
            victory();
        }
    }

    /**
     * This method is used for making player cast fireball on monster.
     * It also prints out messages about the spell casting into combat log.
     *
     * @param opponentIndex Index of targeted opponent in monster list.
     */

    public void useFireball(int opponentIndex) {
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

    /**
     * This method is used for making player cast flamestrike on all monsters.
     * It also prints out messages about the spell casting into combat log.
     */

    public void useFlamestrike() {
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

    /**
     * This method is used for making player cast heal on themselves.
     * It also prints out messages about the spell casting into combat log.
     */

    public void useHeal() {
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

    /**
     * This method is used for making player use health potion.
     * It also prints out messages about consumable usage into combat log.
     */

    public void useHpPotion() {
        int prevHp = player.hp;
        player.consumables.useHpPotion(player);
        int healAmount = player.hp - prevHp;

        assignedController.sendMessage(player.name + " drank hp potion and restored " + healAmount + " hp.");

        assignedController.updatePlayer_hp();
        assignedController.updatePlayer_hpPotions();

        opponentsTurn();
    }

    /**
     * This method is used for making player use mana potion.
     * It also prints out messages about consumable usage into combat log.
     */

    public void useMpPotion() {
        int prevMp = player.mp;
        player.consumables.useMpPotion(player);
        int healAmount = player.mp - prevMp;

        assignedController.sendMessage(player.name + " drank mp potion and restored " + healAmount + " mp.");

        assignedController.updatePlayer_mp();
        assignedController.updatePlayer_mpPotions();

        opponentsTurn();
    }

    /**
     * This method is used for removing monster from combat.
     * It grants the player loot from killed monster.
     * It also prints out messages about killed monster and loot gained from it.
     *
     * @param opponentIndex Index of targeted opponent in monster list.
     */

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

            assignedController.updatePlayer_nameLevel();
            assignedController.updatePlayer_attributes();
            assignedController.updatePlayer_skills();
        }

        assignedController.updatePlayer_exp();
        assignedController.updatePlayer_gold();
    }

    /**
     * This method is used for handling opponent turn.
     * During monster's turn the remaining opponents attack player.
     * If player's hp goes to 0 or below they die and they are defeated.
     * It also prints out messages about monster attacks.
     */

    public void opponentsTurn() {
        int i;

        assignedController.disableCombatButtons();

        for (i = 0; i < opponents.size(); i++) {
            Monster attacker = opponents.get(i);

            boolean hadArmor, critUsed;

            if (player.armor != null) {
                hadArmor = true;
            } else {
                hadArmor = false;
            }

            int prevHp = player.hp;
            critUsed = attacker.attack(player);
            int dmg = prevHp - player.hp;

            if (dmg > 0) {
                if (critUsed) {
                    assignedController.sendMessage(attacker.name + " attacks and deals " + dmg + " damage to " + player.name + ". [CRITICAL DAMAGE]");
                } else {
                    assignedController.sendMessage(attacker.name + " attacks and deals " + dmg + " damage to " + player.name + ".");
                }
            } else {
                assignedController.sendMessage(attacker.name + " attacks and misses " + player.name + ".");
            }

            assignedController.updatePlayer_hp();
            assignedController.updatePlayer_hpPotions();
            assignedController.updatePlayer_armor();

            if (hadArmor && player.armor == null) {
                assignedController.sendMessage("Your armor just broke!");
            }

            if (player.hp <= 0) {
                assignedController.sendMessage(player.name + " has died!");
                defeat();
                return;
            }
        }

        assignedController.enableCombatButtons();
        nextTurn();
    }

    /**
     * This method simply increments turn counter and prints out the current turn.
     */

    public void nextTurn() {
        turn++;

        assignedController.sendMessage("");
        assignedController.sendMessage("TURN " + turn + ":");
    }

    /**
     * This method prints out victory message, sets forest buttons and saves game.
     */

    public void victory() {
        assignedController.sendMessage("");
        assignedController.sendMessage("You are victorious!");

        assignedController.setForestButtons();

        assignedController.saveGame();
    }

    /**
     * This method prints out defeat message, sets forest buttons, clears monsters, resets mercenary and saves game.
     */

    public void defeat() {
        assignedController.sendMessage("");
        assignedController.sendMessage("You were defeated!");
        assignedController.sendMessage("A new adventurer takes your place.");

        assignedController.setForestButtons();
        assignedController.clearMonsters();

        assignedController.resetMercenary();
        assignedController.saveGame();
    }

    /**
     * This method allows the player to attempt escaping from combat.
     * There is a chance of failing to flee from battle.
     * When mercenary successfully escapes from combat monsters are cleared, forest buttons are set and game is saved.
     * When mercenary fails to escape they just wasted their turn and opponent turn follows.
     * It also prints out messages about escape result.
     */

    public void flee() {
        Random random = new Random();

        int diceRoll = random.nextInt(100);

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

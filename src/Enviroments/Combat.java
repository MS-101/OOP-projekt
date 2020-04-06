package Enviroments;

import Entities.Player.*;
import Entities.Monsters.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import MySystem.*;

public class Combat extends Location {
    Mercenary player;
    ArrayList<Monster> opponents;
    int goldGain, expGain;
    boolean playerEscaped;

    File accountsfile;
    AccountsHashTable myHashTable;

    public Combat(File accountsFile, AccountsHashTable myHashtable, Mercenary player, ArrayList<Monster> opponents) throws IOException {
        this.accountsfile = accountsFile;
        this.myHashTable = myHashtable;
        this.player = player;
        this.opponents = opponents;

        this.goldGain = 0;
        this.expGain = 0;

        start();
    }

    private void start() throws IOException {
        int turnCounter = 1;
        int i;

        System.out.print(this.player.name + " engaged in combat with " + this.opponents.get(0).name);
        for (i = 1; i < opponents.size(); i++) {
            if (i+1 == opponents.size()) {
                System.out.print(" and " + this.opponents.get(i).name);
            } else {
                System.out.print(", " + this.opponents.get(i).name);
            }
        }
        System.out.println("!");
        System.out.println();

        while (true) {
            System.out.println("TURN " + turnCounter + " START:");
            System.out.println();

            //Prints all remaining combatants
            System.out.println(this.player.name + ": " + this.player.hp + "/" + this.player.maxHp + " hp, " + this.player.mp + "/" + this.player.maxMp + " mp");
            for (i = 0; i < this.opponents.size(); i++) {
                    System.out.println(i+1 + ") " + this.opponents.get(i).name + ": " + this.opponents.get(i).hp + "/" + this.opponents.get(i).maxHp + " hp, " + this.opponents.get(i).mp + "/" + this.opponents.get(i).maxMp + " mp");
            }
            System.out.println();

            playerTurn();

            if (this.playerEscaped) {
                myAccountHandler.rewriteAccountsFile(accountsfile, myHashTable);
                return;
            }

            for (i = 0; i < this.opponents.size(); i++) {
                Monster pickedOpponent = opponents.get(i);

                if (pickedOpponent.hp <= 0) {
                    Random randomNumber = new Random();
                    int gainedGold;

                    gainedGold = randomNumber.nextInt((pickedOpponent.highGold - pickedOpponent.lowGold) + 1) + pickedOpponent.lowGold;

                    this.expGain += pickedOpponent.exp;
                    this.goldGain += gainedGold;

                    this.opponents.remove(i);
                }
            }

            if (this.opponents.size() == 0) {
                victory();
                myAccountHandler.rewriteAccountsFile(accountsfile, myHashTable);
                return;
            }

            for (i = 0; i < this.opponents.size(); i++) {
                opponentTurn(i);

                if (this.player.hp <= 0) {
                    defeat();
                    myAccountHandler.rewriteAccountsFile(accountsfile, myHashTable);
                    return;
                }
            }

            turnCounter++;
        }
    }

    private void playerTurn() {
        System.out.println("Your turn:");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("ATTACK <enemyIndex> - deal damage to a specific opponent");
        System.out.println("SPELL <spellName> {spellTarget} - use 'spell help' to view all available spells");
        if (this.player.consumables.hpPotions_amount > 0) {
            System.out.println("HP - use hp potion [heals " + this.player.consumables.hpPotion_heal + " hp] (" + this.player.consumables.hpPotions_amount + " remaining)");
        }
        if (this.player.consumables.mpPotions_amount > 0) {
            System.out.println("MP - use mp potion [heals " + this.player.consumables.mpPotion_heal + " mp] (" + this.player.consumables.mpPotions_amount + " remaining)");
        }
        System.out.println("SKIP - if you are feeling suicidal");
        System.out.println("FLEE - disengage from combat (may be unsuccessful)");
        System.out.println();

        while (true) {
            myCommand.readInput();

            if (myCommand.name.equalsIgnoreCase("ATTACK")) {
                if (myCommand.param1Str != null) {
                    if (myCommand.param1Int > 0 && myCommand.param1Int <= opponents.size()) {
                        int opponentIndex = myCommand.param1Int - 1;
                        Monster pickedOpponent = this.opponents.get(opponentIndex);

                        this.player.attack(pickedOpponent);
                        return;
                    } else {
                        System.out.println("Selected opponent does not exist.");
                        continue;
                    }
                } else {
                    System.out.println("You did not select an opponent.");
                    continue;
                }
            }

            if (myCommand.name.equalsIgnoreCase("SPELL")) {
                if (myCommand.param1Str != null) {
                    if (myCommand.param1Str.equalsIgnoreCase("help")) {
                        System.out.println();

                        if (player.skills.fireball.curLvl > 0) {
                            System.out.println("Fireball <targetIndex> [lvl " + player.skills.fireball.curLvl + "] (" + player.skills.fireball.mpCost + " mp): Deal " +  player.skills.fireball.lowSpellDmg + "-" + player.skills.fireball.highSpellDmg + " dmg to a single target.");
                        }

                        if (player.skills.flamestrike.curLvl > 0) {
                            System.out.println("Flamestrike [lvl " + player.skills.flamestrike.curLvl + "] (" + player.skills.flamestrike.mpCost + " mp): Deal " +  player.skills.flamestrike.lowSpellDmg + "-" + player.skills.flamestrike.highSpellDmg + " dmg to all targets.");
                        }

                        if (player.skills.heal.curLvl > 0) {
                            System.out.println("Heal [lvl " + player.skills.heal.curLvl + "] (" + player.skills.heal.mpCost + " mp): Heal " +  player.skills.heal.mpCost + " hp.");
                        }

                        System.out.println();

                        continue;
                    }

                    if (myCommand.param1Str.equalsIgnoreCase("fireball")) {
                        if (player.skills.fireball.curLvl > 0) {
                            if (player.mp >= player.skills.fireball.mpCost) {
                                if (myCommand.param2Str != null) {
                                    if (myCommand.param2Int > 0 && myCommand.param2Int <= opponents.size()) {
                                        int opponentIndex = myCommand.param2Int - 1;
                                        Monster selectedOpponent = opponents.get(opponentIndex);

                                        player.skills.fireball.cast(player, selectedOpponent);
                                        break;
                                    } else {
                                        System.out.println("Selected opponent does not exist.");
                                        continue;
                                    }
                                } else {
                                    System.out.println("You did not select an opponent.");
                                    continue;
                                }
                            } else {
                                System.out.println("You don't have enough mana to cast this spell.");
                                continue;
                            }
                        } else {
                            System.out.println("You have not learnt this spell yet.");
                            continue;
                        }
                    }

                    if (myCommand.param1Str.equalsIgnoreCase("flamestrike")) {
                        if (player.skills.flamestrike.curLvl > 0) {
                            if (player.mp >= player.skills.flamestrike.mpCost) {
                                player.skills.flamestrike.cast(player, opponents);
                                break;
                            } else {
                                System.out.println("You don't have enough mana to cast this spell.");
                                continue;
                            }
                        } else {
                            System.out.println("You have not learnt this spell yet.");
                            continue;
                        }
                    }

                    if (myCommand.param1Str.equalsIgnoreCase("heal")) {
                        if (player.skills.heal.curLvl > 0) {
                            if (player.mp >= player.skills.heal.mpCost) {
                                player.skills.heal.cast(player);
                                break;
                            } else {
                                System.out.println("You don't have enough mana to cast this spell.");
                                continue;
                            }
                        } else {
                            System.out.println("You have not learnt this spell yet.");
                            continue;
                        }
                    }

                    System.out.println("Invalid ability name.");
                    continue;
                } else {
                    System.out.println("You did not select an ability.");
                    continue;
                }
            }

            if (this.player.consumables.hpPotions_amount > 0 && myCommand.name.equalsIgnoreCase("HP")) {
                if (this.player.useHpPotion()) {
                    return;
                } else {
                    continue;
                }
            }

            if (this.player.consumables.mpPotions_amount > 0 && myCommand.name.equalsIgnoreCase("MP")) {
                if (this.player.useMpPotion()) {
                    return;
                } else {
                    continue;
                }
            }

            if (myCommand.name.equalsIgnoreCase("SKIP")) {
                System.out.println("You have decided to skip your turn.");
                System.out.println();

                return;
            }

            if (myCommand.name.equalsIgnoreCase("FLEE")) {
                Random randomNumber = new Random();
                int diceRoll = randomNumber.nextInt(100);

                if (diceRoll < 60) {
                    System.out.println("You may live to fight another day.");
                    System.out.println();

                    playerEscaped = true;
                    return;
                } else {
                    System.out.println("You have failed to lose your pursuers.");
                    System.out.println();

                    playerEscaped = false;
                    return;
                }

            }

            System.out.println("Incorrect command!");
        }
    }

    private void opponentTurn(int opponentIndex) {
        Monster pickedOpponent;

        pickedOpponent = this.opponents.get(opponentIndex);

        System.out.println(opponentIndex+1 + ") " + pickedOpponent.name + "'s turn:");
        System.out.println();

        pickedOpponent.attack(this.player);
    }

    private void victory() {
        System.out.println("You emerge victorious!");

        this.player.loot.addGold(this.goldGain);
        if (this.player.lvl < this.player.maxLvl) {
            this.player.loot.addExp(this.expGain);
            this.player.checkLevelUp();
        }

        System.out.println("You have gained " + this.goldGain + " gold and " + this.expGain + " exp");
        System.out.println("You currently have " + this.player.loot.gold + " gold and " + this.player.loot.exp + "/" + this.player.lvlRequirement + " exp");
        System.out.println();
    }

    private void defeat() {
        System.out.println("You have been defeated in combat!");
        System.out.println("A new adventurer takes your place.");
        System.out.println();

        this.player.reset();
    }
}

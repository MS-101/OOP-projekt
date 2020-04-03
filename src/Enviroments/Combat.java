package Enviroments;

import Entities.Player.*;
import Entities.Monsters.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Combat {
    Mercenary player;
    ArrayList<Monster> opponents;
    int goldGain, expGain;

    public Combat(Mercenary player, ArrayList<Monster> opponents) {
        this.player = player;
        this.opponents = opponents;

        this.goldGain = 0;
        this.expGain = 0;

        start();
    }

    private void start() {
        int turnCounter = 1;
        int i;
        boolean legalCombat;

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

        legalCombat = true;

        while (legalCombat) {
            System.out.println("TURN " + turnCounter + " START:");
            System.out.println();

            //Prints all remaining combatants
            System.out.println(this.player.name + ": " + this.player.hp + "/" + this.player.maxHp + " hp, " + this.player.mp + "/" + this.player.maxMp + " mp");
            for (i = 0; i < this.opponents.size(); i++) {
                    System.out.println(i+1 + ") " + this.opponents.get(i).name + ": " + this.opponents.get(i).hp + "/" + this.opponents.get(i).maxHp + " hp, " + this.opponents.get(i).mp + "/" + this.opponents.get(i).maxMp + " mp");
            }
            System.out.println();

            if (playerTurn()) {
                return;
            }

            if (this.opponents.size() == 0) {
                break;
            }

            for (i = 0; i < this.opponents.size(); i++) {
                opponentTurn(i);

                if (this.player.hp <= 0) {
                    legalCombat = false;
                    break;
                }
            }

            turnCounter++;
        }

        if (this.player.hp > 0) {
            victory();
        } else {
            defeat();
        }
    }

    //this function return true when player successfully escaped from combat
    private boolean playerTurn() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Your turn:");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("ATTACK 'ENEMY_INDEX' - deal damage to a specific opponent");
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
            String command = myScanner.nextLine();
            String commandParameter = null;

            StringTokenizer st = new StringTokenizer(command," ");
            int commandParameterInt = 0;

            String commandName = st.nextToken();;
            if (st.hasMoreTokens()) {
                commandParameter = st.nextToken();
                if (commandParameter.matches("[0-9]+")) {
                    commandParameterInt = Integer.parseInt(commandParameter);
                }
            }

            if (commandName.equalsIgnoreCase("ATTACK")) {
                if (commandParameter != null) {
                    if (commandParameterInt > 0 && commandParameterInt <= opponents.size()) {
                        this.player.attack(this.opponents.get(commandParameterInt - 1));

                        if (this.opponents.get(commandParameterInt - 1).hp < 0) {
                            Random randomNumber = new Random();
                            int gainedGold;

                            gainedGold = randomNumber.nextInt((this.opponents.get(commandParameterInt - 1).highGold - this.opponents.get(commandParameterInt - 1).lowGold) + 1) + this.opponents.get(commandParameterInt - 1).lowGold;

                            this.expGain += this.opponents.get(commandParameterInt - 1).exp;
                            this.goldGain += gainedGold;

                            this.opponents.remove(commandParameterInt - 1);
                        }

                        return false;
                    } else {
                        System.out.println("Selected opponent does not exist.");
                        continue;
                    }
                } else {
                    System.out.println("You did not select an opponent.");
                    continue;
                }
            }

            if (this.player.consumables.hpPotions_amount > 0 && commandName.equalsIgnoreCase("HP")) {
                if (this.player.useHpPotion()) {
                    return false;
                } else {
                    continue;
                }
            }

            if (this.player.consumables.mpPotions_amount > 0 && commandName.equalsIgnoreCase("MP")) {
                if (this.player.useMpPotion()) {
                    return false;
                } else {
                    continue;
                }
            }

            if (commandName.equalsIgnoreCase("SKIP")) {
                System.out.println("You have decided to skip your turn.");
                System.out.println();

                return false;
            }

            if (commandName.equalsIgnoreCase("FLEE")) {
                Random randomNumber = new Random();
                int diceRoll = randomNumber.nextInt(100);

                if (diceRoll < 60) {
                    System.out.println("You may live to fight another day.");
                    System.out.println();
                    return true;
                } else {
                    System.out.println("You have failed to lose your pursuers.");
                    System.out.println();
                    return false;
                }

            }

            System.out.println("Incorrect command!");
        }
    }

    private void opponentTurn(int opponentIndex) {
        System.out.println(opponentIndex+1 + ") " + this.opponents.get(opponentIndex).name + "'s turn:");
        System.out.println();

        this.opponents.get(opponentIndex).attack(this.player);
    }

    private void victory() {
        this.player.loot.addExp(this.expGain);
        this.player.loot.addGold(this.goldGain);

        System.out.println("You emerge victorious!");
        System.out.println("You have gained " + this.expGain + " exp and " + this.goldGain + " gold");
        System.out.println("You currently have " + this.player.loot.exp + " exp and " + this.player.loot.gold + " gold");
        System.out.println();
    }

    private void defeat() {
        System.out.println("You have been defeated in combat.");
        System.out.println();
    }
}
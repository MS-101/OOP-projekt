package Enviroments;

import Entities.Player.Mercenary;
import MySystem.*;
import java.io.File;
import java.io.IOException;

public class Inn extends Location {
    int roomCost = 25;
    int roomComfort = 30;

    public void visit(File accountsFile, AccountsHashTable myHashtable, Mercenary player, Village myVillage) throws IOException {
        AccountHandler myAccountManager = new AccountHandler();

        while (true) {
            System.out.println("\"How may I help you?\" the trader asks.");
            System.out.println("You currently have " + player.loot.gold + " gold.");
            System.out.println(player.name + ": " + player.hp + "/" + player.maxHp + " " + player.mp + "/" + player.maxMp);
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("REST - rent a room and spend the night here [" + roomCost + " gold] (restores " + roomComfort + " % hp and mp)");
            System.out.println("SAVE - do this before exiting the game [game auto-saves after combat]");
            System.out.println("QUIT - exits the game");
            System.out.println("RETURN - your business is concluded here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("REST")) {
                    if (player.loot.gold >= roomCost) {

                        System.out.println("You decide to spend the night here.");
                        player.loot.payGold(roomCost);
                        System.out.println();

                        int healAmount = (int)(((double)roomComfort / (double)100) * player.maxHp);
                        player.healAll(healAmount);

                        myVillage.myForge.generateInventory();

                        break;
                    } else {
                        System.out.println("You don't have enough gold to pay for this.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("SAVE")) {
                    System.out.println("Game progress saved!");
                    System.out.println();

                    myAccountManager.rewriteAccountsFile(accountsFile, myHashtable);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("QUIT")) {
                    System.exit(0);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("RETURN")) {
                    System.out.println("\"Farewell,\" he says as you leave the inn.");
                    System.out.println();
                    return;
                }

                System.out.println("Invalid command!");
            }
        }

    }
}

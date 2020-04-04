package Enviroments;

import Entities.Player.Mercenary;

public class Inn extends Location {
    int roomCost = 25;
    int roomComfort = 30;

    public void visit(Mercenary player) {
        while (true) {
            System.out.println("\"How may I help you?\" the trader asks.");
            System.out.println("You currently have " + player.loot.gold + " gold.");
            System.out.println(player.name + ": " + player.hp + "/" + player.maxHp + " " + player.mp + "/" + player.maxMp);
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("REST - rent a room and spend the night here [" + roomCost + " gold] (restores " + roomComfort + " % hp and mp)");
            System.out.println("RETURN - your business is concluded here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("REST")) {
                    if (player.loot.gold >= roomCost) {
                        System.out.println("You decide to spend the night here.");
                        System.out.println();

                        player.hp += ((double)roomComfort / 100) * player.maxHp;

                        if (player.hp > player.maxHp) {
                            player.hp = player.maxHp;
                        }

                        player.loot.payGold(roomCost);

                        break;
                    } else {
                        System.out.println("You don't have enough gold to pay for this.");
                        continue;
                    }
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

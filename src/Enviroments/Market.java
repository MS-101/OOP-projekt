package Enviroments;

import Entities.Player.Mercenary;
import MySystem.*;
import java.io.File;

public class Market extends Location {
    int hpCost = 20, mpCost = 20;

    public void visit(File accountsFile, AccountsHashTable myHashtable, Mercenary player, Village myVillage) {
        while (true) {
            System.out.println("\"How may I help you?\" the trader asks.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("HP <amount> - buy hp potions [" + this.hpCost + " gold each] (" + player.consumables.hpPotions_amount + "/" + player.consumables.hpPotions_maxAmount + ")");
            System.out.println("MP <amount> - buy mp potions [" + this.mpCost + " gold each] (" + player.consumables.mpPotions_amount + "/" + player.consumables.mpPotions_maxAmount + ")");
            System.out.println("RETURN - your business is concluded here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("HP")) {
                    if (myCommand.param1Str != null) {
                        if (myCommand.param1Int > 0) {
                            if (player.consumables.hpPotions_amount + myCommand.param1Int <= player.consumables.hpPotions_maxAmount) {
                                if (player.loot.gold >= this.hpCost * myCommand.param1Int) {
                                    System.out.println("Trader sells you " + myCommand.param1Int + " hp potions.");
                                    player.loot.payGold(myCommand.param1Int * hpCost);
                                    System.out.println();

                                    player.consumables.addHpPotions(myCommand.param1Int);
                                    break;
                                } else {
                                    System.out.println("You don't have enough gold to pay for this.");
                                    continue;
                                }
                            } else {
                                System.out.println("You don't have enough inventory space.");
                                continue;
                            }
                        } else {
                            System.out.println("You selected invalid amount.");
                            continue;
                        }
                    } else {
                        System.out.println("You did not select amount.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("MP")) {
                    if (myCommand.param1Str != null) {
                        if (myCommand.param1Int > 0) {
                            if (player.consumables.mpPotions_amount + myCommand.param1Int <= player.consumables.mpPotions_maxAmount) {
                                if (player.loot.gold >= this.mpCost * myCommand.param1Int) {
                                    System.out.println("Trader sells you " + myCommand.param1Int + " mp potions.");
                                    player.loot.payGold(myCommand.param1Int * mpCost);
                                    System.out.println();

                                    player.consumables.addMpPotions(myCommand.param1Int);
                                    break;
                                } else {
                                    System.out.println("You don't have enough gold to pay for this.");
                                    continue;
                                }
                            } else {
                                System.out.println("You don't have enough inventory space.");
                                continue;
                            }
                        } else {
                            System.out.println("You selected invalid amount.");
                            continue;
                        }
                    } else {
                        System.out.println("You did not select amount.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("RETURN")) {
                    System.out.println("\"Farewell,\" he says as you leave the market.");
                    System.out.println();
                    return;
                }

                System.out.println("Invalid command!");
            }
        }

    }
}

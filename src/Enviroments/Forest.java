package Enviroments;

import java.util.ArrayList;
import java.util.Random;
import Entities.Player.Mercenary;
import Entities.Monsters.*;

public class Forest extends Location {
    public void visit(Mercenary player) {
        int i;

        while (true) {
            System.out.println("Do you want to hunt here?");
            System.out.println();

            System.out.println(player.name + " [lvl " + player.lvl + "] :");
            System.out.println("status: " + player.hp + "/" + player.maxHp + " hp, " + player.mp + "/" + player.maxMp + " mp");
            System.out.println("consumables: " + player.consumables.hpPotions_amount + "/" + player.consumables.hpPotions_maxAmount + " hp potions, " + player.consumables.mpPotions_amount + "/" + player.consumables.hpPotions_maxAmount + " mp potions");
            System.out.println("loot: " + player.loot.gold + " gold, " + player.loot.exp + "/" + player.lvlRequirement + " exp");
            if (player.weapon != null) {
                System.out.println("weapon (" + player.weapon.name + "): " + player.weapon.durability + "/" + player.weapon.maxDurability + " durability");
            } else {
                System.out.println("weapon: null");
            }
            if (player.armor != null) {
                System.out.println("armor (" + player.armor.name + "): " + player.armor.durability + "/" + player.armor.maxDurability + " durability");
            } else {
                System.out.println("armor: null");
            }
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("HUNT - find and attempt to slay some monsters");
            System.out.println("RETURN - come back to the village to resupply, rest or pick up your reward");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("HUNT")) {
                    Random randomNumber = new Random();
                    ArrayList<Monster> opponents = new ArrayList<Monster>();;
                    int monsterCount = randomNumber.nextInt(3) + 1;

                    System.out.println("You have decided to hunt some monsters.");
                    System.out.println();

                    for (i = 0; i < monsterCount; i++) {
                        opponents.add(new Ghoul());
                    }

                    new Combat(player, opponents);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("STATUS")) {
                    System.out.println("You have returned to the village.");
                    System.out.println();
                    return;
                }

                if (myCommand.name.equalsIgnoreCase("RETURN")) {
                    System.out.println("You have returned to the village.");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }
        }
    }
}

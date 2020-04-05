package Enviroments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import Entities.Player.Mercenary;
import Entities.Monsters.*;

public class Forest extends Location {
    ArrayList<Monster> monsterLootTable;

    public Forest() {
        int i;
        monsterLootTable = new ArrayList<Monster>();

        for (i = 0; i < 40; i++) {
            monsterLootTable.add(new Ghoul());
        }
        for (i = 0; i < 25; i++) {
            monsterLootTable.add(new Alghoul());
        }
        for (i = 0; i < 25; i++) {
            monsterLootTable.add(new Giant_Spider());
        }
        for (i = 0; i < 10; i++) {
            monsterLootTable.add(new Giant());
        }
    }

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
                System.out.println("weapon: N/A");
            }
            if (player.armor != null) {
                System.out.println("armor (" + player.armor.name + "): " + player.armor.durability + "/" + player.armor.maxDurability + " durability");
            } else {
                System.out.println("armor: N/A");
            }
            System.out.println();

            if (player.attributePoints > 0 || player.skillPoints > 0) {
                if (player.attributePoints > 0) {
                    System.out.println("You have unallocated " + player.attributePoints + " attribute points!");
                }
                if (player.skillPoints > 0) {
                    System.out.println("You have unallocated " + player.skillPoints + " skill points!");
                }
                System.out.println();
            }

            System.out.println("Enter one of the following commands:");
            System.out.println("HUNT - find and attempt to slay some monsters");
            System.out.println("RETURN - come back to the village to resupply, rest or pick up your reward");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("HUNT")) {
                    Random randomNumber = new Random();
                    ArrayList<Monster> opponents = new ArrayList<Monster>();

                    int monsterCount = randomNumber.nextInt(3) + 1;

                    System.out.println("You have decided to hunt some monsters.");
                    System.out.println();

                    for (i = 0; i < monsterCount; i++) {
                        int randomIndex = randomNumber.nextInt(monsterLootTable.size());

                        Monster newMonster = this.monsterLootTable.get(randomIndex).getCopy();

                        opponents.add(newMonster);
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

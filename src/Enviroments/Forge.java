package Enviroments;

import java.util.ArrayList;
import java.util.Random;

import Entities.Player.Mercenary;
import Items.Item;
import Items.Weapons.*;
import Items.Armor.*;

public class Forge extends Location {
    ArrayList<Item> forgeInventory;
    ArrayList<Item> forgeLootTable;
    int forgeInventoryLimit;

    public Forge() {
        forgeInventory = new ArrayList<Item>();
        forgeLootTable = new ArrayList<Item>();

        forgeLootTable.add(new Copper_Dagger());
        forgeLootTable.add(new Copper_Gladius());
        forgeLootTable.add(new Copper_Spear());
        forgeLootTable.add(new Copper_Axe());

        forgeLootTable.add(new Leather_Armor());

        forgeInventoryLimit = 8;
        generateInventory();
    }

    public void generateInventory() {
        Random randomNumber = new Random();
        Item newItem;
        int i, randomIndex;

        forgeInventory.clear();

        for (i = 0; i < forgeInventoryLimit; i++) {
            randomIndex = randomNumber.nextInt(forgeLootTable.size());

            newItem = forgeLootTable.get(randomIndex).getCopy();

            forgeInventory.add(newItem);
        }
    }

    public void visit(Mercenary player) {
        while (true) {
            System.out.println("\"How may I help you, sir?\" the blacksmith asks.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("TRADE - replace your old equipment with new one");
            System.out.println("REPAIR - fix damaged equipment before it breaks permamently");
            System.out.println("RETURN - your business is concluded here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("TRADE")) {
                    System.out.println("\"What do you have for sale?\"");
                    System.out.println();

                    this.trade(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("REPAIR")) {
                    System.out.println("\"My equipment could use some repairs.\"");
                    System.out.println();

                    this.repair(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("RETURN")) {
                    System.out.println("\"Farewell,\" he says as you leave the forge");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }


        }
    }

    private void trade(Mercenary player) {
        Item pickedItem;
        int i;

        while (true) {
            System.out.println("\"Have a look at my wares,\" he replies.");
            System.out.println("You currently have " + player.loot.gold + " gold.");
            System.out.println();

            System.out.println("Your inventory:");
            if (player.weapon != null) {
                System.out.print("weapon (" + player.weapon.name + "):");
                System.out.print(" (" + player.weapon.lowPhysicalDmg + "-" + player.weapon.highPhysicalDmg + " physical dmg)");
                System.out.print(" (" + player.weapon.lowPiercingDmg + "-" + player.weapon.highPiercingDmg + " piercing dmg)");
                System.out.println();
            } else {
                System.out.println("weapon: N/A");
            }
            if (player.armor != null) {
                System.out.print("armor (" + player.armor.name + "):");
                System.out.print(" (" + player.armor.armorVal + " armor)");
                System.out.println();
            } else {
                System.out.println("armor: N/A");
            }
            System.out.println();

            System.out.println("Blacksmith's inventory:");
            for (i = 0; i < this.forgeInventory.size(); i++) {
                pickedItem = this.forgeInventory.get(i);
                System.out.print(i+1 + ") " + pickedItem.name + " [" + pickedItem.cost + " gold]:");

                if (pickedItem instanceof Weapon) {
                    if (((Weapon) pickedItem).lowPhysicalDmg != 0 || ((Weapon) pickedItem).highPhysicalDmg != 0) {
                        System.out.print(" (" + ((Weapon) pickedItem).lowPhysicalDmg + "-" + ((Weapon) pickedItem).highPhysicalDmg + " physical dmg)");
                    }
                    if (((Weapon) pickedItem).lowPiercingDmg != 0 || ((Weapon) pickedItem).highPiercingDmg != 0) {
                        System.out.print(" (" + ((Weapon) pickedItem).lowPiercingDmg + "-" + ((Weapon) pickedItem).highPiercingDmg + " piercing dmg)");
                    }
                    System.out.println();
                }

                if (pickedItem instanceof Armor) {
                    System.out.print(" (" + ((Armor) pickedItem).armorVal + " armor)");
                    System.out.println();
                }
            }
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("BUY <itemIndex> - your gear's quality prolongs your longevity");
            System.out.println("FINISH - perhaps there is something else that you need");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("BUY")) {
                    if (myCommand.param1Str != null) {
                        if (myCommand.param1Int > 0 && myCommand.param1Int <= this.forgeInventory.size()) {
                            pickedItem = this.forgeInventory.get(myCommand.param1Int - 1);

                            if (player.loot.gold >= pickedItem.cost) {
                                System.out.println("\"Pleasure doing business with you,\" the smith says contentedly.");
                                System.out.println();

                                player.loot.payGold(pickedItem.cost);
                                if (pickedItem instanceof  Weapon) {
                                    player.setWeapon((Weapon) pickedItem);
                                } else {
                                    player.setArmor((Armor) pickedItem);
                                }

                                this.forgeInventory.remove(myCommand.param1Int - 1);

                                break;
                            } else {
                                System.out.println("You don't have enough gold to pay for this.");
                                continue;
                            }
                        } else {
                            System.out.println("Selected item does not exist.");
                            continue;
                        }
                    } else {
                        System.out.println("You did not select an item.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("FINISH")) {
                    System.out.println("Do I still need anything here?");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }
        }
    }

    private void repair(Mercenary player) {
        int weaponRepairCost = 0, armorRepairCost = 0;

        if (player.weapon != null && player.weapon.durability != player.weapon.maxDurability) {
            weaponRepairCost = (int)(((double)(player.weapon.maxDurability - player.weapon.durability) / (double)player.weapon.maxDurability) * player.weapon.repairCost);
        }

        if (player.armor != null && player.armor.durability != player.armor.maxDurability) {
            armorRepairCost = (int)((double)(player.armor.maxDurability - player.armor.durability) / (double)player.armor.maxDurability * player.armor.repairCost);
        }

        while (true) {
            System.out.println("\"What do you need repaired?\" the blacksmith asks.");
            System.out.println("You currently have " + player.loot.gold + " gold.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            if (weaponRepairCost != 0) {
                System.out.println("WEAPON - repair your currently equipped weapon (" + player.weapon.durability + "/" + player.weapon.maxDurability + " durability) [" + weaponRepairCost + " gold]");
            }
            if (armorRepairCost != 0) {
                System.out.println("ARMOR - repair your currently worn armor (" + player.armor.durability + "/" + player.armor.maxDurability + " durability) [" + armorRepairCost + " gold]");
            }
            System.out.println("FINISH - perhaps there is something else that you need");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (weaponRepairCost != 0 && myCommand.name.equalsIgnoreCase("WEAPON")) {
                    if (player.loot.gold >= weaponRepairCost) {
                        System.out.println("Smith repairs your weapon.");
                        System.out.println();

                        player.loot.payGold(weaponRepairCost);
                        weaponRepairCost = 0;
                        player.weapon.repairItem();
                    } else {
                        System.out.println("You don't have enough gold to pay for this.");
                        System.out.println();
                    }

                    break;
                }

                if (armorRepairCost != 0 && myCommand.name.equalsIgnoreCase("ARMOR")) {
                    if (player.loot.gold >= armorRepairCost) {
                        System.out.println("Smith repairs your armor.");
                        System.out.println();

                        player.loot.payGold(armorRepairCost);
                        armorRepairCost = 0;
                        player.armor.repairItem();

                        break;
                    } else {
                        System.out.println("You don't have enough gold to pay for this.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("FINISH")) {
                    System.out.println("Do I still need anything here?");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }
        }
    }
}

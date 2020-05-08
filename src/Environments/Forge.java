package Environments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import Entities.Player.Mercenary;
import Items.Item;
import Items.Weapons.*;
import Items.Armor.*;

/**
 * Player can buy and repair items here.
 * Forge inventory is refreshed after resting at inn.
 */

public class Forge implements Serializable {
    public ArrayList<Item> forgeInventory;
    public ArrayList<Item> forgeLootTable;
    public int forgeInventoryLimit;

    /**
     * This constructor creates forge loot table and generates
     * forge inventory for the first tune.
     */

    public Forge() {
        forgeInventory = new ArrayList<>();
        forgeLootTable = new ArrayList<>();

        forgeLootTable.add(new Copper_Dagger());
        forgeLootTable.add(new Copper_Gladius());
        forgeLootTable.add(new Copper_Spear());
        forgeLootTable.add(new Copper_Axe());

        forgeLootTable.add(new Leather_Armor());

        forgeInventoryLimit = 8;
        generateInventory();
    }

    /**
     * Clears forge inventory, picks random items from forge loot table
     * and adds them to forge inventory.
     */

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

    /**
     * This method makes the customer pay for picked item
     * and equips the buyer with it.
     *
     * @param customer Mercenary that is buying the item.
     * @param itemID Index of bought item.
     */

    public void buyItem(Mercenary customer, int itemID) {
        Item pickedItem = this.forgeInventory.get(itemID);

        customer.loot.payGold(pickedItem.cost);

        if (pickedItem instanceof Weapon) {
            customer.setWeapon((Weapon)pickedItem);
        } else if (pickedItem instanceof Armor) {
            customer.setArmor((Armor)pickedItem);
        }

        this.forgeInventory.remove(itemID);
    }

    /**
     * This method makes the customer pay for repairs
     * and smith repairs the damaged item.
     *
     * @param customer Mercenary that is paying for repairs.
     * @param damagedItem Damaged item to be repaired.
     */

    public void repairItem(Mercenary customer, Item damagedItem) {
        int payment = (int)(((double)(damagedItem.maxDurability - damagedItem.durability) / (double)damagedItem.maxDurability) * damagedItem.repairCost);

        customer.loot.payGold(payment);
        damagedItem.repairItem();
    }
}

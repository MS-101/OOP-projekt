package Enviroments;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Entities.Player.Mercenary;
import Items.Item;
import Items.Weapons.*;
import Items.Armor.*;
import MySystem.*;

public class Forge {
    public ArrayList<Item> forgeInventory;
    public ArrayList<Item> forgeLootTable;
    public int forgeInventoryLimit;

    public Forge() {
        int i;
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
}

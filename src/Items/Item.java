package Items;

import java.io.Serializable;

/**
 * Parent class of all armor and weapons.
 * Contains information such as cost, repair cost, durability and name.
 * This class doesn't have a constructor but it contains methods,
 * that allow its child classes to set the values of this class.
 */

public abstract class Item implements Serializable {
    public int cost, repairCost;
    public int durability, maxDurability;
    public String name;

    /**
     * Sets the cost and repair cost of item.
     * Repair cost is always smaller than item cost,
     * so it is favourable to repair item rather than buying a new one.
     *
     * @param cost Amount of gold needed to buy this item.
     * @param repairCost Amount of gold needed to fully repair this item.
     */

    public void setCost(int cost, int repairCost) {
        this.cost = cost;
        this.repairCost = repairCost;
    }

    /**
     * Sets the maximum durability of item.
     * It also sets the current durability to maximum durability.
     *
     * @param maxDurability Amount of uses this item has before breaking.
     */

    public void setDurability(int maxDurability) {
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
    }

    /**
     * Restores item durability to its maximum value.
     */

    public void repairItem() {
        this.durability = this.maxDurability;
    }

    /**
     * Sets the name of item.
     *
     * @param name String that is used for setting name of item.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets values of item copy.
     * It is used in forge loot table.
     *
     * @param itemCopy Copy of item.
     */

    public void copy(Item itemCopy) {
        itemCopy.setCost(this.cost, this.repairCost);
        itemCopy.setDurability(this.maxDurability);
        itemCopy.setName(this.name);
    }

    /**
     * Abstract creation of copy.
     * It is used in forge loot table.
     *
     * @return Returns copy of item.
     */

    public abstract Item getCopy();
}

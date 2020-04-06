package Items;

import java.io.Serializable;

public abstract class Item implements Serializable {
    public int cost, repairCost;
    public int durability, maxDurability;
    public String name;

    public void setCost(int cost, int repairCost) {
        this.cost = cost;
        this.repairCost = repairCost;
    }

    public void setDurability(int maxDurability) {
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
    }

    public void repairItem() {
        this.durability = this.maxDurability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void copy(Item itemCopy) {
        itemCopy.setCost(this.cost, this.repairCost);
        itemCopy.setDurability(this.maxDurability);
        itemCopy.setName(this.name);
    }

    public abstract Item getCopy();
}

package Items;

public class Item {
    int cost, repairCost;
    int durability, maxDurability;

    public void setCost(int cost, int repairCost) {
        this.cost = cost;
        this.repairCost = repairCost;
    }

    public void setDurability(int maxDurability) {
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
    }
}

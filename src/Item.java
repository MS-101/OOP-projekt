public class Item {
    int cost, repairCost;
    int durability, maxDurability;

    void setCost(int cost, int repairCost) {
        this.cost = cost;
        this.repairCost = repairCost;
    }

    void setDurability(int maxDurability) {
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
    }
}

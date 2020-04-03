package Items;

public class Item {
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
}

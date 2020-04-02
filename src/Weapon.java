public class Weapon extends Item {
    int lowPhysicalDmg, highPhysicalDmg;
    int lowPiercingDmg, highPiercingDmg;

    public void setPhysicalDmg(int lowPhysicalDmg, int highPhysicalDmg) {
        this.lowPhysicalDmg = lowPhysicalDmg;
        this.highPhysicalDmg = highPhysicalDmg;
    }

    public void setPiercingDmg(int lowPiercingDmg, int highPiercingDmg) {
        this.lowPiercingDmg = lowPiercingDmg;
        this.highPiercingDmg = highPiercingDmg;
    }
}

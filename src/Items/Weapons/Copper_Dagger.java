package Items.Weapons;

import java.io.Serializable;

public class Copper_Dagger extends Weapon {
    public Copper_Dagger() {
        setPhysicalDmg(5, 6);
        setPiercingDmg(3, 4);
        setCost(75, 40);
        setDurability(40);
        setName("Copper Dagger");
    }
}

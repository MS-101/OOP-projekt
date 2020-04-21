package Skills;

import Entities.Entity;

import java.io.Serializable;

public class Skill implements Serializable {
    public int curLvl, maxLvl;
    public int mpCost;

    public void cast(Entity caster) {
        caster.mp -= mpCost;
    }
}

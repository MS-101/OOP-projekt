package Skills;

import Entities.Entity;

import java.io.Serializable;

/**
 * Parent class of all skills.
 * Contains information such as mana cost, current and maximum level of ability.
 */

public class Skill implements Serializable {
    public int curLvl, maxLvl;
    public int mpCost;

    /**
     * Substracts mana cost from caster's mana.
     *
     * @param caster Entity that casted this spell.
     */

    public void cast(Entity caster) {
        caster.mp -= mpCost;
    }
}

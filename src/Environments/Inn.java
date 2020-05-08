package Environments;

import Entities.Player.Mercenary;

import java.io.Serializable;

/**
 * You can rest here to restore hp and mp and generate new forge inventory.
 */

public class Inn implements Serializable {
    public int roomCost = 30;
    public double roomComfort = 0.4;

    /**
     * Restores hp and mp for a gold cost.
     *
     * @param myMercenary Player that used this facility.
     * @param myForge Forge, which inventory will be refreshed.
     */

    public void rest(Mercenary myMercenary, Forge myForge) {
        int healHpAmount = (int)(myMercenary.maxHp * roomComfort);
        int healMpAmount = (int)(myMercenary.maxMp * roomComfort);

        myMercenary.healHp(healHpAmount);
        myMercenary.healMp(healMpAmount);
        myMercenary.loot.payGold(roomCost);

        myForge.generateInventory();
    }
}

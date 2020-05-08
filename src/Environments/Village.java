package Environments;

import java.io.Serializable;

/**
 * This class stores the player's village data.
 * It contains all information about the village establishments.
 */

public class Village implements Serializable {
    public Inn myInn = new Inn();
    public Forge myForge = new Forge();
    public Market myMarket = new Market();
}


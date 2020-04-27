package Environments;

import java.io.Serializable;

public class Village implements Serializable {
    public Inn myInn = new Inn();
    public Forge myForge = new Forge();
    public Market myMarket = new Market();
    public Forest myForest = new Forest();
}


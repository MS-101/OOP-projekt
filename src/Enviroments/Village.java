package Enviroments;

import Entities.Player.Mercenary;

public class Village extends Location {
    Inn myInn;
    Forge myForge;
    Market myMarket;
    Church myChurch;
    Forest myForest;

    public Village() {
        myInn = new Inn();
        myForge = new Forge();
        myMarket = new Market();
        myChurch = new Church();
        myForest = new Forest();
    }

    public void visit(Mercenary player) {
        while (true) {
            System.out.println("Choose a location of your next visit.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("INN - you may spend the night here or acquire new contracts");
            System.out.println("FORGE - local smith can sell you his goods or repair your equipment");
            System.out.println("MARKET - a place where you can purchase consumables");
            System.out.println("CHURCH - allocate your points here after gaining a new level");
            System.out.println("FOREST - find and slay monsters from your contracts here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("INN")) {
                    System.out.println("You have entered the establishment.");
                    System.out.println();

                    myInn.visit(this, player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("FORGE")) {
                    System.out.println("You decide to pay a visit to the local smith.");
                    System.out.println();

                    myForge.visit(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("MARKET")) {
                    System.out.println("You have decided to buy some necessities.");
                    System.out.println();

                    myMarket.visit(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("CHURCH")) {
                    System.out.println("You approach the local shrine.");
                    System.out.println();

                    myChurch.visit(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("FOREST")) {
                    System.out.println("You have entered the forest.");
                    System.out.println();

                    myForest.visit(player);
                    break;
                }

                System.out.println("Incorrect command!");
            }
        }
    }
}


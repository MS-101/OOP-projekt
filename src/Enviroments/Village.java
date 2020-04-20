package Enviroments;

import Entities.Player.Mercenary;
import MySystem.*;
import java.io.File;
import java.io.IOException;

public class Village extends Location {
    public Inn myInn;
    public Forge myForge;
    public Market myMarket;
    public Forest myForest;

    public Village() {
        myInn = new Inn();
        myForge = new Forge();
        myMarket = new Market();
        myForest = new Forest();
    }

    public void visit(File accountsFile, AccountsHashTable myHashtable, Mercenary player) throws IOException {
        while (true) {
            System.out.println("Choose a location of your next visit.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("INN - you may spend the night here or acquire new contracts");
            System.out.println("FORGE - local smith can sell you his goods or repair your equipment");
            System.out.println("MARKET - a place where you can purchase consumables");
            System.out.println("FOREST - find and slay monsters from your contracts here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("INN")) {
                    System.out.println("You have entered the establishment.");
                    System.out.println();

                    myInn.visit(accountsFile, myHashtable, player, this);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("FORGE")) {
                    System.out.println("You decide to pay a visit to the local smith.");
                    System.out.println();

                    myForge.visit(accountsFile, myHashtable, player, this);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("MARKET")) {
                    System.out.println("You have decided to buy some necessities.");
                    System.out.println();

                    myMarket.visit(accountsFile, myHashtable, player, this);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("FOREST")) {
                    System.out.println("You have entered the forest.");
                    System.out.println();

                    myForest.visit(accountsFile, myHashtable, player, this);
                    break;
                }

                System.out.println("Incorrect command!");
            }
        }
    }
}


import java.util.Scanner;

public class Village implements Location {
    Tavern myTavern;
    Forge myForge;
    Market myMarket;
    Church myChurch;
    Graveyard myGraveyard;
    Forest myForest;

    public Village() {
        myTavern = new Tavern();
        myForge = new Forge();
        myMarket = new Market();
        myChurch = new Church();
        myGraveyard = new Graveyard();
        myForest = new Forest();
    }

    public void visit(Mercenary player) {
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a location of your next visit.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("TAVERN - you may spend the night here or acquire new contracts");
            System.out.println("FORGE - local smith can sell you his goods or repair your equipment");
            System.out.println("MARKET - a place where you can purchase consumables");
            System.out.println("CHURCH - ???");
            System.out.println("GRAVEYARD - visit the graves of fallen adventurers");
            System.out.println("FOREST - find and slay monsters from your contracts here");
            System.out.println();

            while (true) {
                String command = myScanner.nextLine();

                if (command.equalsIgnoreCase("TAVERN")) {
                    System.out.println("You have entered the establishment.");
                    System.out.println();

                    myTavern.visit(player);
                    break;
                }

                if (command.equalsIgnoreCase("FORGE")) {
                    System.out.println("You have entered the establishment.");
                    System.out.println();

                    myForge.visit(player);
                    break;
                }

                if (command.equalsIgnoreCase("MARKET")) {
                    System.out.println("You have decided to buy some necessities.");
                    System.out.println();

                    myMarket.visit(player);
                    break;
                }

                if (command.equalsIgnoreCase("CHURCH")) {
                    System.out.println("You approach the local shrine.");
                    System.out.println();

                    myChurch.visit(player);
                    break;
                }

                if (command.equalsIgnoreCase("GRAVEYARD")) {
                    System.out.println("You can mourn the dead here.");
                    System.out.println();

                    myGraveyard.visit(player);
                    break;
                }

                if (command.equalsIgnoreCase("FOREST")) {
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


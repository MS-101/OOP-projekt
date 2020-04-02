import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Mercenary player = new Mercenary();

        village(player);
    }

    public static void village(Mercenary player) {
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a location of your next visit.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("TAVERN - you may spend the night here or acquire new contracts");
            System.out.println("BLACKSMITH - local smith can sell you his goods or repair your equipment");
            System.out.println("MARKET - a place where you can purchase consumables");
            System.out.println("FOREST - find and slay monsters from your contracts here");
            System.out.println();

            while (true) {
                String command = myScanner.nextLine();

                if (command.equalsIgnoreCase("FOREST")) {
                    System.out.println("You have entered the forest.");
                    System.out.println();

                    forest(player);
                    break;
                }

                System.out.println("Incorrect command!");
            }
        }
    }

    public static void forest(Mercenary player) {
        Scanner myScanner = new Scanner(System.in);
        ArrayList<Monster> opponents;
        Combat fight;
        int i;

        while (true) {
            System.out.println("Do you want to hunt here?");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("HUNT - find and attempt to slay some monsters");
            System.out.println("RETURN - come back to the village to resupply, rest or pick up your reward.");
            System.out.println();

            while (true) {
                String command = myScanner.nextLine();
                Random randomNumber = new Random();
                int monsterCount;

                if (command.equalsIgnoreCase("HUNT")) {
                    System.out.println("You have decided to hunt some monsters.");
                    System.out.println();

                    monsterCount = randomNumber.nextInt(3) + 1;

                    opponents = new ArrayList<Monster>();
                    for (i = 0; i < monsterCount; i++) {
                        opponents.add(new Ghoul());
                    }

                    fight = new Combat(player, opponents);
                    break;
                }

                if (command.equalsIgnoreCase("RETURN")) {
                    System.out.println("You have returned to the village.");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }


        }
    }
}


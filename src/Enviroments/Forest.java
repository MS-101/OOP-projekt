package Enviroments;

import java.util.ArrayList;
import java.util.Random;
import Entities.Player.Mercenary;
import Entities.Monsters.*;

public class Forest extends Location {
    public void visit(Mercenary player) {
        int i;

        while (true) {
            System.out.println("Do you want to hunt here?");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("HUNT - find and attempt to slay some monsters");
            System.out.println("RETURN - come back to the village to resupply, rest or pick up your reward");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("HUNT")) {
                    Random randomNumber = new Random();
                    ArrayList<Monster> opponents = new ArrayList<Monster>();;
                    int monsterCount = randomNumber.nextInt(3) + 1;

                    System.out.println("You have decided to hunt some monsters.");
                    System.out.println();

                    for (i = 0; i < monsterCount; i++) {
                        opponents.add(new Ghoul());
                    }

                    new Combat(player, opponents);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("RETURN")) {
                    System.out.println("You have returned to the village.");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }


        }
    }
}

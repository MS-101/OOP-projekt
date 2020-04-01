import java.util.Random;
import java.util.Scanner;

public class Combat {

    public static void start(Mercenary player, Entity opponent) {
        System.out.println(player.name + " engaged in combat with " + opponent.name + "!");
        System.out.println();

        int turnCounter = 1;

        while (player.hp > 0 && opponent.hp > 0) {
            System.out.println("TURN " + turnCounter + " START:");
            System.out.println();

            System.out.println(player.name + ": " + player.hp + "/" + player.maxHp + " hp, " + player.mp + "/" + player.maxMp + " mp");
            System.out.println(opponent.name + ": " + opponent.hp + "/" + opponent.maxHp + " hp, " + opponent.mp + "/" + opponent.maxMp + " mp");

            System.out.println();

            if (playerTurn(player, opponent)) {
                return;
            }

            if (opponent.hp > 0) {
                opponentTurn(player, opponent);
            }

            turnCounter++;
        }
    }

    //this function return true when player successfully escaped from combat
    private static boolean playerTurn(Mercenary player, Entity opponent) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Your turn:");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("ATTACK - deal damage to your opponent");
        if (player.hpPotions_amount > 0) {
            System.out.println("HP - use hp potion [heals " + player.hpPotion_heal + " hp] (" + player.hpPotions_amount + " remaining)");
        }
        if (player.mpPotions_amount > 0) {
            System.out.println("MP - use mp potion [heals " + player.mpPotion_heal + " mp] (" + player.mpPotions_amount + " remaining)");
        }
        System.out.println("FLEE - disengage from combat (may be unsuccessful)");
        System.out.println();

        while (true) {
            String command = myScanner.nextLine();

            if (command.equalsIgnoreCase("ATTACK")) {
                player.attack(opponent);
                return false;
            }

            if (command.equalsIgnoreCase("FLEE")) {
                Random randomNumber = new Random();
                int diceRoll = randomNumber.nextInt(100);

                if (diceRoll < 60) {
                    System.out.println("You may live to fight another day.");
                    System.out.println();
                    return true;
                } else {
                    System.out.println("You have failed to lose your pursuers.");
                    System.out.println();
                    return false;
                }

            }

            if (player.hpPotions_amount > 0 && command.equalsIgnoreCase("HP")) {
                if (player.useHpPotion()) {
                    return false;
                }
            }

            if (player.mpPotions_amount > 0 && command.equalsIgnoreCase("MP")) {
                if (player.useMpPotion()) {
                    return false;
                }
            }

            System.out.println("Incorrect command!");
        }
    }

    private static void opponentTurn(Mercenary player, Entity opponent) {
        System.out.println(opponent.name + "'s turn:");
        System.out.println();

        opponent.attack(player);
    }

    public static void end(String result) {

    }
}

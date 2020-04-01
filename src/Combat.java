import java.util.Scanner;

public class Combat {

    public static void start(Mercenary player, Entity opponent) {
        System.out.println("Mercenary found a " + opponent.name + " in the woods!");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("FIGHT - battle to death with your opponents");
        System.out.println("FLEE - try to escape your pursuers (may be unsuccessful)");
        System.out.println();

        Scanner myScanner = new Scanner(System.in);
        String command;

        while (true) {
            command = myScanner.nextLine();

            if (command.equalsIgnoreCase("FIGHT")) {
                System.out.println("You have decided to engage in combat.");
                System.out.println();
                fight(player, opponent);
                return;
            }

            if (command.equalsIgnoreCase("FLEE")) {
                System.out.println("You may live to fight another day.");
                System.out.println();
                return;
            }

            System.out.println("Incorrect command!");
        }
    }

    public static void fight(Mercenary player, Entity opponent) {
        int turnCounter = 1;

        while (player.hp > 0 && opponent.hp > 0) {
            System.out.println("TURN " + turnCounter + " START:");
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
    public static boolean playerTurn(Mercenary player, Entity opponent) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Your turn:");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("ATTACK - deal damage to your opponent");
        if (player.hpPotions_amount > 0) {
            System.out.println("HP - use hp potion [heals " + player.hpPotion_heal + " health] (" + player.hpPotions_amount + " remaining)");
        }
        if (player.mpPotions_amount > 0) {
            System.out.println("MP - use mp potion [heals " + player.mpPotion_heal + " mana] (" + player.mpPotions_amount + " remaining)");
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
                System.out.println("You may live to fight another day.");
                return true;
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

    public static void opponentTurn(Mercenary player, Entity opponent) {
        System.out.println(opponent.name + "'s turn:");
        System.out.println();

        opponent.attack(player);
    }

    public static void end(String result) {

    }
}

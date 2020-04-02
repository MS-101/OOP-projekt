import java.util.Random;
import java.util.Scanner;

public class Combat {

    public static void start(Mercenary player, Monster opponent) {
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

        if (player.hp > 0) {
            victory(player, opponent);
        } else {
            defeat();
        }
    }

    //this function return true when player successfully escaped from combat
    private static boolean playerTurn(Mercenary player, Monster opponent) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Your turn:");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("ATTACK - deal damage to your opponent");
        if (player.consumables.hpPotions_amount > 0) {
            System.out.println("HP - use hp potion [heals " + player.consumables.hpPotion_heal + " hp] (" + player.consumables.hpPotions_amount + " remaining)");
        }
        if (player.consumables.mpPotions_amount > 0) {
            System.out.println("MP - use mp potion [heals " + player.consumables.mpPotion_heal + " mp] (" + player.consumables.mpPotions_amount + " remaining)");
        }
        System.out.println("SKIP - if you are feeling suicidal");
        System.out.println("FLEE - disengage from combat (may be unsuccessful)");
        System.out.println();

        while (true) {
            String command = myScanner.nextLine();

            if (command.equalsIgnoreCase("ATTACK")) {
                player.attack(opponent);
                return false;
            }

            if (player.consumables.hpPotions_amount > 0 && command.equalsIgnoreCase("HP")) {
                if (player.useHpPotion()) {
                    return false;
                } else {
                    continue;
                }
            }

            if (player.consumables.mpPotions_amount > 0 && command.equalsIgnoreCase("MP")) {
                if (player.useMpPotion()) {
                    return false;
                } else {
                    continue;
                }
            }

            if (command.equalsIgnoreCase("SKIP")) {
                System.out.println("You have decided to skip your turn.");
                System.out.println();

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

            System.out.println("Incorrect command!");
        }
    }

    private static void opponentTurn(Mercenary player, Monster opponent) {
        System.out.println(opponent.name + "'s turn:");
        System.out.println();

        opponent.attack(player);
    }

    public static void victory(Mercenary player, Monster opponent) {
        Random randomNumber = new Random();
        int gainedGold;

        gainedGold = randomNumber.nextInt((opponent.highGold - opponent.lowGold) + 1) + opponent.lowGold;

        player.loot.addExp(opponent.exp);
        player.loot.addGold(gainedGold);

        System.out.println("You emerge victorious!");
        System.out.println("You have gained " + opponent.exp + " exp and " + gainedGold + " gold");
        System.out.println("You currently have " + player.loot.exp + " exp and " + player.loot.gold + " gold");
        System.out.println();
    }

    public static void defeat() {
        System.out.println("You have been defeated in combat.");
        System.out.println();
    }
}

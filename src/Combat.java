import java.util.Random;
import java.util.Scanner;

public class Combat {
    Mercenary player;
    Monster opponent;

    public Combat(Mercenary player, Monster opponent) {
        this.player = player;
        this.opponent = opponent;

        start();
    }

    private void start() {
        System.out.println(this.player.name + " engaged in combat with " + this.opponent.name + "!");
        System.out.println();

        int turnCounter = 1;

        while (this.player.hp > 0 && this.opponent.hp > 0) {
            System.out.println("TURN " + turnCounter + " START:");
            System.out.println();

            System.out.println(this.player.name + ": " + this.player.hp + "/" + this.player.maxHp + " hp, " + this.player.mp + "/" + this.player.maxMp + " mp");
            System.out.println(this.opponent.name + ": " + this.opponent.hp + "/" + this.opponent.maxHp + " hp, " + this.opponent.mp + "/" + this.opponent.maxMp + " mp");

            System.out.println();

            if (playerTurn()) {
                return;
            }

            if (this.opponent.hp > 0) {
                opponentTurn();
            }

            turnCounter++;
        }

        if (this.player.hp > 0) {
            victory();
        } else {
            defeat();
        }
    }

    //this function return true when player successfully escaped from combat
    private boolean playerTurn() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Your turn:");
        System.out.println();

        System.out.println("Enter one of the following commands:");
        System.out.println("ATTACK - deal damage to your opponent");
        if (this.player.consumables.hpPotions_amount > 0) {
            System.out.println("HP - use hp potion [heals " + this.player.consumables.hpPotion_heal + " hp] (" + this.player.consumables.hpPotions_amount + " remaining)");
        }
        if (this.player.consumables.mpPotions_amount > 0) {
            System.out.println("MP - use mp potion [heals " + this.player.consumables.mpPotion_heal + " mp] (" + this.player.consumables.mpPotions_amount + " remaining)");
        }
        System.out.println("SKIP - if you are feeling suicidal");
        System.out.println("FLEE - disengage from combat (may be unsuccessful)");
        System.out.println();

        while (true) {
            String command = myScanner.nextLine();

            if (command.equalsIgnoreCase("ATTACK")) {
                this.player.attack(opponent);
                return false;
            }

            if (this.player.consumables.hpPotions_amount > 0 && command.equalsIgnoreCase("HP")) {
                if (this.player.useHpPotion()) {
                    return false;
                } else {
                    continue;
                }
            }

            if (this.player.consumables.mpPotions_amount > 0 && command.equalsIgnoreCase("MP")) {
                if (this.player.useMpPotion()) {
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

    private void opponentTurn() {
        System.out.println(this.opponent.name + "'s turn:");
        System.out.println();

        this.opponent.attack(this.player);
    }

    private void victory() {
        Random randomNumber = new Random();
        int gainedGold;

        gainedGold = randomNumber.nextInt((this.opponent.highGold - this.opponent.lowGold) + 1) + this.opponent.lowGold;

        this.player.loot.addExp(this.opponent.exp);
        this.player.loot.addGold(gainedGold);

        System.out.println("You emerge victorious!");
        System.out.println("You have gained " + this.opponent.exp + " exp and " + gainedGold + " gold");
        System.out.println("You currently have " + this.player.loot.exp + " exp and " + this.player.loot.gold + " gold");
        System.out.println();
    }

    private void defeat() {
        System.out.println("You have been defeated in combat.");
        System.out.println();
    }
}

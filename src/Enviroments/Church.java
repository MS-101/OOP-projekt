package Enviroments;

import Entities.Player.Mercenary;

import java.io.File;
import MySystem.*;

public class Church extends Location {
    public void visit(File accountsFile, AccountsHashTable myHashtable, Mercenary player, Village myVillage) {
        while (true) {
            System.out.println("\"What clouds you this time?\" the priest beckons you to confess.");
            System.out.println();

            System.out.println("Enter one of the following commands:");
            System.out.println("ATTRIBUTES - view and improve your stats here");
            System.out.println("SKILLS - view and improve your skills here");
            System.out.println("RETURN - your business is concluded here");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("ATTRIBUTES")) {
                    System.out.println("Reviewing your stats.");
                    System.out.println();

                    this.attributes(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("SKILLS")) {
                    System.out.println("Reviewing your abilities.");
                    System.out.println();

                    this.skills(player);
                    break;
                }

                if (myCommand.name.equalsIgnoreCase("RETURN")) {
                    System.out.println("\"May god guide your path,\" the clergy gives you his blessings");
                    System.out.println();
                    return;
                }

                System.out.println("Incorrect command!");
            }


        }
    }

    private void attributes(Mercenary player) {
        while (true) {
            System.out.println("Your stats: " + player.maxHp + " max hp, " + player.maxMp + " max mp");
            System.out.println("Your attributes: " + player.stats.strength + " strength, " + player.stats.dexterity + " dexterity, " + player.stats.intelligence + " intelligence");
            System.out.println("You have " + player.attributePoints + " attribute points.");
            System.out.println();

            System.out.println("Enter one of the following commands:");

            System.out.println("HP - increases max hp");
            System.out.println("MP - increases max mp");
            System.out.println("STRENGTH - improves physical damage");
            System.out.println("DEXTERITY - improves piercing damage");
            System.out.println("INTELLIGENCE - improves magical damage");
            System.out.println("FINISH - perhaps there is something else that you need");
            System.out.println();

            while (true) {
                myCommand.readInput();

                if (myCommand.name.equalsIgnoreCase("HP")) {
                    if (player.attributePoints > 0) {
                        System.out.println("You increased your health pool.");
                        System.out.println();

                        player.attributePoints--;
                        player.maxHp += 10;
                        player.hp += 10;

                        break;
                    } else {
                        System.out.println("Level up to get more attribute points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("MP")) {
                    if (player.attributePoints > 0) {
                        System.out.println("You increased your mana pool.");
                        System.out.println();

                        player.attributePoints--;
                        player.maxMp += 10;
                        player.mp += 10;

                        break;
                    } else {
                        System.out.println("Level up to get more attribute points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("STRENGTH")) {
                    if (player.attributePoints > 0) {
                        System.out.println("You increased your strength attribute.");
                        System.out.println();

                        player.attributePoints--;
                        player.stats.strength++;

                        break;
                    } else {
                        System.out.println("Level up to get more attribute points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("DEXTERITY")) {
                    if (player.attributePoints > 0) {
                        System.out.println("You increased your dexterity attribute.");
                        System.out.println();

                        player.attributePoints--;
                        player.stats.dexterity++;

                        break;
                    } else {
                        System.out.println("Level up to get more attribute points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("INTELLIGENCE")) {
                    if (player.attributePoints > 0) {
                        System.out.println("You increased your intelligence attribute.");
                        System.out.println();

                        player.attributePoints--;
                        player.stats.intelligence++;

                        break;
                    } else {
                        System.out.println("Level up to get more attribute points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("FINISH")) {
                    System.out.println("You finished reviewing your attributes.");
                    System.out.println();
                    return;
                }

                System.out.println("Invalid command!");
            }
        }
    }

    private void skills(Mercenary player) {
        while (true) {
            System.out.println("You have " + player.skillPoints + " skill points.");
            System.out.println();

            System.out.println("Enter one of the following commands:");

            System.out.println("FIREBALL [lvl " + player.skills.fireball.curLvl + "/" + player.skills.fireball.maxLvl + "] - deals dmg to a single target");
            System.out.println("FLAMESTRIKE [lvl " + player.skills.flamestrike.curLvl + "/" + player.skills.flamestrike.maxLvl + "] - deal dmg to all targets");
            System.out.println("HEAL [lvl " + player.skills.heal.curLvl + "/" + player.skills.heal.maxLvl + "] - heals hp");
            System.out.println("FINISH - perhaps there is something else that you need");
            System.out.println();

            while (true) {
                myCommand.readInput();

                //I should probably improve the following code at some point...

                if (myCommand.name.equalsIgnoreCase("FIREBALL")) {
                    if (player.skillPoints > 0) {
                        if (player.skills.fireball.curLvl < player.skills.fireball.maxLvl) {
                            player.skills.fireball.upgrade();
                            player.skillPoints--;

                            System.out.print("You improved skill Fireball to lvl " + player.skills.fireball.curLvl + "/" + player.skills.fireball.maxLvl + ".");
                            break;
                        } else {
                            System.out.println("This spell is at max lvl.");
                            continue;
                        }
                    } else {
                        System.out.println("Level up to get more skill points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("FLAMESTRIKE")) {
                    if (player.skillPoints > 0) {
                        if (player.skills.flamestrike.curLvl < player.skills.flamestrike.maxLvl) {
                            player.skills.flamestrike.upgrade();
                            player.skillPoints--;

                            System.out.print("You improved skill Flamestrike to lvl " + player.skills.flamestrike.curLvl + "/" + player.skills.flamestrike.maxLvl + ".");
                            break;
                        } else {
                            System.out.println("This spell is at max lvl.");
                            continue;
                        }
                    } else {
                        System.out.println("Level up to get more skill points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("HEAL")) {
                    if (player.skillPoints > 0) {
                        if (player.skills.heal.curLvl < player.skills.heal.maxLvl) {
                            player.skills.heal.upgrade();
                            player.skillPoints--;

                            System.out.print("You improved skill Heal to lvl " + player.skills.heal.curLvl + "/" + player.skills.heal.maxLvl + ".");
                            break;
                        } else {
                            System.out.println("This spell is at max lvl.");
                            continue;
                        }
                    } else {
                        System.out.println("Level up to get more skill points.");
                        continue;
                    }
                }

                if (myCommand.name.equalsIgnoreCase("FINISH")) {
                    System.out.println("You finished reviewing your skills.");
                    System.out.println();
                    return;
                }

                System.out.println("Invalid command!");

            }
        }
    }
}

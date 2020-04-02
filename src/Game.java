import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Mercenary player = new Mercenary();
        player.weapon = new Steel_Spear();
        player.armor = new Steel_Armor();

        ArrayList<Monster> opponents;
        Combat fight;
        int i;

        for (i = 0; i < 1; i++) {
            opponents = new ArrayList<Monster>();
            opponents.add(new Ghoul());
            opponents.add(new Ghoul());
            opponents.add(new Ghoul());

            fight = new Combat(player, opponents);
        }


    }
}


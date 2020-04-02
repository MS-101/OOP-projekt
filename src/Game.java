public class Game {
    public static void main(String[] args) {
        Mercenary player = new Mercenary();
        player.weapon = new Steel_Spear();
        player.armor = new Steel_Armor();

        Combat fight;
        int i;

        for (i = 0; i < 3; i++) {
            fight = new Combat(player, new Ghoul());
        }
    }
}


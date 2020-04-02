public class Game {
    public static void main(String[] args) {
        Mercenary player = new Mercenary();
        Ghoul dummy = new Ghoul();

        player.weapon = new Steel_Spear();
        player.armor = new Steel_Armor();

        Combat.start(player, dummy);
    }
}


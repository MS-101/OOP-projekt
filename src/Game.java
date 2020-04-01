public class Game {
    public static void main(String[] args) {
        Mercenary player = new Mercenary();
        Ghoul dummy = new Ghoul();

        Combat.start(player, dummy);
    }
}

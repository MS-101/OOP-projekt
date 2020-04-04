import Entities.Player.Mercenary;
import Enviroments.Village;

public class Game {
    public static void main(String[] args) {
        Mercenary player = new Mercenary();
        Village myVillage = new Village();

        myVillage.visit(player);
    }
}

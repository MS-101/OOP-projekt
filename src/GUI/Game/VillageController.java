package GUI.Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class VillageController extends GameController {
    public void goToInn() {

    }

    public void goToForge() throws IOException {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Forge.fxml"));
        Parent forgeRoot = (Parent)loader.load();

        ForgeController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updateForge_all();
        myController.updatePlayer_all();

        myScene.setRoot(forgeRoot);
    }

    public void goToMarket() throws IOException {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Market.fxml"));
        Parent marketRoot = (Parent)loader.load();

        MarketController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updateMarket_all();
        myController.updatePlayer_all();

        myScene.setRoot(marketRoot);
    }

    public void goToForest() {

    }
}

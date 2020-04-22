package GUI.Game.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class VillageController extends GameController {
    public void goToInn() throws IOException {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Inn.fxml"));
        Parent innRoot = (Parent)loader.load();

        InnController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updateInn_all();
        myController.updatePlayer_all();

        myScene.setRoot(innRoot);
    }

    public void goToForge() throws IOException {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Forge.fxml"));
        Parent forgeRoot = (Parent)loader.load();

        ForgeController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updateForge_all();
        myController.updatePlayer_all();

        myScene.setRoot(forgeRoot);
    }

    public void goToMarket() throws IOException {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Market.fxml"));
        Parent marketRoot = (Parent)loader.load();

        MarketController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updateMarket_all();
        myController.updatePlayer_all();

        myScene.setRoot(marketRoot);
    }

    public void goToForest() throws IOException {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Forest.fxml"));
        Parent forestRoot = (Parent)loader.load();

        ForestController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updatePlayer_all();
        myController.setForestButtons();

        myScene.setRoot(forestRoot);
    }
}

package GUI.Game.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class VillageController extends GameController {
    public void goToInn() {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Inn.fxml"));

        try {
            Parent innRoot = (Parent)loader.load();
            InnController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updateInn_all();
            myController.updatePlayer_all();

            myScene.setRoot(innRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToForge() {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Forge.fxml"));

        try {
            Parent forgeRoot = (Parent)loader.load();
            ForgeController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updateForge_all();
            myController.updatePlayer_all();

            myScene.setRoot(forgeRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToMarket() {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Market.fxml"));

        try {
            Parent marketRoot = (Parent)loader.load();
            MarketController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updateMarket_all();
            myController.updatePlayer_all();

            myScene.setRoot(marketRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToForest() {
        Scene myScene = (Scene)ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Forest.fxml"));

        try {
            Parent forestRoot = (Parent)loader.load();
            ForestController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updatePlayer_all();
            myController.setForestButtons();

            myScene.setRoot(forestRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

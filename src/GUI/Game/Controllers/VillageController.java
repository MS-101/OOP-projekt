package GUI.Game.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * This controller controls village GUI elements and is an extension of GameController class.
 * This allow the player to visit village establishment and forest from here.
 */

public class VillageController extends GameController {

    /**
     * This will send the player to inn.
     * Creates a new controller and passes all the required data there.
     * Changes the root of current scene to the root of inn.
     */

    public void goToInn() {
        Scene myScene = ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Inn.fxml"));

        try {
            Parent innRoot = loader.load();
            InnController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updateInn_all();
            myController.updatePlayer_all();

            myScene.setRoot(innRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This will send the player to forge.
     * Creates a new controller and passes all the required data there.
     * Changes the root of current scene to the root of forge.
     */

    public void goToForge() {
        Scene myScene = ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Forge.fxml"));

        try {
            Parent forgeRoot = loader.load();
            ForgeController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updateForge_all();
            myController.updatePlayer_all();

            myScene.setRoot(forgeRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This will send the player to market.
     * Creates a new controller and passes all the required data there.
     * Changes the root of current scene to the root of market.
     */

    public void goToMarket() {
        Scene myScene = ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Market.fxml"));

        try {
            Parent marketRoot = loader.load();
            MarketController myController = loader.getController();

            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updateMarket_all();
            myController.updatePlayer_all();

            myScene.setRoot(marketRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This will send the player to forest.
     * Creates a new controller and passes all the required data there.
     * Changes the root of current scene to the root of forest.
     */

    public void goToForest() {
        Scene myScene = ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Forest.fxml"));

        try {
            Parent forestRoot = loader.load();
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

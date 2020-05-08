package GUI.Game.Controllers;

import Environments.Inn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.io.IOException;

public class InnController extends GameController {
    @FXML
    Button restBtn;

    public void rest() {
        Inn myInn = myVillage.myInn;

        myInn.rest(myMercenary, myVillage.myForge);

        updatePlayer_hp();
        updatePlayer_mp();
        updatePlayer_gold();
        updateInn_rest();

        saveGame();
    }

    public void updateInn_all() {
        updateInn_rest();
    }

    public void updateInn_rest() {
        Inn myInn = myVillage.myInn;

        restBtn.setText("Rest [" + myInn.roomCost + " gold]");

        if (myMercenary.loot.gold > myInn.roomCost) {
            restBtn.setDisable(false);
            restBtn.setTextFill(Color.web("black"));
        } else {
            restBtn.setDisable(true);
            restBtn.setTextFill(Color.web("red"));
        }
    }

    /**
     * This will send the player to village.
     * Creates new controller and passes all required data there.
     * Changes the root of current scene to the root of village.
     */

    public void returnToVillage() {
        Scene myScene = ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Village.fxml"));

        try {
            Parent villageRoot = loader.load();

            VillageController myController = loader.getController();
            myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
            myController.updatePlayer_all();

            myScene.setRoot(villageRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

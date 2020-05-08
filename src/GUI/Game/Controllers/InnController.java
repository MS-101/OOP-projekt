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

    public void rest() throws IOException {
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

    public void returnToVillage() throws IOException {
        Scene myScene = (Scene) ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Village.fxml"));
        Parent villageRoot = (Parent) loader.load();

        VillageController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updatePlayer_all();

        myScene.setRoot(villageRoot);
    }
}

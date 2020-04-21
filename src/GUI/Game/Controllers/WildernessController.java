package GUI.Game.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class WildernessController extends GameController {
    public void goToForest() {

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

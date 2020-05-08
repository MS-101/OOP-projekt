import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Mercenary Hunter is 2D rpg game application.
 * In this game player has the control of mercenary,
 * that hunts monsters for living.
 * Game takes place in a village, where the player has access
 * to inn, forge, market and forest.
 * Monsters can be hunted in forest.
 * Gained gold can be used in village establishments to rest,
 * buy and repair items and purchase consumables.
 *
 * @author Martin Šváb
 * @version 1.0
 * @since 2020-05-08
 */

public class Game extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("GUI/UserLogin/UserLogin.fxml"));
        primaryStage.setTitle("Mercenary Hunter");
        primaryStage.setScene(new Scene(loginRoot, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

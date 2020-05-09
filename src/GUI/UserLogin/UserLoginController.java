package GUI.UserLogin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import GUI.Game.Controllers.VillageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import MySystem.*;
import Environments.Village;
import Entities.Player.Mercenary;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This controller controls login GUI elements.
 * This allows the player to send registration or login request to mySystem.
 * Upon successful registration/login game is started with user's data.
 */

public class UserLoginController implements Initializable {
    File accountsFile = new File("accounts.txt");
    AccountsFileHandler myAccountHandler = new AccountsFileHandler();
    AccountsHashTable myHashTable;

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextArea errorMessage;

    /**
     * This method handles event of pressing login button.
     * It reads user's username and password from GUI elements and sends login request.
     * If login is successful the game is started using the account's data.
     * If login is unsuccessful user gets error message.
     * Login can fail if user doesn't input or types incorrect account information.
     */

    public void pressLoginButton () {
        String username = tf_username.getText();
        String unprotectedPassword = pf_password.getText();

        if (username.isEmpty() || unprotectedPassword.isEmpty()) {
            errorMessage.setText("You must enter username and password!");
            errorMessage.setVisible(true);
            return;
        }

        Account myAccount = myHashTable.login(username, unprotectedPassword);

        if (myAccount != null) {
            Village myVillage = myAccount.getAccountVillage();
            Mercenary myMercenary = myAccount.getAccountMercenary();

            startGame(accountsFile, myHashTable, myMercenary, myVillage);
        } else {
            errorMessage.setText("Incorrect password or username!");
            errorMessage.setVisible(true);
        }
    }

    /**
     * This method handles event of pressing register button.
     * It reads user's username and password from GUI elements and sends register request.
     * If registration is successful the game is started using the new account's data.
     * If registration is unsuccessful user gets error message.
     * Registration can fail if user doesn't input account information,
     * or uses weak password or entered username is already in use.
     */

    public void pressRegisterButton () {
        String username = tf_username.getText();
        String unprotectedPassword = pf_password.getText();

        if (username.isEmpty() || unprotectedPassword.isEmpty()) {
            errorMessage.setText("You must enter username and password!");
            errorMessage.setVisible(true);
            return;
        }

        if (!myHashTable.isPasswordStrong(unprotectedPassword)) {
            errorMessage.setText("Password must contain at least 8 characters, lowercase letter, uppercase letter, number and special symbol.");
            errorMessage.setVisible(true);
            return;
        }


        Account registeredAccount = myHashTable.register(username, unprotectedPassword);

        if (registeredAccount != null) {
            myAccountHandler.rewriteAccountsFile(accountsFile, myHashTable);

            Village myVillage = registeredAccount.getAccountVillage();
            Mercenary myMercenary = registeredAccount.getAccountMercenary();

            startGame(accountsFile, myHashTable, myMercenary, myVillage);
        } else {
            errorMessage.setText("Username is already in use!");
            errorMessage.setVisible(true);
        }
    }

    /**
     * Starts the game with player data.
     * Game is always started in village.
     *
     * @param accountsFile File where accounts hashtable is stored.
     * @param myHashTable Hashtable where accounts are stored.
     * @param myMercenary Player's mercenary.
     * @param myVillage Player's village.
     */

    private void startGame(File accountsFile, AccountsHashTable myHashTable, Mercenary myMercenary, Village myVillage) {
        Stage primaryStage = (Stage)ap.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Game/FXML/Village.fxml"));

        try {
            Parent villageRoot = loader.load();
            VillageController controller = loader.getController();

            controller.passUserData(accountsFile, myHashTable, myVillage, myMercenary);
            primaryStage.setScene(new Scene(villageRoot, 1200, 1000));

            controller.updatePlayer_all();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if account file exists. If it doesn't exist, it creates a new account file.
     * Then it reads the hashtable from accounts file.
     * The game doesn't handle updates well. If game crashes at startup it is likely that
     * you are using outdated accounts file and you will need to delete it manually to start the game. (accounts.txt)
     *
     * @param url url
     * @param resourceBundle resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        if (!accountsFile.exists()) {
            myAccountHandler.createAccountsFile(accountsFile);
        }

        myHashTable = myAccountHandler.readAccountsFile(accountsFile);
    }
}

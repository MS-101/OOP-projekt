package GUI.UserLogin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.event.ActionEvent;

import MySystem.*;
import Environments.Village;
import Entities.Player.Mercenary;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public void pressLoginButton (ActionEvent event) throws NoSuchAlgorithmException, IOException {
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

    public void pressRegisterButton (ActionEvent event) throws NoSuchAlgorithmException, IOException {
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

    private void startGame(File accountsFile, AccountsHashTable myHashTable, Mercenary myMercenary, Village myVillage) throws IOException {
        Stage primaryStage = (Stage) ap.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Game/FXML/Village.fxml"));
        Parent villageRoot = (Parent) loader.load();
        VillageController controller = loader.getController();

        controller.passUserData(accountsFile, myHashTable, myVillage, myMercenary);
        primaryStage.setScene(new Scene(villageRoot, 1200, 1000));

        controller.updatePlayer_all();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        //if (!accountsFile.exists()) {
            myAccountHandler.createAccountsFile(accountsFile);
        //}

        myHashTable = myAccountHandler.readAccountsFile(accountsFile);
    }
}

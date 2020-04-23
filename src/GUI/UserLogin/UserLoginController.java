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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import MySystem.*;
import Enviroments.Village;
import Entities.Player.Mercenary;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserLoginController implements Initializable {
    File accountsFile = new File("accounts.txt");
    AccountsFileHandler myAccountManager = new AccountsFileHandler();
    AccountsHashTable myHashTable;

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Label label_error;

    public void pressLoginButton (ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String username = tf_username.getText();
        String unprotectedPassword = pf_password.getText();

        Account myAccount = myHashTable.login(username, unprotectedPassword);

        if (myAccount != null) {
            Village myVillage = myAccount.getAccountVillage();
            Mercenary myMercenary = myAccount.getAccountMercenary();

            startGame(accountsFile, myHashTable, myMercenary, myVillage);
            //myVillage.visit(accountsFile, myHashTable, myMercenary);
        } else {
            label_error.setText("Incorrect password or username!");
            label_error.setVisible(true);
        }
    }

    public void pressRegisterButton (ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String username = tf_username.getText();
        String unprotectedPassword = pf_password.getText();

        Account registeredAccount = myHashTable.register(username, unprotectedPassword);
        if (registeredAccount != null) {
            myAccountManager.rewriteAccountsFile(accountsFile, myHashTable);

            Village myVillage = registeredAccount.getAccountVillage();
            Mercenary myMercenary = registeredAccount.getAccountMercenary();

            startGame(accountsFile, myHashTable, myMercenary, myVillage);
            //myVillage.visit(accountsFile, myHashTable, myMercenary);
        } else {
            label_error.setText("Username is already in use!");
            label_error.setVisible(true);
        }
    }

    private void startGame(File accountsFile, AccountsHashTable myHashTable, Mercenary myMercenary, Village myVillage) throws IOException {
        Stage primaryStage = (Stage) ap.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Game/FXML/Village.fxml"));
        Parent villageRoot = (Parent) loader.load();
        VillageController controller = loader.getController();

        controller.passUserData(accountsFile, myHashTable, myVillage, myMercenary);
        primaryStage.setScene(new Scene(villageRoot, 1200, 1000));

        //testing
        myMercenary.hp = 50;
        myMercenary.mp = 50;
        myMercenary.loot.gold = 200;
        myMercenary.consumables.hpPotions_amount = 5;
        myMercenary.consumables.mpPotions_amount = 5;
        myMercenary.weapon.durability = 1;
        myMercenary.stats.attributePoints = 10;
        myMercenary.skills.skillPoints = 10;

        controller.updatePlayer_all();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        //if (!accountsFile.exists()) {
            try {
                myAccountManager.createAccountsFile(accountsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}

        try {
            myHashTable = myAccountManager.readAccountsFile(accountsFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

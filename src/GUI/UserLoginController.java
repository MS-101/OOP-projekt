package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import MySystem.*;
import Enviroments.Village;
import Entities.Player.Mercenary;
import javafx.stage.*;

import javax.xml.transform.Source;

public class UserLoginController implements Initializable {
    File accountsFile = new File("accounts.txt");
    AccountHandler myAccountManager = new AccountHandler();
    AccountsHashTable myHashTable;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Label label_error;

    public void pressLoginButton (ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String username = tf_username.getText();
        String unprotectedPassword = pf_password.getText();
        String securePassword = securePassword(unprotectedPassword);

        Account myAccount = myHashTable.login(username, securePassword);

        if (myAccount != null) {
            Village myVillage = myAccount.getAccountVillage();
            Mercenary myMercenary = myAccount.getAccountMercenary();

            ((Node)(event.getSource())).getScene().getWindow().hide();
            myVillage.visit(accountsFile, myHashTable, myMercenary);
        } else {
            label_error.setText("Incorrect password or username!");
            label_error.setVisible(true);
        }
    }

    public void pressRegisterButton (ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String username = tf_username.getText();
        String unprotectedPassword = pf_password.getText();
        String securePassword = securePassword(unprotectedPassword);

        Account registeredAccount = myHashTable.register(username, securePassword);
        if (registeredAccount != null) {
            myAccountManager.rewriteAccountsFile(accountsFile, myHashTable);

            Village myVillage = registeredAccount.getAccountVillage();
            Mercenary myMercenary = registeredAccount.getAccountMercenary();

            ((Node)(event.getSource())).getScene().getWindow().hide();
            myVillage.visit(accountsFile, myHashTable, myMercenary);
        } else {
            label_error.setText("Username is already in use!");
            label_error.setVisible(true);
        }
    }

    private static String securePassword(String unprotectedPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(unprotectedPassword.getBytes(StandardCharsets.UTF_8));

        String protectedPassword = bytesToHex(encodedHash);

        return protectedPassword;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        if (!accountsFile.exists()) {
            try {
                myAccountManager.createAccountsFile(accountsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            myHashTable = myAccountManager.readAccountsFile(accountsFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package GUI.Game;

import Enviroments.Forge;
import Items.Armor.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.IOException;

public class ForgeController extends GameController {
    @FXML
    Button repairWeaponBtn;
    @FXML
    Button repairArmorBtn;

    @FXML
    Label repairWeaponName;
    @FXML
    Label repairWeaponCost;
    @FXML
    Label repairWeaponDurLabel;
    @FXML
    ProgressBar repairWeaponDurBar;

    @FXML
    Label repairArmorName;
    @FXML
    Label repairArmorCost;
    @FXML
    Label repairArmorDurLabel;
    @FXML
    ProgressBar repairArmorDurBar;

    public void buyItem(ActionEvent event) {
        Forge myForge = myVillage.myForge;

        Button myButton = (Button)event.getTarget();
        String buttonId = myButton.getId();
        int itemId = buttonId.charAt(4);

        Item pickedItem = myForge.forgeInventory.get(itemId);

        myMercenary.loot.payGold(pickedItem.cost);
        updatePlayer_gold();

        if (pickedItem instanceof Weapon) {
            myMercenary.setWeapon((Weapon)pickedItem);
            updatePlayer_weapon();
        } else {
            myMercenary.setArmor((Armor)pickedItem);
            updatePlayer_armor();
        }

        myForge.forgeInventory.remove(itemId);
        updateForge_all();
    }

    public void repairWeapon() {
        Weapon myWeapon = myMercenary.weapon;

        int payment = (int)(((double)(myWeapon.maxDurability - myWeapon.durability) / (double)myWeapon.maxDurability) * myWeapon.repairCost);

        myMercenary.loot.payGold(payment);
        myWeapon.repairItem();

        updatePlayer_gold();
        updatePlayer_weapon();
        updateForge_repairAll();
    }

    public void repairArmor() {
        Armor myArmor = myMercenary.armor;

        int payment = (int)(((double)(myArmor.maxDurability - myArmor.durability) / (double)myArmor.maxDurability) * myArmor.repairCost);

        myMercenary.loot.payGold(payment);
        myArmor.repairItem();

        updatePlayer_gold();
        updatePlayer_armor();
        updateForge_repairAll();
    }

    public void returnToVillage() throws IOException {
        Scene myScene = (Scene) ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Village.fxml"));
        Parent villageRoot = (Parent) loader.load();

        VillageController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updatePlayer_all();

        myScene.setRoot(villageRoot);
    }

    public  void updateForge_all() {
        updateForge_trade();
        updateForge_repairAll();
    }

    public void updateForge_trade() {
        int i;
        Forge myForge = myVillage.myForge;

        for (i = 0; i < myForge.forgeInventory.size(); i++) {

        }
    }

    public void updateForge_repairAll() {
        updateForge_repairWeapon();
        updateForge_repairArmor();
    }

    public void updateForge_repairWeapon() {
        Weapon myWeapon = myMercenary.weapon;

        if (myWeapon != null) {
            int repairCost = (int) (((double) (myWeapon.maxDurability - myWeapon.durability) / (double) myWeapon.maxDurability) * myWeapon.repairCost);
            double durProgress = (double) myWeapon.durability / (double) myWeapon.maxDurability;

            repairWeaponName.setText(myWeapon.name);
            repairWeaponCost.setText("cost: " + String.valueOf(repairCost));
            repairWeaponDurLabel.setText(myWeapon.durability + "/" + myWeapon.maxDurability);
            repairWeaponDurBar.setProgress(durProgress);

            if (myWeapon.durability < myWeapon.maxDurability && myMercenary.loot.gold >= repairCost) {
                repairWeaponBtn.setDisable(false);
            } else {
                repairWeaponBtn.setDisable(true);
            }
        } else {
            repairWeaponName.setText("N/A");
            repairWeaponCost.setText("N/A");
            repairWeaponDurLabel.setText("N/A");
            repairWeaponDurBar.setProgress(0);
            repairWeaponBtn.setDisable(true);
        }
    }

    public void updateForge_repairArmor() {
        Armor myArmor = myMercenary.armor;

        if (myArmor != null) {
            int repairCost = (int)(((double)(myArmor.maxDurability - myArmor.durability) / (double)myArmor.maxDurability) * myArmor.repairCost);
            double durProgress = (double)myArmor.durability / (double)myArmor.maxDurability;

            repairArmorName.setText(myArmor.name);
            repairArmorCost.setText("cost: " + String.valueOf(repairCost));
            repairArmorDurLabel.setText(myArmor.durability + "/" + myArmor.maxDurability);
            repairArmorDurBar.setProgress(durProgress);

            if (myArmor.durability < myArmor.maxDurability && myMercenary.loot.gold >= repairCost) {
                repairArmorBtn.setDisable(false);
            } else {
                repairArmorBtn.setDisable(true);
            }
        } else {
            repairArmorName.setText("N/A");
            repairArmorCost.setText("cost: N/A");
            repairArmorDurLabel.setText("N/A");
            repairArmorDurBar.setProgress(0);
            repairArmorBtn.setDisable(true);
        }
    }
}

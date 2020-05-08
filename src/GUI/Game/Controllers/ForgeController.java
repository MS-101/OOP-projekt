package GUI.Game.Controllers;

import Environments.Forge;
import Items.Armor.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * This controller controls forge GUI elements and is an extension of GameController class.
 * This allow the player to interact with forge.
 */

public class ForgeController extends GameController {
    @FXML
    VBox tradeVBox;

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

    /**
     * This method handles event of pressing buy item button.
     * Calls the buyItem method from Forge, updates the necessary GUI elements and saves the game.
     *
     * @param itemID Index of bought item.
     */

    public void buyItem(int itemID) {
        Forge myForge = myVillage.myForge;

        myForge.buyItem(myMercenary, itemID);

        updatePlayer_gold();
        updatePlayer_weapon();
        updatePlayer_armor();

        updateForge_all();

        saveGame();
    }

    /**
     * This method handles event of pressing repairWeapon button.
     * Calls the repairItem method from Forge, updates the necessary GUI elements and saves the game.
     */

    public void repairWeapon() {
        Forge myForge = myVillage.myForge;
        Weapon myWeapon = myMercenary.weapon;

        myForge.repairItem(myMercenary, myWeapon);

        updatePlayer_gold();
        updatePlayer_weapon();
        updateForge_trade();
        updateForge_repairAll();

        saveGame();
    }

    /**
     * This method handles event of pressing repairArmor button.
     * Calls the repairItem method from Forge and updates the necessary GUI elements.
     */

    public void repairArmor() {
        Forge myForge = myVillage.myForge;
        Armor myArmor = myMercenary.armor;

        myForge.repairItem(myMercenary, myArmor);

        updatePlayer_gold();
        updatePlayer_armor();
        updateForge_trade();
        updateForge_repairAll();

        saveGame();
    }

    /**
     * This will send the player to village.
     * Creates a new controller and passes all the required data there.
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

    /**
     * Updates all forge GUI elements.
     */

    public  void updateForge_all() {
        updateForge_trade();
        updateForge_repairAll();
    }

    /**
     * Updates trade tab in forge GUI.
     * All available items are displayed in the trade tab.
     * Each item consists of splitPane which contains the item's name, description, button and cost.
     * If the player cannot afford an item, it's purchase button is disabled
     * and it's cost label's font color is set to red.
     */

    public void updateForge_trade() {
        int itemIndex;
        Forge myForge = myVillage.myForge;

        tradeVBox.getChildren().clear();

        for (itemIndex = 0; itemIndex < myForge.forgeInventory.size(); itemIndex++) {
            Item pickedItem = myForge.forgeInventory.get(itemIndex);

            SplitPane forgeItem = new SplitPane();

            AnchorPane itemNameAnchor = new AnchorPane();
            Label itemName = new Label();
            itemName.setText(pickedItem.name);
            itemName.setAlignment(Pos.CENTER);
            itemNameAnchor.getChildren().add(itemName);

            AnchorPane.setTopAnchor(itemName, 0.0);
            AnchorPane.setLeftAnchor(itemName, 0.0);
            AnchorPane.setRightAnchor(itemName, 0.0);
            AnchorPane.setBottomAnchor(itemName, 0.0);

            AnchorPane itemDescrAnchor = new AnchorPane();
            ListView<String> itemDescr = new ListView<String>();
            if (pickedItem instanceof Weapon) {
                Weapon pickedWeapon = (Weapon) pickedItem;

                if (pickedWeapon.lowPhysDmg != 0 && pickedWeapon.highPhysDmg != 0) {
                    itemDescr.getItems().add("physical damage: " + pickedWeapon.lowPhysDmg + " - " + pickedWeapon.highPhysDmg);
                }

                if (pickedWeapon.lowPiercDmg != 0 && pickedWeapon.highPiercDmg != 0) {
                    itemDescr.getItems().add("piercing damage: " + pickedWeapon.lowPiercDmg + " - " + pickedWeapon.highPiercDmg);
                }
            }
            if (pickedItem instanceof Armor) {
                Armor pickedArmor = (Armor) pickedItem;

                itemDescr.getItems().add("armor: " + pickedArmor.armorVal);
            }
            itemDescr.setMouseTransparent(true);
            itemDescr.setFocusTraversable(false);
            itemDescrAnchor.getChildren().add(itemDescr);

            AnchorPane.setTopAnchor(itemDescr, 0.0);
            AnchorPane.setLeftAnchor(itemDescr, 0.0);
            AnchorPane.setRightAnchor(itemDescr, 0.0);
            AnchorPane.setBottomAnchor(itemDescr, 0.0);

            AnchorPane itemCostAnchor = new AnchorPane();
            Label itemCost = new Label();
            itemCost.setText("Cost: " + pickedItem.cost + " gold");
            itemCost.setAlignment(Pos.CENTER);
            itemCostAnchor.getChildren().add(itemCost);

            AnchorPane.setTopAnchor(itemCost, 0.0);
            AnchorPane.setLeftAnchor(itemCost, 0.0);
            AnchorPane.setRightAnchor(itemCost, 0.0);
            AnchorPane.setBottomAnchor(itemCost, 0.0);

            AnchorPane itemBtnAnchor = new AnchorPane();
            Button itemBtn = new Button();
            itemBtn.setText("Buy");

            if (myMercenary.loot.gold >= pickedItem.cost) {
                int finalItemIndex = itemIndex;

                itemBtn.setOnAction(new EventHandler() {

                    @Override
                    public void handle(Event event) {
                        buyItem(finalItemIndex);
                    }
                });

                itemCost.setTextFill(Color.web("black"));
            } else {
                itemBtn.setDisable(true);
                itemCost.setTextFill(Color.web("red"));
            }

            itemBtnAnchor.getChildren().add(itemBtn);

            AnchorPane.setTopAnchor(itemBtn, 0.0);
            AnchorPane.setLeftAnchor(itemBtn, 0.0);
            AnchorPane.setRightAnchor(itemBtn, 0.0);
            AnchorPane.setBottomAnchor(itemBtn, 0.0);

            forgeItem.getItems().addAll(itemNameAnchor, itemDescrAnchor, itemBtnAnchor, itemCostAnchor);

            itemName.maxWidthProperty().bind(forgeItem.widthProperty().multiply(0.2));
            itemDescr.maxWidthProperty().bind(forgeItem.widthProperty().multiply(0.3));
            itemBtn.maxWidthProperty().bind(forgeItem.widthProperty().multiply(0.25));
            itemCost.maxWidthProperty().bind(forgeItem.widthProperty().multiply(0.25));

            forgeItem.setPrefHeight(80);
            forgeItem.setDividerPositions(0.2, 0.5, 0.75);

            tradeVBox.getChildren().add(forgeItem);
        }
    }

    /**
     * Updates repair tab in forge GUI.
     */

    public void updateForge_repairAll() {
        updateForge_repairWeapon();
        updateForge_repairArmor();
    }

    /**
     * Updates weapon repair GUI elements in forge GUI.
     * If the player cannot afford to repair their weapon, the repair button is disabled
     * and it's cost label's font color is set to red.
     */

    public void updateForge_repairWeapon() {
        Weapon myWeapon = myMercenary.weapon;

        if (myWeapon != null) {
            int repairCost = (int) (((double) (myWeapon.maxDurability - myWeapon.durability) / (double) myWeapon.maxDurability) * myWeapon.repairCost);
            double durProgress = (double) myWeapon.durability / (double) myWeapon.maxDurability;

            repairWeaponName.setText(myWeapon.name);
            repairWeaponCost.setText("cost: " + repairCost + " gold");
            repairWeaponDurLabel.setText(myWeapon.durability + "/" + myWeapon.maxDurability);
            repairWeaponDurBar.setProgress(durProgress);

            if (myWeapon.durability <= 0.2 * myWeapon.maxDurability) {
                repairWeaponDurLabel.setTextFill(Color.web("red"));
            } else {
                repairWeaponDurLabel.setTextFill(Color.web("black"));
            }

            if (myMercenary.loot.gold < repairCost) {
                repairWeaponCost.setTextFill(Color.web("red"));
            } else {
                repairWeaponCost.setTextFill(Color.web("black"));
            }

            repairWeaponBtn.setDisable(myWeapon.durability >= myWeapon.maxDurability || myMercenary.loot.gold < repairCost);
        } else {
            repairWeaponName.setText("N/A");
            repairWeaponCost.setText("cost: N/A gold");
            repairWeaponDurLabel.setText("N/A");
            repairWeaponDurBar.setProgress(0);

            repairWeaponDurLabel.setTextFill(Color.web("black"));
            repairWeaponCost.setTextFill(Color.web("black"));

            repairWeaponBtn.setDisable(true);
        }
    }

    /**
     * Updates armor repair GUI elements in forge GUI.
     * If the player cannot afford to repair their armor, the repair button is disabled
     * and it's cost label's font color is set to red.
     */

    public void updateForge_repairArmor() {
        Armor myArmor = myMercenary.armor;

        if (myArmor != null) {
            int repairCost = (int)(((double)(myArmor.maxDurability - myArmor.durability) / (double)myArmor.maxDurability) * myArmor.repairCost);
            double durProgress = (double)myArmor.durability / (double)myArmor.maxDurability;

            repairArmorName.setText(myArmor.name);
            repairArmorCost.setText("cost: " + repairCost + " gold");
            repairArmorDurLabel.setText(myArmor.durability + "/" + myArmor.maxDurability);
            repairArmorDurBar.setProgress(durProgress);

            if (myArmor.durability <= 0.2 * myArmor.maxDurability) {
                repairArmorDurLabel.setTextFill(Color.web("red"));
            } else {
                repairArmorDurLabel.setTextFill(Color.web("black"));
            }

            if (myMercenary.loot.gold < repairCost) {
                repairArmorCost.setTextFill(Color.web("red"));
            } else {
                repairArmorCost.setTextFill(Color.web("black"));
            }

            repairArmorBtn.setDisable(myArmor.durability >= myArmor.maxDurability || myMercenary.loot.gold < repairCost);
        } else {
            repairArmorName.setText("N/A");
            repairArmorCost.setText("cost: N/A gold");
            repairArmorDurLabel.setText("N/A");
            repairArmorDurBar.setProgress(0);

            repairArmorDurLabel.setTextFill(Color.web("black"));
            repairArmorCost.setTextFill(Color.web("black"));

            repairArmorBtn.setDisable(true);
        }
    }
}

package GUI.Game.Controllers;

import Enviroments.Forge;
import Items.Armor.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.swing.event.ChangeListener;
import java.io.IOException;

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
        updateForge_trade();
        updateForge_repairAll();
    }

    public void repairArmor() {
        Armor myArmor = myMercenary.armor;

        int payment = (int)(((double)(myArmor.maxDurability - myArmor.durability) / (double)myArmor.maxDurability) * myArmor.repairCost);

        myMercenary.loot.payGold(payment);
        myArmor.repairItem();

        updatePlayer_gold();
        updatePlayer_armor();
        updateForge_trade();
        updateForge_repairAll();
    }

    public void returnToVillage() throws IOException {
        Scene myScene = ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Village.fxml"));
        Parent villageRoot = loader.load();

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

            itemNameAnchor.setTopAnchor(itemName, 0.0);
            itemNameAnchor.setLeftAnchor(itemName, 0.0);
            itemNameAnchor.setRightAnchor(itemName, 0.0);
            itemNameAnchor.setBottomAnchor(itemName, 0.0);

            AnchorPane itemDescrAnchor = new AnchorPane();
            ListView<String> itemDescr = new ListView<String>();
            if (pickedItem instanceof Weapon) {
                Weapon pickedWeapon = (Weapon) pickedItem;

                if (pickedWeapon.lowPhysicalDmg != 0 && pickedWeapon.highPhysicalDmg != 0) {
                    itemDescr.getItems().add("physical damage: " + pickedWeapon.lowPhysicalDmg + " - " + pickedWeapon.highPhysicalDmg);
                }

                if (pickedWeapon.lowPiercingDmg != 0 && pickedWeapon.highPiercingDmg != 0) {
                    itemDescr.getItems().add("piercing damage: " + pickedWeapon.lowPiercingDmg + " - " + pickedWeapon.highPiercingDmg);
                }
            }
            if (pickedItem instanceof Armor) {
                Armor pickedArmor = (Armor) pickedItem;

                itemDescr.getItems().add("armor: " + pickedArmor.armorVal);
            }
            itemDescr.setMouseTransparent(true);
            itemDescr.setFocusTraversable(false);
            itemDescrAnchor.getChildren().add(itemDescr);

            itemDescrAnchor.setTopAnchor(itemDescr, 0.0);
            itemDescrAnchor.setLeftAnchor(itemDescr, 0.0);
            itemDescrAnchor.setRightAnchor(itemDescr, 0.0);
            itemDescrAnchor.setBottomAnchor(itemDescr, 0.0);

            AnchorPane itemCostAnchor = new AnchorPane();
            Label itemCost = new Label();
            itemCost.setText("Cost: " + pickedItem.cost + " gold");
            itemCost.setAlignment(Pos.CENTER);
            itemCostAnchor.getChildren().add(itemCost);

            itemCostAnchor.setTopAnchor(itemCost, 0.0);
            itemCostAnchor.setLeftAnchor(itemCost, 0.0);
            itemCostAnchor.setRightAnchor(itemCost, 0.0);
            itemCostAnchor.setBottomAnchor(itemCost, 0.0);

            AnchorPane itemBtnAnchor = new AnchorPane();
            Button itemBtn = new Button();
            itemBtn.setText("Buy");

            if (myMercenary.loot.gold >= pickedItem.cost) {
                int finalItemIndex = itemIndex;

                itemBtn.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        if (pickedItem instanceof Weapon) {
                            Weapon pickedWeapon = (Weapon)pickedItem;

                            myMercenary.setWeapon(pickedWeapon);
                            updatePlayer_weapon();
                        }

                        if (pickedItem instanceof Armor) {
                            Armor pickedArmor = (Armor)pickedItem;

                            myMercenary.setArmor(pickedArmor);
                            updatePlayer_armor();
                        }

                        myMercenary.loot.payGold(pickedItem.cost);

                        myForge.forgeInventory.remove(finalItemIndex);

                        updatePlayer_gold();
                        updateForge_trade();
                        updateForge_repairAll();
                    }
                });

                itemCost.setTextFill(Color.web("black"));
            } else {
                itemBtn.setDisable(true);
                itemCost.setTextFill(Color.web("red"));
            }

            itemBtnAnchor.getChildren().add(itemBtn);

            itemBtnAnchor.setTopAnchor(itemBtn, 0.0);
            itemBtnAnchor.setLeftAnchor(itemBtn, 0.0);
            itemBtnAnchor.setRightAnchor(itemBtn, 0.0);
            itemBtnAnchor.setBottomAnchor(itemBtn, 0.0);

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

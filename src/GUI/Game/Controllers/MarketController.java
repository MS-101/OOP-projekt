package GUI.Game.Controllers;

import Entities.Player.PlayerConsumables;
import Environments.Market;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * This controller controls inn GUI elements and is an extension of GameController class.
 * This allow the player to interact with inn.
 */

public class MarketController extends GameController {
    @FXML
    Button hpPotion_btn;
    @FXML
    Button mpPotion_btn;

    @FXML
    Label hpPotion_name;
    @FXML
    Label mpPotion_name;
    @FXML
    Label hpPotion_descr;
    @FXML
    Label mpPotion_descr;
    @FXML
    Label hpPotion_cost;
    @FXML
    Label mpPotion_cost;

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
     * This method handles event of pressing buy hp potion button.
     * Calls buy hp potion method from market and updates all associated GUI elements.
     */

    public void buyHpPotion() {
        Market myMarket = myVillage.myMarket;

        myMarket.buyHpPotion(myMercenary);

        updatePlayer_gold();
        updatePlayer_hpPotions();
        updateMarket_tradeAll();

        saveGame();
    }

    /**
     * This method handles event of pressing buy mp potion button.
     * Calls buy mp potion method from market and updates all associated GUI elements.
     */

    public void buyMpPotion() {
        Market myMarket = myVillage.myMarket;

        myMarket.buyMpPotion(myMercenary);

        updatePlayer_gold();
        updatePlayer_mpPotions();
        updateMarket_tradeAll();

        saveGame();
    }

    /**
     * Updates all market GUI elements.
     */

    public void updateMarket_all() {
        updateMarket_tradeAll();
    }

    /**
     * Updates market trade tab GUI.
     */

    public void updateMarket_tradeAll() {
        updateMarket_tradeHpPotions();
        updateMarket_tradeMpPotions();
    }

    /**
     * Updates market GUI elements associated with buying hp potions.
     * When player doesn't have enough gold to buy hp potion,
     * buy button is disabled and cost font color is set to red.
     */

    public void updateMarket_tradeHpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        hpPotion_name.setText("hp potion [" + myConsumables.hpPotions_amount + "/" + myConsumables.hpPotions_maxAmount + "]");
        hpPotion_descr.setText("heals " + myConsumables.hpPotionData.hpHeal + " hp");
        hpPotion_cost.setText("cost: " + myConsumables.hpPotionData.cost);

        if (myConsumables.hpPotions_amount < myConsumables.hpPotions_maxAmount) {
            hpPotion_name.setTextFill(Color.web("black"));
        } else {
            hpPotion_name.setTextFill(Color.web("red"));
        }

        if (myMercenary.loot.gold >= myConsumables.hpPotionData.cost) {
            hpPotion_cost.setTextFill(Color.web("black"));
        } else {
            hpPotion_cost.setTextFill(Color.web("red"));
        }

        if (myConsumables.hpPotions_amount < myConsumables.hpPotions_maxAmount && myMercenary.loot.gold >= myConsumables.hpPotionData.cost) {
            hpPotion_btn.setDisable(false);
        } else {
            hpPotion_btn.setDisable(true);
        }
    }

    /**
     * Updates market GUI elements associated with buying mp potions.
     * When player doesn't have enough gold to buy mp potion,
     * buy button is disabled and cost font color is set to red.
     */

    public void updateMarket_tradeMpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        mpPotion_name.setText("mp potion [" + myConsumables.mpPotions_amount + "/" + myConsumables.mpPotions_maxAmount + "]");
        mpPotion_descr.setText("heals " + myConsumables.mpPotionData.mpHeal + " mp");
        mpPotion_cost.setText("cost: " + myConsumables.mpPotionData.cost);

        if (myConsumables.mpPotions_amount < myConsumables.mpPotions_maxAmount) {
            mpPotion_name.setTextFill(Color.web("black"));
        } else {
            mpPotion_name.setTextFill(Color.web("red"));
        }

        if (myMercenary.loot.gold >= myConsumables.mpPotionData.cost) {
            mpPotion_cost.setTextFill(Color.web("black"));
        } else {
            mpPotion_cost.setTextFill(Color.web("red"));
        }

        if (myConsumables.mpPotions_amount < myConsumables.mpPotions_maxAmount && myMercenary.loot.gold >= myConsumables.mpPotionData.cost) {
            mpPotion_btn.setDisable(false);
        } else {
            mpPotion_btn.setDisable(true);
        }
    }
}

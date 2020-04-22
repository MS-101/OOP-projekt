package GUI.Game.Controllers;

import Consumables.HpPotion;
import Consumables.MpPotion;
import Entities.Player.PlayerConsumables;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;

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

    public void returnToVillage() throws IOException {
        Scene myScene = (Scene) ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Village.fxml"));
        Parent villageRoot = (Parent) loader.load();

        VillageController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updatePlayer_all();

        myScene.setRoot(villageRoot);
    }

    public void buyHpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;
        HpPotion hpPotionData = new HpPotion();

        int payment = hpPotionData.cost;

        myMercenary.loot.payGold(payment);
        myConsumables.addHpPotions(1);

        updatePlayer_gold();
        updatePlayer_hpPotions();
        updateMarket_tradeAll();
    }

    public void buyMpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;
        MpPotion mpPotionData = new MpPotion();

        int payment = mpPotionData.cost;

        myMercenary.loot.payGold(payment);
        myConsumables.addMpPotions(1);

        updatePlayer_gold();
        updatePlayer_mpPotions();
        updateMarket_tradeAll();
    }

    public void updateMarket_all() {
        updateMarket_tradeAll();
    }

    public void updateMarket_tradeAll() {
        updateMarket_tradeHpPotions();
        updateMarket_tradeMpPotions();
    }

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

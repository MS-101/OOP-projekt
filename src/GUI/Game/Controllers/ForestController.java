package GUI.Game.Controllers;

import Entities.Player.PlayerConsumables;
import Entities.Player.PlayerSkills;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;

import java.io.IOException;

public class ForestController extends GameController {
    @FXML
    ToggleButton combat_attackBtn;
    @FXML
    ToggleButton combat_fireballBtn;

    @FXML
    Button combat_hpPotionBtn;
    @FXML
    Button combat_mpPotionBtn;
    @FXML
    Button combat_flamestrikeBtn;
    @FXML
    Button combat_healBtn;

    @FXML
    Button huntBtn;
    @FXML
    Button returnBtn;

    public void combat_pressAttackBtn(ActionEvent event) {

    }

    public void combat_pressHpPotionBtn(ActionEvent event) {
        combat_attackBtn.setSelected(false);
        combat_fireballBtn.setSelected(false);

        pressHpPotionBtn(event);

        updateForest_hpPotion();

        updateForest_heal();
    }

    public void combat_pressMpPotionBtn(ActionEvent event) {
        combat_attackBtn.setSelected(false);
        combat_fireballBtn.setSelected(false);

        pressMpPotionBtn(event);

        updateForest_mpPotion();

        updateForest_fireball();
        updateForest_flamestrike();
        updateForest_heal();
    }

    public void combat_pressFireballBtn(ActionEvent event) {

    }

    public void combat_pressFlamestrikeBtn(ActionEvent event) {
        PlayerSkills mySkills = myMercenary.skills;

        combat_attackBtn.setSelected(false);
        combat_fireballBtn.setSelected(false);

        updatePlayer_mp();

        updateForest_mpPotion();

        updateForest_fireball();
        updateForest_heal();
        updateForest_flamestrike();
    }

    public void combat_pressHealBtn(ActionEvent event) {
        PlayerSkills mySkills = myMercenary.skills;

        combat_attackBtn.setSelected(false);
        combat_fireballBtn.setSelected(false);

        mySkills.heal.cast(myMercenary);

        updatePlayer_hp();
        updatePlayer_mp();

        updateForest_hpPotion();
        updateForest_mpPotion();

        updateForest_fireball();
        updateForest_heal();
        updateForest_flamestrike();
    }

    public void hunt() {
        enableCombatButtons();
        setCombatButtons();
    }

    public void returnToVillage() throws IOException {
        Scene myScene = (Scene) ap.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Village.fxml"));
        Parent villageRoot = (Parent) loader.load();

        VillageController myController = loader.getController();
        myController.passUserData(accountsFile, myHashtable, myVillage, myMercenary);
        myController.updatePlayer_all();

        myScene.setRoot(villageRoot);
    }

    public void setForestButtons() {
        combat_attackBtn.setVisible(false);
        combat_hpPotionBtn.setVisible(false);
        combat_mpPotionBtn.setVisible(false);
        combat_fireballBtn.setVisible(false);
        combat_flamestrikeBtn.setVisible(false);
        combat_healBtn.setVisible(false);

        huntBtn.setVisible(true);
        returnBtn.setText("Return");

        enablePlayerButtons();
    }

    public void setCombatButtons() {
        combat_attackBtn.setVisible(true);
        combat_hpPotionBtn.setVisible(true);
        combat_mpPotionBtn.setVisible(true);
        combat_fireballBtn.setVisible(true);
        combat_flamestrikeBtn.setVisible(true);
        combat_healBtn.setVisible(true);

        huntBtn.setVisible(false);
        returnBtn.setText("Flee");

        disablePlayerButtons();
        enableCombatButtons();
    }

    public void disableCombatButtons() {
        combat_attackBtn.setDisable(true);
        combat_hpPotionBtn.setDisable(true);
        combat_mpPotionBtn.setDisable(true);
        combat_fireballBtn.setDisable(true);
        combat_flamestrikeBtn.setDisable(true);
        combat_healBtn.setDisable(true);
    }

    public void enableCombatButtons() {
        combat_attackBtn.setDisable(false);
        updateForest_All();
    }

    public void updateForest_All() {
        updateForest_hpPotion();
        updateForest_mpPotion();
        updateForest_fireball();
        updateForest_flamestrike();
        updateForest_heal();
    }

    public  void updateForest_fireball() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_fireballBtn.setText("fireball [lvl " + mySkills.fireball.curLvl + "]");

        if (mySkills.fireball.curLvl > 0 && myMercenary.mp > mySkills.fireball.mpCost) {
            combat_fireballBtn.setDisable(false);
        } else {
            combat_fireballBtn.setDisable(true);
        }
    }

    public  void updateForest_flamestrike() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_flamestrikeBtn.setText("flamestrike [lvl " + mySkills.flamestrike.curLvl + "]");

        if (mySkills.flamestrike.curLvl > 0 && myMercenary.mp > mySkills.flamestrike.mpCost) {
            combat_flamestrikeBtn.setDisable(false);
        } else {
            combat_flamestrikeBtn.setDisable(true);
        }
    }

    public  void updateForest_heal() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_healBtn.setText("heal [lvl " + mySkills.heal.curLvl + "]");

        if (mySkills.heal.curLvl > 0 && myMercenary.mp > mySkills.heal.mpCost && myMercenary.hp < myMercenary.maxHp) {
            combat_healBtn.setDisable(false);
        } else {
            combat_healBtn.setDisable(true);
        }
    }

    public void updateForest_hpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        combat_hpPotionBtn.setText("hp potion (" + myConsumables.hpPotions_amount + ")");

        if (myConsumables.hpPotions_amount > 0 && myMercenary.hp < myMercenary.maxHp) {
            combat_hpPotionBtn.setDisable(false);
        } else {
            combat_hpPotionBtn.setDisable(true);
        }
    }

    public void updateForest_mpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        combat_mpPotionBtn.setText("mp potion (" + myConsumables.mpPotions_amount + ")");

        if (myConsumables.mpPotions_amount > 0 && myMercenary.mp < myMercenary.maxMp) {
            combat_mpPotionBtn.setDisable(false);
        } else {
            combat_mpPotionBtn.setDisable(true);
        }
    }
}

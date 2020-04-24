package GUI.Game.Controllers;

import Combat.Combat;
import Entities.Monsters.Monster;
import Entities.Player.PlayerConsumables;
import Entities.Player.PlayerSkills;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class ForestController extends GameController {
    Combat myCombat;

    @FXML
    HBox MonsterHBox;

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

    @FXML
    ListView<String> combatLog;

    public void combat_pressAttackBtn(ActionEvent event) {

    }

    public void combat_pressHpPotionBtn(ActionEvent event) {
        combat_attackBtn.setSelected(false);
        combat_fireballBtn.setSelected(false);

        int prevHp = myMercenary.hp;

        pressHpPotionBtn(event);

        int effectiveHeal = myMercenary.hp - prevHp;
        sendMessage("You drank hp potion and restored " + effectiveHeal + " hp.");

        updateForest_hpPotion();

        updateForest_heal();
    }

    public void combat_pressMpPotionBtn(ActionEvent event) {
        combat_attackBtn.setSelected(false);
        combat_fireballBtn.setSelected(false);

        int prevMp = myMercenary.mp;

        pressMpPotionBtn(event);

        int effectiveHeal = myMercenary.mp - prevMp;
        sendMessage("You drank mp potion and restored " + effectiveHeal + " mp.");

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

        int prevHp = myMercenary.hp;

        mySkills.heal.cast(myMercenary);

        int effectiveHeal = myMercenary.hp - prevHp;
        sendMessage("You cast a spell and healed yourself for " + effectiveHeal + " hp.");

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

        myCombat = new Combat(myMercenary, this);
        generateMonsterPanes();
    }

    public void generateMonsterPanes() {
        int monsterIndex;

        for (monsterIndex = 0; monsterIndex < myCombat.opponents.size(); monsterIndex++) {
            Monster pickedMonster = myCombat.opponents.get(monsterIndex);
            double progress;

            AnchorPane monsterPaneAnchor = new AnchorPane();

            TitledPane monsterPane = new TitledPane();
            AnchorPane monsterPaneContent = new AnchorPane();

            monsterPane.setText(pickedMonster.name);
            monsterPane.setCollapsible(false);
            monsterPane.setFont(Font.font("System", FontWeight.BOLD, 18));

            SplitPane monsterSplitPane = new SplitPane();
            monsterSplitPane.setOrientation(Orientation.VERTICAL);

            AnchorPane monsterHpAnchor = new AnchorPane();
            Label monsterHpLabel = new Label();
            monsterHpLabel.setText(pickedMonster.hp + "/" + pickedMonster.maxHp);
            monsterHpLabel.setAlignment(Pos.CENTER);
            ProgressBar monsterHpBar = new ProgressBar();
            progress = (double)pickedMonster.hp / (double)pickedMonster.maxHp;
            monsterHpBar.setProgress(progress);
            monsterHpBar.setStyle("-fx-accent: red");

            monsterHpAnchor.getChildren().addAll(monsterHpBar, monsterHpLabel);

            monsterHpAnchor.setTopAnchor(monsterHpLabel,  0.0);
            monsterHpAnchor.setLeftAnchor(monsterHpLabel, 0.0);
            monsterHpAnchor.setRightAnchor(monsterHpLabel, 0.0);
            monsterHpAnchor.setBottomAnchor(monsterHpLabel, 0.0);
            monsterHpAnchor.setTopAnchor(monsterHpBar,  0.0);
            monsterHpAnchor.setLeftAnchor(monsterHpBar, 0.0);
            monsterHpAnchor.setRightAnchor(monsterHpBar, 0.0);
            monsterHpAnchor.setBottomAnchor(monsterHpBar, 0.0);

            AnchorPane monsterMpAnchor = new AnchorPane();
            Label monsterMpLabel = new Label();
            monsterMpLabel.setAlignment(Pos.CENTER);
            ProgressBar monsterMpBar = new ProgressBar();
            if (pickedMonster.maxMp != 0) {
                monsterMpLabel.setText(pickedMonster.mp + "/" + pickedMonster.maxMp);
                progress = (double)pickedMonster.mp / (double)pickedMonster.maxMp;
            } else {
                monsterMpLabel.setText("N/A");
                progress = 0;
            }
            monsterMpBar.setProgress(progress);
            monsterMpBar.setStyle("-fx-accent: lightblue");

            monsterMpAnchor.getChildren().addAll(monsterMpBar, monsterMpLabel);

            monsterMpAnchor.setTopAnchor(monsterMpLabel,  0.0);
            monsterMpAnchor.setLeftAnchor(monsterMpLabel, 0.0);
            monsterMpAnchor.setRightAnchor(monsterMpLabel, 0.0);
            monsterMpAnchor.setBottomAnchor(monsterMpLabel, 0.0);
            monsterMpAnchor.setTopAnchor(monsterMpBar,  0.0);
            monsterMpAnchor.setLeftAnchor(monsterMpBar, 0.0);
            monsterMpAnchor.setRightAnchor(monsterMpBar, 0.0);
            monsterMpAnchor.setBottomAnchor(monsterMpBar, 0.0);

            AnchorPane monsterBtnAnchor = new AnchorPane();
            Button monsterBtn = new Button();
            monsterBtn.setText("Target");

            monsterBtnAnchor.getChildren().addAll(monsterBtn);

            monsterBtnAnchor.setTopAnchor(monsterBtn,  0.0);
            monsterBtnAnchor.setLeftAnchor(monsterBtn, 0.0);
            monsterBtnAnchor.setRightAnchor(monsterBtn, 0.0);
            monsterBtnAnchor.setBottomAnchor(monsterBtn, 0.0);

            monsterSplitPane.getItems().addAll(monsterHpAnchor, monsterMpAnchor, monsterBtnAnchor);
            monsterSplitPane.setDividerPositions(0.33, 0.66);

            monsterPaneContent.getChildren().addAll(monsterSplitPane);

            monsterPaneContent.setTopAnchor(monsterSplitPane,  0.0);
            monsterPaneContent.setLeftAnchor(monsterSplitPane, 0.0);
            monsterPaneContent.setRightAnchor(monsterSplitPane, 0.0);
            monsterPaneContent.setBottomAnchor(monsterSplitPane, 0.0);

            monsterPane.setContent(monsterPaneContent);

            monsterPaneAnchor.getChildren().add(monsterPane);

            MonsterHBox.getChildren().add(monsterPaneAnchor);

            monsterPaneAnchor.setTopAnchor(monsterPane, 0.0);
            monsterPaneAnchor.setLeftAnchor(monsterPane, 0.0);
            monsterPaneAnchor.setRightAnchor(monsterPane, 0.0);
            monsterPaneAnchor.setBottomAnchor(monsterPane, 0.0);

            monsterPaneAnchor.setPrefWidth(200);
        }
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

    public void sendMessage(String message) {
        combatLog.getItems().add(message);
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

        combat_fireballBtn.setText("Fireball [lvl " + mySkills.fireball.curLvl + "]");

        if (mySkills.fireball.curLvl > 0 && myMercenary.mp > mySkills.fireball.mpCost) {
            combat_fireballBtn.setDisable(false);
        } else {
            combat_fireballBtn.setDisable(true);
        }
    }

    public  void updateForest_flamestrike() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_flamestrikeBtn.setText("Flamestrike [lvl " + mySkills.flamestrike.curLvl + "]");

        if (mySkills.flamestrike.curLvl > 0 && myMercenary.mp > mySkills.flamestrike.mpCost) {
            combat_flamestrikeBtn.setDisable(false);
        } else {
            combat_flamestrikeBtn.setDisable(true);
        }
    }

    public  void updateForest_heal() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_healBtn.setText("Heal [lvl " + mySkills.heal.curLvl + "]");

        if (mySkills.heal.curLvl > 0 && myMercenary.mp > mySkills.heal.mpCost && myMercenary.hp < myMercenary.maxHp) {
            combat_healBtn.setDisable(false);
        } else {
            combat_healBtn.setDisable(true);
        }
    }

    public void updateForest_hpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        combat_hpPotionBtn.setText("HP Potion (" + myConsumables.hpPotions_amount + ")");

        if (myConsumables.hpPotions_amount > 0 && myMercenary.hp < myMercenary.maxHp) {
            combat_hpPotionBtn.setDisable(false);
        } else {
            combat_hpPotionBtn.setDisable(true);
        }
    }

    public void updateForest_mpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        combat_mpPotionBtn.setText("MP Potion (" + myConsumables.mpPotions_amount + ")");

        if (myConsumables.mpPotions_amount > 0 && myMercenary.mp < myMercenary.maxMp) {
            combat_mpPotionBtn.setDisable(false);
        } else {
            combat_mpPotionBtn.setDisable(true);
        }
    }
}

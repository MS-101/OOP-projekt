package GUI.Game.Controllers;

import Combat.Combat;
import Entities.Monsters.Monster;
import Entities.Player.PlayerConsumables;
import Entities.Player.PlayerSkills;
import javafx.event.Event;
import javafx.event.EventHandler;
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

/**
 * This controller controls forest GUI elements and is an extension of GameController class.
 * It can switch between forest buttons and combat buttons.
 * Combat is initiated from here and player input during combat is taken from this controller.
 */

public class ForestController extends GameController {
    Combat myCombat;

    @FXML
    HBox monsterHBox;

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

    /**
     * This method handles event of pressing attack button.
     */

    public void combat_pressAttackBtn() {
        updateForest_monsterHBox();
    }

    /**
     * This method handles event of pressing fireball button.
     */

    public void combat_pressFireballBtn() {
        updateForest_monsterHBox();
    }

    /**
     * This method handles event of pressing hp potion button.
     */

    public void combat_pressHpPotionBtn() {
        myCombat.useHpPotion();
    }

    /**
     * This method handles event of pressing mp potion button.
     */

    public void combat_pressMpPotionBtn() {
        myCombat.useMpPotion();
    }

    /**
     * This method handles event of pressing flamestrike button.
     */

    public void combat_pressFlamestrikeBtn() {
        myCombat.useFlamestrike();
    }

    /**
     * This method handles event of pressing heal button.
     */

    public void combat_pressHealBtn() {
        myCombat.useHeal();
    }

    /**
     * This method handles event of pressing hunt button.
     * It creates new instance of combat and displays monsters in GUI.
     * It also sends message to combat log signaling the start of first turn.
     */

    public void pressHunt() {
        enableCombatButtons();
        setCombatButtons();

        myCombat = new Combat(myMercenary, this);
        updateForest_monsterHBox();
        combatLog.getItems().clear();
        sendMessage("TURN 1:");
    }

    /**
     * This method is used for updating displayed monsters in GUI.
     * It is capable of adding any amount of monsters to GUI.
     */

    public void updateForest_monsterHBox() {
        int monsterIndex;

        monsterHBox.getChildren().clear();

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

            AnchorPane.setTopAnchor(monsterHpLabel,  0.0);
            AnchorPane.setLeftAnchor(monsterHpLabel, 0.0);
            AnchorPane.setRightAnchor(monsterHpLabel, 0.0);
            AnchorPane.setBottomAnchor(monsterHpLabel, 0.0);
            AnchorPane.setTopAnchor(monsterHpBar,  0.0);
            AnchorPane.setLeftAnchor(monsterHpBar, 0.0);
            AnchorPane.setRightAnchor(monsterHpBar, 0.0);
            AnchorPane.setBottomAnchor(monsterHpBar, 0.0);

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

            AnchorPane.setTopAnchor(monsterMpLabel,  0.0);
            AnchorPane.setLeftAnchor(monsterMpLabel, 0.0);
            AnchorPane.setRightAnchor(monsterMpLabel, 0.0);
            AnchorPane.setBottomAnchor(monsterMpLabel, 0.0);
            AnchorPane.setTopAnchor(monsterMpBar,  0.0);
            AnchorPane.setLeftAnchor(monsterMpBar, 0.0);
            AnchorPane.setRightAnchor(monsterMpBar, 0.0);
            AnchorPane.setBottomAnchor(monsterMpBar, 0.0);

            AnchorPane monsterBtnAnchor = new AnchorPane();
            Button monsterBtn = new Button();
            monsterBtn.setText("Target");

            if (combat_attackBtn.isSelected() || combat_fireballBtn.isSelected()) {
                monsterBtn.setDisable(false);

                int finalMonsterIndex = monsterIndex;
                monsterBtn.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        if (combat_attackBtn.isSelected()) {
                            myCombat.useAttack(finalMonsterIndex);
                        }

                        if (combat_fireballBtn.isSelected()) {
                            myCombat.useFireball(finalMonsterIndex);
                        }
                    }
                });
            } else {
                monsterBtn.setDisable(true);
            }

            monsterBtnAnchor.getChildren().addAll(monsterBtn);

            AnchorPane.setTopAnchor(monsterBtn,  0.0);
            AnchorPane.setLeftAnchor(monsterBtn, 0.0);
            AnchorPane.setRightAnchor(monsterBtn, 0.0);
            AnchorPane.setBottomAnchor(monsterBtn, 0.0);

            monsterSplitPane.getItems().addAll(monsterHpAnchor, monsterMpAnchor, monsterBtnAnchor);
            monsterSplitPane.setDividerPositions(0.33, 0.66);

            monsterPaneContent.getChildren().addAll(monsterSplitPane);

            AnchorPane.setTopAnchor(monsterSplitPane,  0.0);
            AnchorPane.setLeftAnchor(monsterSplitPane, 0.0);
            AnchorPane.setRightAnchor(monsterSplitPane, 0.0);
            AnchorPane.setBottomAnchor(monsterSplitPane, 0.0);

            monsterPane.setContent(monsterPaneContent);

            monsterPaneAnchor.getChildren().add(monsterPane);

            monsterHBox.getChildren().add(monsterPaneAnchor);

            AnchorPane.setTopAnchor(monsterPane, 0.0);
            AnchorPane.setLeftAnchor(monsterPane, 0.0);
            AnchorPane.setRightAnchor(monsterPane, 0.0);
            AnchorPane.setBottomAnchor(monsterPane, 0.0);

            monsterPaneAnchor.setPrefWidth(200);
        }
    }

    /**
     * Removes all monsters from GUI
     */

    public void clearMonsters() {
        monsterHBox.getChildren().clear();
    }

    /**
     * This method handles event of pressing return/flee button.
     * When returning it simply sends you to village.
     * When fleeing it runs the flee method from combat.
     */

    public void pressReturn() {
        String returnBtnText = returnBtn.getText();

        if (returnBtnText.equals("Return")) {
            returnToVillage();
        } else if (returnBtnText.equals("Flee")) {
            myCombat.flee();
        }
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
     * This allows the controller to send messages to combat log.
     *
     * @param message String that will be printed in combat log.
     */

    public void sendMessage(String message) {
        combatLog.getItems().add(message);
        combatLog.scrollTo(combatLog.getItems().size());
    }

    /**
     * Sets buttons to forest buttons.
     * In this state player can interact with player pane's buttons.
     * In the forest pane player is allowed to interact with forest buttons,
     * for example they can hunt to initiate combat.
     * Return button is set to "return" state.
     */

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

    /**
     * Sets buttons to combat buttons.
     * In this state player cannot interact with player pane's buttons.
     * In the forest pane player is allowed to interact with combat buttons,
     * for example they can attack opponents, use skills, etc...
     * Return buttons is set to "flee" state.
     */

    public void setCombatButtons() {
        combat_attackBtn.setSelected(true);
        combat_fireballBtn.setSelected(false);

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

    /**
     * This method is called to prevent the player from acting during opponent's turn.
     */

    public void disableCombatButtons() {
        combat_attackBtn.setDisable(true);
        combat_hpPotionBtn.setDisable(true);
        combat_mpPotionBtn.setDisable(true);
        combat_fireballBtn.setDisable(true);
        combat_flamestrikeBtn.setDisable(true);
        combat_healBtn.setDisable(true);
    }

    /**
     * This method is called at the start of player turn to allow him to act.
     */

    public void enableCombatButtons() {
        combat_attackBtn.setDisable(false);
        updateForest_all();
    }

    /**
     * Updates all forest GUI elements.
     */

    public void updateForest_all() {
        updateForest_hpPotion();
        updateForest_mpPotion();
        updateForest_fireball();
        updateForest_flamestrike();
        updateForest_heal();
    }

    /**
     * Updates fireball button in forest GUI.
     * It will be disabled when the player has yet to learn this skill or they don't have enough mana to cast it.
     */

    public  void updateForest_fireball() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_fireballBtn.setText("Fireball [lvl " + mySkills.fireball.curLvl + "]");

        if (mySkills.fireball.curLvl > 0 && myMercenary.mp > mySkills.fireball.mpCost) {
            combat_fireballBtn.setDisable(false);
        } else {
            combat_fireballBtn.setDisable(true);
        }
    }

    /**
     * Updates flamestrike button in forest GUI.
     * It will be disabled when the player has yet to learn this skill or they don't have enough mana to cast it.
     */

    public  void updateForest_flamestrike() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_flamestrikeBtn.setText("Flamestrike [lvl " + mySkills.flamestrike.curLvl + "]");

        if (mySkills.flamestrike.curLvl > 0 && myMercenary.mp > mySkills.flamestrike.mpCost) {
            combat_flamestrikeBtn.setDisable(false);
        } else {
            combat_flamestrikeBtn.setDisable(true);
        }
    }

    /**
     * Updates heal button in forest GUI.
     * It will be disabled when the player has yet to learn this skill or they don't have enough mana to cast it.
     */

    public  void updateForest_heal() {
        PlayerSkills mySkills = myMercenary.skills;

        combat_healBtn.setText("Heal [lvl " + mySkills.heal.curLvl + "]");

        if (mySkills.heal.curLvl > 0 && myMercenary.mp > mySkills.heal.mpCost && myMercenary.hp < myMercenary.maxHp) {
            combat_healBtn.setDisable(false);
        } else {
            combat_healBtn.setDisable(true);
        }
    }

    /**
     * Updates hp potions button in forest GUI.
     * It will be disabled when the player is at full health or doesn't have any hp potions.
     */

    public void updateForest_hpPotion() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        combat_hpPotionBtn.setText("HP Potion (" + myConsumables.hpPotions_amount + ")");

        if (myConsumables.hpPotions_amount > 0 && myMercenary.hp < myMercenary.maxHp) {
            combat_hpPotionBtn.setDisable(false);
        } else {
            combat_hpPotionBtn.setDisable(true);
        }
    }

    /**
     * Updates mp potions button in forest GUI.
     * It will be disabled when the player is at full mana or doesn't have any mp potions.
     */

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

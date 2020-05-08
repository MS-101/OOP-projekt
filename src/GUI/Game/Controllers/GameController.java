package GUI.Game.Controllers;

import Entities.Player.Mercenary;
import Entities.Player.PlayerConsumables;
import Entities.Player.PlayerSkills;
import Entities.Player.PlayerStats;
import Environments.Village;
import Items.Armor.Armor;
import Items.Weapons.Weapon;
import MySystem.AccountsFileHandler;
import MySystem.AccountsHashTable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Parent class of all game controllers. This controller implements the handling of player tab.
 * Also contains data and methods that all of the game controllers need to operate.
 */

public class GameController implements Initializable {
    File accountsFile;
    AccountsHashTable myHashtable;

    Village myVillage;
    Mercenary myMercenary;

    @FXML
    AnchorPane ap;

    @FXML
    private TitledPane playerPane;
    @FXML
    private TitledPane consumablesPane;
    @FXML
    private TitledPane attrsPane;
    @FXML
    private TitledPane skillsPane;
    @FXML
    private TitledPane weaponPane;
    @FXML
    private TitledPane armorPane;

    @FXML
    private ProgressBar expBar;
    @FXML
    private ProgressBar hpBar;
    @FXML
    private ProgressBar mpBar;
    @FXML
    private ProgressBar weaponDurBar;
    @FXML
    private ProgressBar armorDurBar;

    @FXML
    private SplitPane weaponDescr;
    @FXML
    private SplitPane armorDescr;

    @FXML
    private Button hpPotionBtn;
    @FXML
    private Button mpPotionBtn;
    @FXML
    private Button strBtn;
    @FXML
    private Button dexBtn;
    @FXML
    private Button intBtn;
    @FXML
    private Button fireballBtn;
    @FXML
    private Button flamestrikeBtn;
    @FXML
    private Button healBtn;

    @FXML
    private Label expLabel;
    @FXML
    private Label hpLabel;
    @FXML
    private Label mpLabel;
    @FXML
    private Label weaponDurLabel;
    @FXML
    private Label armorDurLabel;
    @FXML
    private Label weapon_physDmg;
    @FXML
    private Label weapon_piercDmg;
    @FXML
    private Label armor_armorVal;
    @FXML
    private Label hpPotionLabel;
    @FXML
    private Label mpPotionLabel;
    @FXML
    private Label attrPtsLabel;
    @FXML
    private Label strLabel;
    @FXML
    private Label dexLabel;
    @FXML
    private Label intLabel;
    @FXML
    private Label skillPtsLabel;
    @FXML
    private Label fireballLabel;
    @FXML
    private Label flamestrikeLabel;
    @FXML
    private Label healLabel;
    @FXML
    private Label goldLabel;

    /**
     * This method allows controllers to pass user data between each other.
     *
     * @param accountsFile File that is used to store player accounts.
     * @param myHashtable Hashtable that is stores in player accounts file.
     * @param myVillage User's village.
     * @param myMercenary User's mercenary.
     */

    public void passUserData(File accountsFile, AccountsHashTable myHashtable, Village myVillage, Mercenary myMercenary) {
        this.accountsFile = accountsFile;
        this.myHashtable = myHashtable;

        this.myVillage = myVillage;
        this.myMercenary = myMercenary;
    }

    /**
     * Resets mercenary and updated all player tab GUI elements.
     */

    public void resetMercenary() {
        myMercenary.reset();
        updatePlayer_all();
    }

    /**
     * Saves user's game data to an external file.
     */

    public void saveGame() {
        AccountsFileHandler myAccountsFileHandler = new AccountsFileHandler();

        myAccountsFileHandler.rewriteAccountsFile(accountsFile, myHashtable);
    }

    /**
     * This method handles event of pressing log out button.
     * This will change the scene to login scene.
     */

    public void pressLogOutBtn() {
        Stage primaryStage = (Stage) ap.getScene().getWindow();

        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("../../UserLogin/UserLogin.fxml"));
            primaryStage.setScene(new Scene(loginRoot, 400, 300));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disables all buttons in player pane.
     * Used when entering combat.
     */

    public void disablePlayerButtons() {
        attrsPane.setDisable(true);
        skillsPane.setDisable(true);
        consumablesPane.setDisable(true);
    }

    /**
     * Enables all buttons in player pane.
     * Used when exiting combat.
     */

    public void enablePlayerButtons() {
        attrsPane.setDisable(false);
        skillsPane.setDisable(false);
        consumablesPane.setDisable(false);
    }

    /**
     * This method handles event of pressing hp potion button in player pane.
     * Makes the player use hp potion and updates all associated GUI elements.
     */

    public void pressHpPotionBtn() {
        myMercenary.consumables.useHpPotion(myMercenary);
        updatePlayer_hp();
        updatePlayer_hpPotions();

        saveGame();
    }

    /**
     * This method handles event of pressing mp potion button in player pane.
     * Makes the player use mp potion and updates all associated GUI elements.
     */

    public void pressMpPotionBtn() {
        myMercenary.consumables.useMpPotion(myMercenary);
        updatePlayer_mp();
        updatePlayer_mpPotions();

        saveGame();
    }

    /**
     * This method handles event of pressing strength button in player pane.
     * Makes the player increase their strength stat and updates all associated GUI elements.
     */

    public void pressStrengthBtn() {
        PlayerStats myStats = myMercenary.stats;

        myStats.increaseStrength(myMercenary);

        updatePlayer_attributes();
        updatePlayer_hp();

        saveGame();
    }

    /**
     * This method handles event of pressing dexterity button in player pane.
     * Makes the player increase their dexterity stat and updates all associated GUI elements.
     */

    public void pressDexterityBtn() {
        PlayerStats myStats = myMercenary.stats;

        myStats.increaseDexterity(myMercenary);

        updatePlayer_attributes();

        saveGame();
    }

    /**
     * This method handles event of pressing intelligence button in player pane.
     * Makes the player increase their intelligence stat and updates all associated GUI elements.
     */

    public void pressIntelligenceBtn() {
        PlayerStats myStats = myMercenary.stats;

        myStats.increaseIntelligence(myMercenary);

        updatePlayer_attributes();
        updatePlayer_mp();

        saveGame();
    }

    /**
     * This method handles event of pressing fireball button in player pane.
     * Makes the player upgrade their fireball skill and updates all associated GUI elements.
     */

    public void pressFireballBtn() {
        PlayerSkills mySkills = myMercenary.skills;

        mySkills.fireball.upgrade(myMercenary);

        updatePlayer_skills();

        saveGame();
    }

    /**
     * This method handles event of pressing flamestrike button in player pane.
     * Makes the player upgrade their flamestrike skill and updates all associated GUI elements.
     */

    public void pressFlamestrikeBtn() {
        PlayerSkills mySkills = myMercenary.skills;

        mySkills.flamestrike.upgrade(myMercenary);

        updatePlayer_skills();

        saveGame();
    }

    /**
     * This method handles event of pressing heal button in player pane.
     * Makes the player upgrade their heal skill and updates all associated GUI elements.
     */

    public void pressHealBtn() {
        PlayerSkills mySkills = myMercenary.skills;

        mySkills.heal.upgrade(myMercenary);

        updatePlayer_skills();

        saveGame();
    }

    /**
     * Updates all player GUI elements.
     */

    public void updatePlayer_all() {
        updatePlayer_nameLevel();

        updatePlayer_exp();
        updatePlayer_hp();
        updatePlayer_mp();

        updatePlayer_weapon();
        updatePlayer_armor();

        updatePlayer_hpPotions();
        updatePlayer_mpPotions();

        updatePlayer_attributes();
        updatePlayer_skills();

        updatePlayer_gold();
    }

    /**
     * Updates player name and level in player GUI.
     */

    public void updatePlayer_nameLevel() {
        playerPane.setText(myMercenary.name + " [lvl " + myMercenary.lvl + " ]");
    }

    /**
     * Updates player exp in player GUI.
     * If player is at max exp, it will be stated in exp label.
     */

    public void updatePlayer_exp() {
        if (myMercenary.lvl != myMercenary.maxLvl) {
            expLabel.setText(myMercenary.loot.exp + "/" + myMercenary.lvlRequirement);
            double progress = (double)myMercenary.loot.exp / (double)myMercenary.lvlRequirement;

            expBar.setProgress(progress);
        } else {
            expLabel.setText("Max level!");
            expBar.setProgress(1);
        }
    }

    /**
     * Updates player hp in player GUI.
     */

    public void updatePlayer_hp() {
        hpLabel.setText(myMercenary.hp + "/" + myMercenary.maxHp);

        double progress = (double)myMercenary.hp / (double)myMercenary.maxHp;
        hpBar.setProgress(progress);
    }

    /**
     * Updates player mp in player GUI.
     */

    public void updatePlayer_mp() {
        mpLabel.setText(myMercenary.mp + "/" + myMercenary.maxMp);

        double progress = (double)myMercenary.mp / (double)myMercenary.maxMp;
        mpBar.setProgress(progress);
    }

    /**
     * Updates player weapon in player GUI.
     */

    public void updatePlayer_weapon() {
        Weapon myWeapon = myMercenary.weapon;

        if (myWeapon != null) {
            weaponPane.setText("Weapon: " + myWeapon.name);

            weaponDurLabel.setText(myWeapon.durability + "/" + myWeapon.maxDurability);
            if (myWeapon.durability <= 0.2 * myWeapon.maxDurability) {
                weaponDurLabel.setTextFill(Color.web("red"));
            } else {
                weaponDurLabel.setTextFill(Color.web("black"));
            }

            double durProgress = (double)myWeapon.durability / (double)myWeapon.maxDurability;
            weaponDurBar.setProgress(durProgress);

            weapon_physDmg.setText("Physical damage: " + myWeapon.lowPhysDmg + "-" + myWeapon.highPhysDmg);
            weapon_piercDmg.setText("Piercing damage: " + myWeapon.lowPiercDmg + "-" + myWeapon.highPiercDmg);

            weaponDescr.setVisible(true);
        } else {
            weaponPane.setText("Weapon: N/A");

            weaponDescr.setVisible(false);
        }
    }

    /**
     * Updates player armor in player GUI.
     */

    public void updatePlayer_armor() {
        Armor myArmor = myMercenary.armor;

        if (myArmor != null) {
            armorPane.setText("Armor: " + myArmor.name);

            armorDurLabel.setText(myArmor.durability + "/" + myArmor.maxDurability);
            if (myArmor.durability <= 0.2 * myArmor.maxDurability) {
                armorDurLabel.setTextFill(Color.web("red"));
            } else {
                armorDurLabel.setTextFill(Color.web("black"));
            }

            double durProgress = (double)myArmor.durability / (double)myArmor.maxDurability;
            armorDurBar.setProgress(durProgress);

            armor_armorVal.setText("Armor: " + myArmor.armorVal);

            armorDescr.setVisible(true);
        } else {
            armorPane.setText("Armor: N/A");

            armorDescr.setVisible(false);
        }
    }

    /**
     * Updates player hp potions in player GUI.
     */

    public void updatePlayer_hpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        hpPotionLabel.setText("HP Potions: " + myConsumables.hpPotions_amount + "/" + myConsumables.hpPotions_maxAmount);

        if (myConsumables.hpPotions_amount > 0 && myMercenary.hp < myMercenary.maxHp) {
            hpPotionBtn.setDisable(false);
        } else {
            hpPotionBtn.setDisable(true);
        }
    }

    /**
     * Updates player mp potions in player GUI.
     */

    public void updatePlayer_mpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        mpPotionLabel.setText("MP Potions: " + myConsumables.mpPotions_amount + "/" + myConsumables.mpPotions_maxAmount);

        if (myConsumables.mpPotions_amount > 0 && myMercenary.mp < myMercenary.maxMp) {
            mpPotionBtn.setDisable(false);
        } else {
            mpPotionBtn.setDisable(true);
        }
    }

    /**
     * Updates player attributes in player GUI.
     */

    public void updatePlayer_attributes() {
        PlayerStats myStats = myMercenary.stats;

        attrPtsLabel.setText(myStats.attributePoints + " attribute points");

        strLabel.setText("Strength: " + myStats.strength);
        dexLabel.setText("Dexterity: " + myStats.dexterity);
        intLabel.setText("Intelligence: " + myStats.intelligence);

        if (myStats.attributePoints > 0) {
            strBtn.setDisable(false);
            dexBtn.setDisable(false);
            intBtn.setDisable(false);
        } else {
            strBtn.setDisable(true);
            dexBtn.setDisable(true);
            intBtn.setDisable(true);
        }
    }

    /**
     * Updates player skills in player GUI.
     */

    public void updatePlayer_skills() {
        PlayerSkills mySkills = myMercenary.skills;

        skillPtsLabel.setText(mySkills.skillPoints + " skill points");

        fireballLabel.setText("Fireball: " + mySkills.fireball.curLvl + "/" + mySkills.fireball.maxLvl);
        flamestrikeLabel.setText("Flamestrike: " + mySkills.flamestrike.curLvl + "/" + mySkills.flamestrike.maxLvl);
        healLabel.setText("Heal: " + mySkills.heal.curLvl + "/" + mySkills.heal.maxLvl);

        if (mySkills.skillPoints > 0) {
            if (mySkills.fireball.curLvl == mySkills.fireball.maxLvl) {
                fireballBtn.setDisable(true);
            } else {
                fireballBtn.setDisable(false);
            }

            if (mySkills.flamestrike.curLvl == mySkills.flamestrike.maxLvl) {
                flamestrikeBtn.setDisable(true);
            } else {
                flamestrikeBtn.setDisable(false);
            }

            if (mySkills.heal.curLvl == mySkills.heal.maxLvl) {
                healBtn.setDisable(true);
            } else {
                healBtn.setDisable(false);
            }
        } else {
            fireballBtn.setDisable(true);
            flamestrikeBtn.setDisable(true);
            healBtn.setDisable(true);
        }
    }

    /**
     * Updates player gold in player GUI.
     */

    public void updatePlayer_gold() {
        goldLabel.setText("Gold: " + myMercenary.loot.gold);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

    }
}

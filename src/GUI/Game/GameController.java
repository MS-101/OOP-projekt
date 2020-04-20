package GUI.Game;

import Entities.Player.Mercenary;
import Entities.Player.PlayerConsumables;
import Enviroments.Village;
import Items.Armor.Armor;
import Items.Weapons.Weapon;
import MySystem.Account;
import MySystem.AccountsHashTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class GameController implements Initializable {
    File accountsFile;
    AccountsHashTable myHashtable;

    Village myVillage;
    Mercenary myMercenary;

    @FXML
    private TitledPane playerPane;
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

    public void passUserData(File accountsFile, AccountsHashTable myHashtable, Village myVillage, Mercenary myMercenary) {
        this.accountsFile = accountsFile;
        this.myHashtable = myHashtable;

        this.myVillage = myVillage;
        this.myMercenary = myMercenary;
    }

    public void updatePlayer_All() {
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

        updatePlayer_Gold();
    }

    public void updatePlayer_nameLevel() {
        playerPane.setText(myMercenary.name + " [lvl " + myMercenary.lvl + " ]");
    }

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

    public void updatePlayer_hp() {
        hpLabel.setText(myMercenary.hp + "/" + myMercenary.maxHp);

        double progress = (double)myMercenary.hp / (double)myMercenary.maxHp;
        hpBar.setProgress(progress);
    }

    public void updatePlayer_mp() {
        mpLabel.setText(myMercenary.mp + "/" + myMercenary.maxMp);

        double progress = (double)myMercenary.mp / (double)myMercenary.maxMp;
        mpBar.setProgress(progress);
    }

    public void updatePlayer_weapon() {
        Weapon myWeapon = myMercenary.weapon;

        if (myWeapon != null) {
            weaponPane.setText("Weapon: " + myWeapon.name);

            weaponDurLabel.setText(myWeapon.durability + "/" + myWeapon.maxDurability);
            double progress = (double)myWeapon.durability / (double)myWeapon.maxDurability;
            weaponDurBar.setProgress(progress);

            weapon_physDmg.setText("Physical damage: " + myWeapon.lowPhysicalDmg + "-" + myWeapon.highPhysicalDmg);
            weapon_piercDmg.setText("Piercing damage: " + myWeapon.lowPiercingDmg + "-" + myWeapon.highPiercingDmg);

            weaponDescr.setVisible(true);
        } else {
            weaponPane.setText("Weapon: null");

            weaponDescr.setVisible(false);
        }
    }

    public void updatePlayer_armor() {
        Armor myArmor = myMercenary.armor;

        if (myArmor != null) {
            armorPane.setText("Armor: " + myArmor.name);

            armorDurLabel.setText(myArmor.durability + "/" + myArmor.maxDurability);
            double progress = (double)myArmor.durability / (double)myArmor.maxDurability;
            armorDurBar.setProgress(progress);

            armor_armorVal.setText("Armor: " + myArmor.armorVal);

            armorDescr.setVisible(true);
        } else {
            armorPane.setText("Armor: null");

            armorDescr.setVisible(false);
        }
    }

    public void updatePlayer_hpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        hpPotionLabel.setText("hp potions: " + myConsumables.hpPotions_amount + "/" + myConsumables.hpPotions_maxAmount);
        if (myConsumables.hpPotions_amount > 0) {
            hpPotionBtn.setDisable(false);
        } else {
            hpPotionBtn.setDisable(true);
        }
    }

    public void updatePlayer_mpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        mpPotionLabel.setText("mp potions: " + myConsumables.mpPotions_amount + "/" + myConsumables.mpPotions_maxAmount);
        if (myConsumables.mpPotions_amount > 0) {
            mpPotionBtn.setDisable(false);
        } else {
            mpPotionBtn.setDisable(true);
        }
    }

    public void updatePlayer_attributes() {
        attrPtsLabel.setText("You have " + myMercenary.attributePoints + " points remaining.");

        strLabel.setText("Strength: " + myMercenary.stats.strength);
        dexLabel.setText("Dexterity: " + myMercenary.stats.dexterity);
        intLabel.setText("Intelligence: " + myMercenary.stats.intelligence);

        if (myMercenary.attributePoints > 0) {
            strBtn.setDisable(false);
            dexBtn.setDisable(false);
            intBtn.setDisable(false);
        } else {
            strBtn.setDisable(true);
            dexBtn.setDisable(true);
            intBtn.setDisable(true);
        }
    }

    public void updatePlayer_skills() {
        skillPtsLabel.setText("You have " + myMercenary.skillPoints + " points remaining.");

        fireballLabel.setText("Fireball: " + myMercenary.skills.fireball.curLvl + "/" + myMercenary.skills.fireball.maxLvl);
        flamestrikeLabel.setText("Flamestrike: " + myMercenary.skills.flamestrike.curLvl + "/" + myMercenary.skills.flamestrike.maxLvl);
        healLabel.setText("Heal: " + myMercenary.skills.heal.curLvl + "/" + myMercenary.skills.heal.maxLvl);

        if (myMercenary.skillPoints > 0) {
            fireballBtn.setDisable(false);
            flamestrikeBtn.setDisable(false);
            healBtn.setDisable(false);
        } else {
            fireballBtn.setDisable(true);
            flamestrikeBtn.setDisable(true);
            healBtn.setDisable(true);
        }
    }

    public void updatePlayer_Gold() {
        goldLabel.setText("Gold: " + myMercenary.loot.gold);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

    }
}

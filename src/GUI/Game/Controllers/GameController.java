package GUI.Game.Controllers;

import Entities.Player.Mercenary;
import Entities.Player.PlayerConsumables;
import Entities.Player.PlayerSkills;
import Entities.Player.PlayerStats;
import Enviroments.Village;
import Items.Armor.Armor;
import Items.Weapons.Weapon;
import MySystem.AccountsHashTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



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

    public void pressHpPotionBtn (ActionEvent event) {
        myMercenary.consumables.useHpPotion(myMercenary);
        updatePlayer_hp();
        updatePlayer_hpPotions();
    }

    public void pressMpPotionBtn (ActionEvent event) {
        myMercenary.consumables.useMpPotion(myMercenary);
        updatePlayer_mp();
        updatePlayer_mpPotions();
    }

    public void pressStrengthBtn (ActionEvent event) {
        PlayerStats myStats = myMercenary.stats;

        myStats.attributePoints--;
        myStats.strength++;

        myMercenary.maxHp += 5;
        myMercenary.hp += 5;

        updatePlayer_attributes();
        updatePlayer_hp();
    }

    public void pressDexterityBtn (ActionEvent event) {
        PlayerStats myStats = myMercenary.stats;

        myStats.attributePoints--;
        myStats.dexterity++;

        updatePlayer_attributes();
    }

    public void pressIntelligenceBtn (ActionEvent event) {
        PlayerStats myStats = myMercenary.stats;

        myStats.attributePoints--;
        myStats.intelligence++;

        myMercenary.maxMp += 5;
        myMercenary.mp += 5;

        updatePlayer_attributes();
        updatePlayer_mp();
    }

    public void pressFireballBtn (ActionEvent event) {
        PlayerSkills mySkills = myMercenary.skills;

        mySkills.skillPoints--;
        mySkills.fireball.upgrade();

        updatePlayer_skills();
    }

    public void pressFlamestrikeBtn (ActionEvent event) {
        PlayerSkills mySkills = myMercenary.skills;

        mySkills.skillPoints--;
        mySkills.flamestrike.upgrade();

        updatePlayer_skills();
    }

    public void pressHealBtn (ActionEvent event) {
        PlayerSkills mySkills = myMercenary.skills;

        mySkills.skillPoints--;
        mySkills.heal.upgrade();

        updatePlayer_skills();
    }

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
            if (myWeapon.durability <= 0.2 * myWeapon.maxDurability) {
                weaponDurLabel.setTextFill(Color.web("red"));
            } else {
                weaponDurLabel.setTextFill(Color.web("black"));
            }

            double durProgress = (double)myWeapon.durability / (double)myWeapon.maxDurability;
            weaponDurBar.setProgress(durProgress);

            weapon_physDmg.setText("Physical damage: " + myWeapon.lowPhysicalDmg + "-" + myWeapon.highPhysicalDmg);
            weapon_piercDmg.setText("Piercing damage: " + myWeapon.lowPiercingDmg + "-" + myWeapon.highPiercingDmg);

            weaponDescr.setVisible(true);
        } else {
            weaponPane.setText("Weapon: N/A");

            weaponDescr.setVisible(false);
        }
    }

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

    public void updatePlayer_hpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        hpPotionLabel.setText("hp potions: " + myConsumables.hpPotions_amount + "/" + myConsumables.hpPotions_maxAmount);
        if (myConsumables.hpPotions_amount > 0 && myMercenary.hp < myMercenary.maxHp) {
            hpPotionBtn.setDisable(false);
        } else {
            hpPotionBtn.setDisable(true);
        }
    }

    public void updatePlayer_mpPotions() {
        PlayerConsumables myConsumables = myMercenary.consumables;

        mpPotionLabel.setText("mp potions: " + myConsumables.mpPotions_amount + "/" + myConsumables.mpPotions_maxAmount);
        if (myConsumables.mpPotions_amount > 0 && myMercenary.mp < myMercenary.maxMp) {
            mpPotionBtn.setDisable(false);
        } else {
            mpPotionBtn.setDisable(true);
        }
    }

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

    public void updatePlayer_gold() {
        goldLabel.setText("Gold: " + myMercenary.loot.gold);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

    }
}

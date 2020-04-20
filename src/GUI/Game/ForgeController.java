package GUI.Game;

import Enviroments.Forge;
import Items.Armor.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ForgeController extends GameController {
    public void buyItem(ActionEvent event) {
        Forge myForge = myVillage.myForge;

        Button myButton = (Button)event.getTarget();
        String buttonId = myButton.getId();
        int itemId = buttonId.charAt(4);

        Item pickedItem = myForge.forgeInventory.get(itemId);

        myMercenary.loot.payGold(pickedItem.cost);
        updatePlayer_Gold();

        if (pickedItem instanceof Weapon) {
            myMercenary.setWeapon((Weapon)pickedItem);
            updatePlayer_weapon();
        } else {
            myMercenary.setArmor((Armor)pickedItem);
            updatePlayer_armor();
        }

        myForge.forgeInventory.remove(itemId);
        updateForgeInventory();
    }

    public void updateForgeInventory() {
        int i;
        Forge myForge = myVillage.myForge;

        VBox test;

        for (i = 0; i < myForge.forgeInventory.size(); i++) {
            test.getChildren();
        }
    }
}

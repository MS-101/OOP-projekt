Instructions for the project Mercenary Hunter:

Functions bytesToHex and securePassword from AccountsHashTable use code from the following website: https://www.baeldung.com/sha-256-hashing-java (8.4.2020)

Example of inheritance - class Ghouls is a child class of Monster, which is a child class of Entity.
Example of polymorphism - class Item has abstract function getCopy() and its child classes Weapon and Armor overwrite this function. (used for generating random forge inventory)
Example of aggregation - class Mercenary contains references to classes PlayerConsumables, PlayerLoot, PlayerSkills and PlayerStats.

When you first launch the game you will be asked to register an account.
The next time you start the game you will be able to log in to that account.
All passwords are encrypted using SHA-512 with salt and pepper.
User password is required to contain at least 8 characters, lowercase letter, uppercase letter, number and special symbol.

Game auto-saves at certain points.
You can log out from your account at any time (button in bottom right corner).
Game GUI contains 2 panes - location pane on the left and character pane on the right.

In game you can visit the following locations:
Inn - you can rest to restore hp and mp there
Forge - you can buy and repair equipment there
Market - you can buy consumables there
Forest - you can hunt monsters there

After acquiring enough exp points, the player levels up and gains 2 attribute points and 1 skill point.
You can allocate them at any time outside of combat in the character pane.

Player attributes:
strength - increases physical damage and max hp
dexterity - increases piercing damage
intelligence - increases skill power and max mp

Player skills:
fireball - deal damage to single target
flamestrike - deals damage to all opponents
heal - heals your hp

Consumables explained
- you can have at most 10 consumables of each type
- there are currently 2 consumables - hp potion (heals hp) and mp potion (heals mp)

Items explained
- you can equip a weapon and armor
- items have durability, if it falls to 0 the item will break
- when item is heavily damaged it is less effective
- weapons can deal physical and piercing damage
- armor can protect against physical damage

Combat mechanics:
Combat is turn based, player always starts first.
In combat you can fight at most 3 opponents at a time.
You cannot interact with character pane during combat.
You get exp and gold from killing monsters.

During player turn, the player can decide to do one of the following:
Targetable abilities:
- attack - deal damage with your weapon (can miss or crit)
- fireball - deal damage to single target

Non-targetable abilities:
- hp potion - use hp potion
- mp potion - use mp potion
- flamestrike - deals damage to all opponents
- heal - heals your hp
- flee - attempt to escape from battle

During enemy turn monsters attack the player.
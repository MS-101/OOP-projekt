package MySystem;

import Entities.Player.Mercenary;
import Environments.Village;

import java.io.Serializable;

/**
 * All account information and game data is stored here.
 * Password is stored here in protected form.
 * It is hashed with hashing algorithm SHA-512, it is also salted and peppered.
 */

public class Account implements Serializable {
    String username;
    String protectedPassword;
    String salt;

    Village accountVillage;
    Mercenary accountMercenary;

    /**
     * Sets user's username.
     *
     * @param username Username to be stored.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets user's protected password.
     *
     * @param protectedPassword Protected password to be stored.
     */

    public void setProtectedPassword(String protectedPassword) {
        this.protectedPassword = protectedPassword;
    }

    /**
     * Sets user's salt.
     * Salt is randomly generated string with length 10,
     * which is used as password's suffix before hashing.
     *
     * @param salt Salt to be stored.
     */

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Sets user's village.
     *
     * @param accountVillage Village to be stored.
     */

    public void setAccountVillage(Village accountVillage) {
        this.accountVillage = accountVillage;
    }

    /**
     * Sets user's mercenary.
     *
     * @param accountMercenary Mercenary to be stored.
     */

    public void setAccountMercenary(Mercenary accountMercenary) {
        this.accountMercenary = accountMercenary;
    }

    /**
     * Get user's username.
     *
     * @return Returns username.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Get user's protected password.
     *
     * @return Returns protected password.
     */

    public String getProtectedPassword() {
        return protectedPassword;
    }

    /**
     * Get user's salt.
     *
     * @return Returns salt.
     */

    public String getSalt() {
        return salt;
    }

    /**
     * Get user's village.
     *
     * @return Returns village.
     */

    public Village getAccountVillage() {
        return accountVillage;
    }

    /**
     * Get user's mercenary.
     *
     * @return Returns mercenary.
     */

    public Mercenary getAccountMercenary() {
        return accountMercenary;
    }
}

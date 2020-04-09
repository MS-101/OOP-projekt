package MySystem;

import Entities.Player.Mercenary;
import Enviroments.Village;

import java.io.Serializable;

public class Account implements Serializable {
    String username;
    String securedPassword;
    String salt;

    Village accountVillage;
    Mercenary accountMercenary;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String securedPassword) {
        this.securedPassword = securedPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setAccountVillage(Village accountVillage) {
        this.accountVillage = accountVillage;
    }

    public void setAccountMercenary(Mercenary accountMercenary) {
        this.accountMercenary = accountMercenary;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return securedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public Village getAccountVillage() {
        return accountVillage;
    }

    public Mercenary getAccountMercenary() {
        return accountMercenary;
    }
}

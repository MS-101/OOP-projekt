package System;

import Entities.Player.Mercenary;
import Enviroments.Village;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountsHashTable implements Serializable {
    int hashtableSize;
    ArrayList<AccountNode> accountHashTable;

    public AccountsHashTable(int hashtableSize) {
        int i;

        this.hashtableSize = hashtableSize;

        accountHashTable = new ArrayList<>();
        for (i = 0; i < hashtableSize; i++) {
            accountHashTable.add(new AccountNode());
        }
    }

    public boolean addAcount(String username, String securedPassword) {
        int key = username.hashCode() % hashtableSize;

        AccountNode pickedAccountNode = this.accountHashTable.get(key);

        int chainIndex = 0;
        while (pickedAccountNode.curAccount != null) {
            if (pickedAccountNode.curAccount.username.equals(username)) {
                System.out.println("Account with this username already exists!");

                return false;
            }

            pickedAccountNode = pickedAccountNode.nextAccount;
            chainIndex++;
        }

        pickedAccountNode.curAccount = new Account();
        pickedAccountNode.nextAccount = new AccountNode();

        Account pickedAccount = pickedAccountNode.curAccount;

        pickedAccount.username = username;
        pickedAccount.securedPassword = securedPassword;
        pickedAccount.setAccountVillage(new Village());
        pickedAccount.setAccountMercenary(new Mercenary());

        /*
        System.out.println("key = " + key);
        System.out.println("username = " + username);
        System.out.println("password = " + securedPassword);
        System.out.println("chain index = " + chainIndex);
         */

        return true;
    }
}

package MySystem;

import java.io.*;

public class AccountsFileHandler implements Serializable {
    public void createAccountsFile(File accountsFile) {
        AccountsHashTable newHashTable = new AccountsHashTable(1000);

        try {
            FileOutputStream fo = new FileOutputStream(accountsFile);
            ObjectOutputStream fileOutput = new ObjectOutputStream(fo);
            fileOutput.writeObject(newHashTable);

            fileOutput.close();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteAccountsFile(File accountsFile, AccountsHashTable myHashTable) {
        try {
            FileOutputStream fo = new FileOutputStream(accountsFile);
            ObjectOutputStream fileOutput = new ObjectOutputStream(fo);
            fileOutput.writeObject(myHashTable);

            fileOutput.close();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AccountsHashTable readAccountsFile(File accountsFile) {
        try {
            FileInputStream fi = new FileInputStream(accountsFile);
            ObjectInputStream input = new ObjectInputStream(fi);
            AccountsHashTable myHashTable = (AccountsHashTable) input.readObject();

            input.close();
            fi.close();

            return myHashTable;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

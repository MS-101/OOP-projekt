package MySystem;

import java.io.*;

/**
 * This class implements methods for handling accounts file.
 */

public class AccountsFileHandler implements Serializable {

    /**
     * Creates new accounts file.
     *
     * @param accountsFile Accounts file to be created.
     */

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

    /**
     * Overwrites existing accounts file's hashtable.
     *
     * @param accountsFile Accounts file to be overwritten.
     * @param myHashTable Hashtable that will overwrite the existing hashtable in accounts file.
     */

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

    /**
     * Reads hashtable from accounts file.
     *
     * @param accountsFile Account file to be read.
     * @return Returns accounts hashtable.
     */

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

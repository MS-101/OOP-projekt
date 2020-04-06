package MySystem;

import java.io.*;

public class AccountHandler implements Serializable {
    public void createAccountsFile(File accountsFile) throws FileNotFoundException, IOException {
        AccountsHashTable newHashTable = new AccountsHashTable(1000);

        FileOutputStream fo = new FileOutputStream(accountsFile);
        ObjectOutputStream fileOutput = new ObjectOutputStream(fo);
        fileOutput.writeObject(newHashTable);

        fileOutput.close();
        fo.close();
    }

    public void rewriteAccountsFile(File accountsFile, AccountsHashTable myHashTable) throws FileNotFoundException, IOException {
        FileOutputStream fo = new FileOutputStream(accountsFile);
        ObjectOutputStream fileOutput = new ObjectOutputStream(fo);
        fileOutput.writeObject(myHashTable);

        fileOutput.close();
        fo.close();
    }

    public AccountsHashTable readAccountsFile(File accountsFile) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(accountsFile);
        ObjectInputStream input = new ObjectInputStream(fi);
        AccountsHashTable myHashTable = (AccountsHashTable) input.readObject();

        input.close();
        fi.close();

        return myHashTable;
    }
}

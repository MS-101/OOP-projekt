import Entities.Player.Mercenary;
import Enviroments.Village;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.*;
import System.*;

public class Game {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        Mercenary myPlayer;
        Village myVillage;
        File accountsFile = new File("accounts.txt");
        AccountsHashTable myHashTable;

        //createAccountsFile(accountsFile);
        myHashTable = readAccountsFile(accountsFile);

        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter one of the following commands:");
            System.out.println("REGISTER - create new account");
            System.out.println("LOGIN - login to an existing account");
            System.out.println("EXIT - terminate this app");
            System.out.println();

            while (true) {
                String consoleInput = myScanner.nextLine();

                if (consoleInput.equalsIgnoreCase("register")) {
                    System.out.println();
                    register(accountsFile, myHashTable);
                    break;
                }

                if (consoleInput.equalsIgnoreCase("login")) {
                    System.out.println();
                    login();
                }

                if (consoleInput.equalsIgnoreCase("exit")) {
                    return;
                }

                System.out.println("Incorrect command!");
            }
        }

        //myVillage.visit(myPlayer);
    }

    private static void register(File accountsFile, AccountsHashTable myHashTable) throws NoSuchAlgorithmException, IOException {
        Scanner myScanner = new Scanner(System.in);

        System.out.print("username: ");
        String username = myScanner.nextLine();

        System.out.print("password: ");
        String unprotectedPassword = myScanner.nextLine();
        String securePassword = securePassword(unprotectedPassword);

        System.out.println();

        if (myHashTable.addAcount(username, securePassword)) {
            System.out.println("Registration succesfull!");
            rewriteAccountsFile(accountsFile, myHashTable);
        }
        System.out.println();
    }

    private static void login() {

    }

    private static String securePassword(String unprotectedPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(unprotectedPassword.getBytes(StandardCharsets.UTF_8));

        String protectedPassword = bytesToHex(encodedhash);

        return protectedPassword;
    }

    private static void createAccountsFile(File accountsFile) throws FileNotFoundException, IOException {
        AccountsHashTable newHashTable = new AccountsHashTable(1000);

        FileOutputStream fo = new FileOutputStream(accountsFile);
        ObjectOutputStream fileOutput = new ObjectOutputStream(fo);
        fileOutput.writeObject(newHashTable);

        fileOutput.close();
        fo.close();
    }

    private static void rewriteAccountsFile(File accountsFile, AccountsHashTable myHashTable) throws FileNotFoundException, IOException {
        FileOutputStream fo = new FileOutputStream(accountsFile);
        ObjectOutputStream fileOutput = new ObjectOutputStream(fo);
        fileOutput.writeObject(myHashTable);

        fileOutput.close();
        fo.close();
    }

    private static AccountsHashTable readAccountsFile(File accountsFile) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(accountsFile);
        ObjectInputStream input = new ObjectInputStream(fi);
        AccountsHashTable myHashTable = (AccountsHashTable) input.readObject();

        input.close();
        fi.close();

        return myHashTable;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

package MySystem;

import Entities.Player.Mercenary;
import Environments.Village;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.abs;

//Functions bytesToHex and securePassword use code from the following website: https://www.baeldung.com/sha-256-hashing-java (8.4.2020)

/**
 * This class represents hash table that is stored in accounts file.
 * It implements methods for account registration, login and password security.
 */

public class AccountsHashTable implements Serializable {
    int hashtableSize;
    ArrayList<AccountNode> accountHashTable;

    /**
     * This constructor creates empty hashtable.
     *
     * @param hashtableSize Size of the new hashtable.
     */

    public AccountsHashTable(int hashtableSize) {
        int i;

        this.hashtableSize = hashtableSize;

        accountHashTable = new ArrayList<>();
        for (i = 0; i < hashtableSize; i++) {
            accountHashTable.add(new AccountNode());
        }
    }

    /**
     * Checks if password is secure.
     * Secure password contains at least 8 characters, lowercase letter,
     * uppercase letter, number and a special symbol.
     *
     * @param unprotectedPassword String that will be checked for password strength.
     * @return Returns true if password is secure or false if password is weak.
     */

    public boolean isPasswordStrong(String unprotectedPassword) {
        int passwordLength = unprotectedPassword.length();
        if (passwordLength < 8) {
            return false;
        }

        boolean lowerCaseFound = false;
        boolean upperCaseFound = false;
        boolean numberFound = false;
        boolean specialFound = false;

        int i;
        for (i = 0; i < unprotectedPassword.length(); i++) {
            char curChar = unprotectedPassword.charAt(i);

            if (curChar > 'a' && curChar < 'z') {
                lowerCaseFound = true;
                continue;
            }

            if (curChar > 'A' && curChar < 'Z') {
                upperCaseFound = true;
                continue;
            }

            if (curChar > '0' && curChar < '9') {
                numberFound = true;
                continue;
            }

            specialFound = true;
        }

        return (lowerCaseFound && upperCaseFound && numberFound && specialFound);
    }

    /**
     * Attempts to register a new account.
     * Registration can fail if username is already in use.
     * Password is secured before it is stored in hashtable.
     *
     * @param username New account username.
     * @param unprotectedPassword New account password.
     * @return Returns registered account if successful, or null if registration failed.
     */

    public Account register(String username, String unprotectedPassword) {
        int key = abs((username.hashCode())) % hashtableSize;

        AccountNode pickedAccountNode = this.accountHashTable.get(key);

        while (pickedAccountNode.curAccount != null) {
            if (pickedAccountNode.curAccount.username.equals(username)) {
                return null;
            }

            pickedAccountNode = pickedAccountNode.nextAccount;
        }

        pickedAccountNode.curAccount = new Account();
        pickedAccountNode.nextAccount = new AccountNode();

        Account pickedAccount = pickedAccountNode.curAccount;

        String salt = generateSalt();
        char pepper = generatePepper();

        String protectedPassword = securePassword(unprotectedPassword, salt, pepper);

        pickedAccount.setUsername(username);
        pickedAccount.setProtectedPassword(protectedPassword);
        pickedAccount.setSalt(salt);
        pickedAccount.setAccountVillage(new Village());
        pickedAccount.setAccountMercenary(new Mercenary(username));

        return pickedAccount;
    }

    /**
     * Attempts to login to an existing account.
     * Login can fail if account doesn't exist or password is incorrect.
     *
     * @param username Username of existing account.
     * @param unprotectedPassword Password of existing account.
     * @return Returns login account if it exist and entered password is correct, otherwise it returns null.
     */

    public Account login(String username, String unprotectedPassword) {
        int key = abs(username.hashCode()) % hashtableSize;

        AccountNode pickedAccountNode = this.accountHashTable.get(key);

        while (pickedAccountNode.curAccount != null) {
            Account pickedAccount = pickedAccountNode.curAccount;
            if (pickedAccount.username.equals(username)) {
                String salt = pickedAccount.getSalt();
                char pepper;

                for (pepper = 'a'; pepper <= 'z'; pepper++) {
                    String protectedPassword = securePassword(unprotectedPassword, salt, pepper);

                    if (pickedAccount.protectedPassword.equals(protectedPassword)) {
                        return pickedAccount;
                    }
                }

                return null;
            }

            pickedAccountNode = pickedAccountNode.nextAccount;
        }

        return null;
    }

    /**
     * Secures password with hashing algorithm, salt and pepper.
     * First the password is appended with salt and pepper.
     * Then the password is hashed multiple times with hashing algorithm SHA-512.
     *
     * @param unprotectedPassword Unprotected password to be secured.
     * @param salt This string will be appended to unprotected password first.
     * @param pepper THis string will be appended to unprotected password last.
     * @return Returns protected password.
     */

    private static String securePassword(String unprotectedPassword, String salt, char pepper) {
        String appendedPassword = unprotectedPassword + salt + pepper;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] passwordBytes = appendedPassword.getBytes(StandardCharsets.UTF_8);
            int i, passwordStrength = 100;

            for (i = 0; i < passwordStrength; i++) {
                passwordBytes = digest.digest(passwordBytes);
            }

            String protectedPassword = bytesToHex(passwordBytes);

            return protectedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Changes bytes to hex and then changes it to string.
     *
     * @param hash Password hash stored in bytes.
     * @return Returns password hash stored in string.
     */

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * Generates random string with 10 characters.
     *
     * @return Returns salt string.
     */

    public String generateSalt() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String salt = new String(array, StandardCharsets.UTF_8);

        return salt;
    }

    /**
     * Generates random character from range of a-z.
     *
     * @return Returns pepper character.
     */

    public char generatePepper() {
        Random random = new Random();
        char pepper = (char) (random.nextInt(26) + 'a');

        return pepper;
    }
}

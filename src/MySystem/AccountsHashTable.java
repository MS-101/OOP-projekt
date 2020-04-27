package MySystem;

import Entities.Player.Mercenary;
import Enviroments.Village;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.abs;

//Functions bytesToHex and securePassword use code from the following website: https://www.baeldung.com/sha-256-hashing-java (8.4.2020)

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

    public Account register(String username, String unprotectedPassword) throws NoSuchAlgorithmException {
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

    public Account login(String username, String unprotectedPassword) throws NoSuchAlgorithmException {
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
            }

            pickedAccountNode = pickedAccountNode.nextAccount;
        }

        return null;
    }

    private static String securePassword(String unprotectedPassword, String salt, char pepper) throws NoSuchAlgorithmException {
        String appendedPassword = unprotectedPassword + salt + pepper;

        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] passwordBytes = appendedPassword.getBytes(StandardCharsets.UTF_8);
        int i, passwordStrength = 100;

        for (i = 0; i < passwordStrength; i++) {
            passwordBytes = digest.digest(passwordBytes);
        }

        String protectedPassword = bytesToHex(passwordBytes);

        return protectedPassword;
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

    public String generateSalt() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String salt = new String(array, StandardCharsets.UTF_8);

        return salt;
    }

    public char generatePepper() {
        Random random = new Random();
        char pepper = (char) (random.nextInt(26) + 'a');

        return pepper;
    }
}

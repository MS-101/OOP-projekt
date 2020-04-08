import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/UserLogin.fxml"));
        primaryStage.setTitle("Mercenary Hunter");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
        /*
        Mercenary myPlayer;
        Village myVillage;
        File accountsFile = new File("accounts.txt");
        AccountHandler myAccountManager = new AccountHandler();
        AccountsHashTable myHashTable;

        if (!accountsFile.exists()) {
            myAccountManager.createAccountsFile(accountsFile);
        }

        myHashTable = myAccountManager.readAccountsFile(accountsFile);

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
                    login(accountsFile, myHashTable);
                    break;
                }

                if (consoleInput.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }

                System.out.println("Incorrect command!");
            }
        }
    }

    private static void register(File accountsFile, AccountsHashTable myHashTable) throws NoSuchAlgorithmException, IOException {
        Scanner myScanner = new Scanner(System.in);
        AccountHandler myAccountManager = new AccountHandler();

        System.out.print("username: ");
        String username = myScanner.nextLine();

        System.out.print("password: ");
        String unprotectedPassword = myScanner.nextLine();
        String securePassword = securePassword(unprotectedPassword);

        System.out.println();

        Account registeredAccount = myHashTable.register(username, securePassword);
        if (registeredAccount != null) {
            System.out.println("Registration succesfull!");
            System.out.println();

            myAccountManager.rewriteAccountsFile(accountsFile, myHashTable);

            Village myVillage = registeredAccount.getAccountVillage();
            Mercenary myMercenary = registeredAccount.getAccountMercenary();

            myVillage.visit(accountsFile, myHashTable, myMercenary);
        }
    }

    private static void login(File accountsFile, AccountsHashTable myHashTable) throws NoSuchAlgorithmException, IOException {
        Scanner myScanner = new Scanner(System.in);

        System.out.print("username: ");
        String username = myScanner.nextLine();

        System.out.print("password: ");
        String unprotectedPassword = myScanner.nextLine();
        String securePassword = securePassword(unprotectedPassword);

        Account myAccount = myHashTable.login(username, securePassword);
        if (myAccount != null) {
            System.out.println("Login succesfull!");
            System.out.println();

            Village myVillage = myAccount.getAccountVillage();
            Mercenary myMercenary = myAccount.getAccountMercenary();

            myVillage.visit(accountsFile, myHashTable, myMercenary);
        } else {
            System.out.println("Login fail!");
            System.out.println();
        }
    }

    private static String securePassword(String unprotectedPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(unprotectedPassword.getBytes(StandardCharsets.UTF_8));

        String protectedPassword = bytesToHex(encodedhash);

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
    */
}

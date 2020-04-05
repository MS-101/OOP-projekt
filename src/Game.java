import Entities.Player.Mercenary;
import Enviroments.Village;

import java.util.Scanner;
import java.io.*;
import System.*;

public class Game {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Mercenary player = new Mercenary();
        Village myVillage = new Village();

        //serialization input
        File accountsFile = new File("accounts.txt");
        Account myAccount = new Account();

        FileOutputStream fo = new FileOutputStream(accountsFile);
        ObjectOutputStream output = new ObjectOutputStream(fo);
        output.writeObject(myAccount);

        output.close();
        fo.close();

        /*
        //write to new file
        try {
            PrintWriter fileOutput = new PrintWriter(accountsFile);

            fileOutput.println("myName");
            fileOutput.println("myPassword");
            fileOutput.close();
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }

        //read from new file
        try {
            Scanner fileInput = new Scanner(accountsFile);

            String name = fileInput.nextLine();
            String  password = fileInput.nextLine();

            System.out.println("account name: " + name);
            System.out.println("account password: " + password);
        } catch (FileNotFoundException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }
        */

        //myVillage.visit(player);
    }
}

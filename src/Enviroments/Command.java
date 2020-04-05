package Enviroments;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Command implements Serializable {
    String name;

    String param1Str;
    String param2Str;

    int param1Int;
    int param2Int;

    public void readInput() {
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine();
        StringTokenizer st = new StringTokenizer(input," ");

        name = st.nextToken();

        param1Str = null;
        if (st.hasMoreTokens()) {
            param1Str = st.nextToken();
            if (param1Str.matches("[0-9]+")) {
                param1Int = Integer.parseInt(param1Str);
            }
        }

        param2Str = null;
        if (st.hasMoreTokens()) {
            param2Str = st.nextToken();
            if (param2Str.matches("[0-9]+")) {
                param2Int = Integer.parseInt(param2Str);
            }
        }
    }
}

package traffic.util;

import java.io.IOException;
import java.util.Scanner;

public class Util {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String INPUT_ERROR = "Error! Incorrect Input. Try again!";

    private Util() {
    }

    /**
     * Gets a positive integer from the user.
     *
     * @param min    the minimum value of the integer.
     * @param message the message to be displayed to the user.
     * @return the positive integer.
     */
    public static int getPositiveInt(int min, String message) {
        System.out.print(message);
        try {
            int positiveInt = Integer.parseInt(SCANNER.nextLine());
            if (positiveInt < min) {
                return getPositiveInt(min, INPUT_ERROR);
            }
            return positiveInt;
        } catch (Exception e) {
            return getPositiveInt(min, INPUT_ERROR);
        }
    }

    /**
     * Clears the console output.
     */
    public static void clearConsoleOutput() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    public static void waitForInput() {
        SCANNER.nextLine();
    }
}

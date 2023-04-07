package traffic;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WELCOME = "Welcome to the traffic management system!";
    private static final String INPUT_ERROR = "Error! Incorrect Input. Try again!";
    private static final String MENU = Command.getMenu();

    public static void main(String[] args) {
        welcome();
        manageSystem();
    }

    public static void welcome() {
        System.out.println(WELCOME);
    }

    public static void printMenu() {
        System.out.println(MENU);
    }

    public static void manageSystem() {
        getPositiveInt(1, "Input the number of roads: ");
        getPositiveInt(1, "Input the interval: ");

        while (executeCommand()){
            clearConsoleOutput();
        }
    }

    public static boolean executeCommand() {
        printMenu();
        Command command = Command.getCommand(scanner.nextLine().toUpperCase());
        System.out.println(command.getResponse());
        return command != Command.QUIT;
    }

    public static int getPositiveInt(int min, String message) {
        System.out.print(message);
        try {
            int positiveInt = Integer.parseInt(scanner.nextLine());
            if (positiveInt < min) {
                return getPositiveInt(min, INPUT_ERROR);
            }
            return positiveInt;
        } catch (Exception e) {
            return getPositiveInt(min, INPUT_ERROR);
        }
    }

    public static void clearConsoleOutput() {
        try {
            System.in.read();
        } catch (IOException ignored){
        }
    }
}

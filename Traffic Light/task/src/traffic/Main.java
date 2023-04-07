package traffic;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WELCOME = "Welcome to the traffic management system!";
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
        System.out.print("Input the number of roads: ");
        scanner.nextInt();
        scanner.nextLine();

        System.out.print("Input the interval: ");
        scanner.nextInt();
        scanner.nextLine();

        printMenu();

        String commandInput = scanner.nextLine();
        Command command = Command.getCommand(commandInput.toUpperCase());
        while (command != Command.QUIT) {
            System.out.println(command.getResponse());

            printMenu();

            commandInput = scanner.nextLine();
            command = Command.getCommand(commandInput.toUpperCase());
        }
        System.out.println(command.getResponse());
    }
}

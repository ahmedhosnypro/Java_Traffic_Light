package traffic.command;

import traffic.TrafficSystem;

import java.util.Scanner;

public class TrafficCommander {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MENU = Command.getMenu();

    private final TrafficSystem trafficSystem;

    public TrafficCommander(TrafficSystem trafficSystem) {
        this.trafficSystem = trafficSystem;
    }

    public boolean executeCommand() {
        printMenu();
        Command command = Command.getCommand(scanner.nextLine().toUpperCase());
        return switch (command) {
            case ADD_ROAD -> {
                trafficSystem.addRoad();
                yield true;
            }
            case DELETE_ROAD -> {
                trafficSystem.deleteRoad();
                yield true;
            }
            case OPEN_SYSTEM -> {
                trafficSystem.openSystem();
                yield false;
            }
            case QUIT -> {
                trafficSystem.shutdown();
                System.out.println("Bye!");
                yield false;
            }
            case UNKNOWN -> {
                System.out.println(command.getResponse());
                yield true;
            }
        };
    }

    public void printMenu() {
        System.out.println(MENU);
    }
}

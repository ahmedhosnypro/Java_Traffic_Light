package traffic.system;

import traffic.command.TrafficCommander;
import traffic.concurrent.QueueThread;

import static traffic.util.Util.*;

public class TrafficSystem {
    TrafficCommander trafficCommander = new TrafficCommander(this);
    private TrafficSystemState trafficSystemState = TrafficSystemState.NOT_STARTED;

    private final QueueThread queueThread;
    private final RoadManager roadManager;

    public TrafficSystem() {
        welcome();

        int numberOfRoads = getPositiveInt(1, "Input the number of roads: ");
        int intervals = getPositiveInt(1, "Input the interval: ");
        roadManager = new RoadManager(intervals, numberOfRoads);

        this.queueThread = new QueueThread("QueueThread", this);
        this.queueThread.start();

        command();
    }

    public static void welcome() {
        System.out.println("Welcome to the traffic management system!");
    }

    public void command() {
        while (trafficCommander.executeCommand()) {
            waitForInput();
        }
    }

    public void openSystem() {
        queueThread.startTime();
        trafficSystemState = TrafficSystemState.SYSTEM;
        var read = SCANNER.nextLine();
        if (read.isEmpty()) {
            closeSystem();
        }
    }

    public void closeSystem() {
        trafficSystemState = TrafficSystemState.MENU;
        command();
    }

    public void shutdown() {
        if (queueThread != null && queueThread.isAlive()) {
            queueThread.stop();
        }
    }

    public TrafficSystemState getState() {
        return trafficSystemState;
    }

    public RoadManager getRoadManager() {
        return roadManager;
    }

    public void addRoad() {
        if (roadManager.addRoad()) {
            queueThread.startTime();
        }
    }
}

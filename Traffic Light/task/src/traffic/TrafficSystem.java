package traffic;

import traffic.command.TrafficCommander;

import java.io.IOException;

import static traffic.Main.SCANNER;
import static traffic.Util.clearConsoleOutput;
import static traffic.Util.getPositiveInt;

public class TrafficSystem {
    private final int intervals;

    TrafficCommander trafficCommander = new TrafficCommander(this);
    private TrafficSystemState trafficSystemState = TrafficSystemState.NOT_STARTED;

    private final QueueThread queueThread;
    private final CircularQueue roadQueue;

    public TrafficSystem() {
        welcome();

        int numberOfRoads = getPositiveInt(1, "Input the number of roads: ");
        roadQueue = new CircularQueue(numberOfRoads);

        intervals = getPositiveInt(1, "Input the interval: ");

        this.queueThread = new QueueThread("QueueThread", this);
        this.queueThread.start();

        command();
    }

    public static void welcome() {
        System.out.println("Welcome to the traffic management system!");
    }

    public void command() {
        while (trafficCommander.executeCommand()) {
            clearConsoleOutput();
        }
    }

    public void addRoad() {
        System.out.print("Input road name: ");
        var roadName = SCANNER.nextLine();
        var added = roadQueue.enqueue(roadName);

        if (added) {
            System.out.println(roadName + " Added!");
        } else {
            System.out.println("queue is full");
        }

    }

    public void deleteRoad() {
        var road = roadQueue.dequeue();
        if (road != null) {
            System.out.println(road + " Deleted!");
        } else {
            System.out.println("queue is empty");
        }
    }

    public void openSystem() {
        queueThread.startTime();
        trafficSystemState = TrafficSystemState.SYSTEM;
        try {
            var read = System.in.read();
            if (read == 10) {
                closeSystem();
            }
        } catch (IOException ignored) {
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

    public int getNumberOfRoads() {
        return roadQueue.capacity();
    }

    public int getIntervals() {
        return intervals;
    }

    public TrafficSystemState getState() {
        return trafficSystemState;
    }

    public String getRoadInfo() {
        return roadQueue.toString();
    }
}

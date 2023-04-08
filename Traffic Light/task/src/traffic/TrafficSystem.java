package traffic;

import traffic.command.TrafficCommander;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static traffic.Util.clearConsoleOutput;
import static traffic.Util.getPositiveInt;

public class TrafficSystem {
    private static final String WELCOME = "Welcome to the traffic management system!";

    private final AtomicInteger numberOfRoads;
    private final AtomicInteger intervals;

    TrafficCommander trafficCommander = new TrafficCommander(this);
    private TrafficSystemState trafficSystemState = TrafficSystemState.NOT_STARTED;

    private final QueueThread queueThread;

    public TrafficSystem() {
        welcome();
        numberOfRoads = getPositiveInt(1, "Input the number of roads: ");
        intervals = getPositiveInt(1, "Input the interval: ");

        this.queueThread = new QueueThread("QueueThread", this);
        this.queueThread.start();

        command();
    }

    public static void welcome() {
        System.out.println(WELCOME);
    }

    public void command() {
        while (trafficCommander.executeCommand()) {
            clearConsoleOutput();
        }
    }

    public void addRoad() {
        numberOfRoads.addAndGet(1);
        System.out.println("Road added");
    }

    public void deleteRoad() {
        numberOfRoads.addAndGet(-1);
        System.out.println("Road deleted");
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

    public AtomicInteger getNumberOfRoads() {
        return numberOfRoads;
    }

    public AtomicInteger getIntervals() {
        return intervals;
    }

    public TrafficSystemState getState() {
        return trafficSystemState;
    }
}

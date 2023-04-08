package traffic.concurrent;

import traffic.system.TrafficSystem;
import traffic.system.TrafficSystemState;

import java.time.Instant;

import static traffic.util.Util.clearConsoleOutput;

public class QueueThread extends Thread {
    private final TrafficSystem trafficSystem;
    private Instant startTime;

    public void startTime() {
        if (this.startTime == null) {
            this.startTime = Instant.now();
        }
    }

    public QueueThread(String name, TrafficSystem trafficSystem) {
        super(name);
        this.trafficSystem = trafficSystem;
    }

    @Override
    public void run() {
        while (true) {
            var time = Instant.now();
            if (trafficSystem.getState() == TrafficSystemState.SYSTEM) {
                clearConsoleOutput();
                var timeDiff = (time.toEpochMilli() - startTime.toEpochMilli()) / 1000;
                System.out.printf("""
                                ! %ds. have passed since system startup !
                                ! Number of roads: %d !
                                ! Interval: %d !
                                                                
                                %s
                                                                
                                ! Press "Enter" to open menu !
                                """,
                        timeDiff,
                        trafficSystem.getRoadManager().getRoads().capacity(),
                        trafficSystem.getRoadManager().getInterval(),
                        trafficSystem.getRoadManager().getState((int) timeDiff));
            }
            try {
                var execMillis = Instant.now().toEpochMilli() - time.toEpochMilli();
                if (execMillis < 1000) {
                    Thread.sleep(1000 - execMillis);
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}

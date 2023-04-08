package traffic;

import java.time.Instant;

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
            if ((trafficSystem.getState() == TrafficSystemState.SYSTEM)
            ) {
                System.out.printf("""
                                ! %ds. have passed since system startup !
                                ! Number of roads: %d !
                                ! Interval: %d !
                                                                
                                %s
                                                                
                                ! Press "Enter" to open menu !
                                """,
                        Instant.now().getEpochSecond() - startTime.getEpochSecond(),
                        trafficSystem.getNumberOfRoads(),
                        trafficSystem.getIntervals(),
                        trafficSystem.getRoadInfo());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

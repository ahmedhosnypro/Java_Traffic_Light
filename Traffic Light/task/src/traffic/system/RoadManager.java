package traffic.system;

import traffic.data.CircularQueue;

import java.util.ArrayList;
import java.util.List;

import static traffic.util.Util.*;

public class RoadManager {
    private final int interval;
    private final CircularQueue roads;

    // used to store deleted roads to calculate the road's state and time to change
    // cleared if the opened road changed
    private final List<Road> deletedRoads = new ArrayList<>();

    private Road lastOpenedRoad;

    /**
     * @param interval in seconds
     * @param capacity number of max roads
     */
    public RoadManager(int interval, int capacity) {
        this.interval = interval;
        this.roads = new CircularQueue(capacity);
    }

    /**
     * adds a road to the system
     * <ul>
     *     <li>if the system capacity not full road will be added</li>
     * </ul>
     *
     * @return true if road added
     */
    public boolean addRoad() {
        System.out.print("Input road name: ");
        var roadName = SCANNER.nextLine();

        var added = roads.enqueue(new Road(roadName));
        if (added) {
            System.out.println(roadName + " Added!");
        } else {
            System.out.println("queue is full");
        }
        return added;
    }

    /**
     * deletes a road from the system
     */
    public void deleteRoad() {
        if (roads.isEmpty()) {
            System.out.println("queue is empty");
            return;
        }
        var road = roads.dequeue();
        System.out.println(road.name() + " Deleted!");

        // after deleting a road, clear the deleted roads list if a system has at least one road
        if (roads.isEmpty() || roads.size() == 1) {
            deletedRoads.clear();
        } else if (roads.size() != 1) {
            deletedRoads.add(road);
        }
    }

    public String getState(int timeDiff) {
        // if the system has no roads, return empty string
        if (roads.isEmpty()) {
            return "";
        }

        var systemRoads = roads.toArray();

        // represent the roads in the system and the deleted roads
        Road[] tmpRoads = new Road[systemRoads.length + deletedRoads.size()];
        for (int i = 0; i < deletedRoads.size(); i++) {
            tmpRoads[i] = deletedRoads.get(i);
        }
        System.arraycopy(systemRoads, 0, tmpRoads, deletedRoads.size(), tmpRoads.length - deletedRoads.size());

        // calculate the index of the opened road
        int openRoadIndex = timeDiff / interval % tmpRoads.length;
        Road openedRoad = tmpRoads[openRoadIndex];

        // calculate the offset of the deleted roads
        int offset = deletedRoads.size();

        StringBuilder sb = new StringBuilder();

        for (int i = offset; i < tmpRoads.length; i++) {
            var roadName = tmpRoads[i].name();
            var state = openRoadIndex == i ? "open" : "closed";

            // calculate the number of roads before the road state changes
            var roadsWaiting = i > openRoadIndex ? i - openRoadIndex : tmpRoads.length - openRoadIndex + i;

            // calculate the time to change the road state
            var timeToChange = state.equals("open")
                    ? interval - timeDiff % interval
                    : roadsWaiting * interval - timeDiff % interval;

            sb.append("Road ").append(roadName).append(" is ");

            if (state.equals("open")) {
                sb.append(ANSI_GREEN);
            } else {
                sb.append(ANSI_RED);
            }

            sb.append(state).append(" for ").append(timeToChange).append("s.").append(ANSI_RESET).append("\n");
        }

        // clear the deleted roads list if the opened road changed
        if (lastOpenedRoad != openedRoad) {
            deletedRoads.clear();
        }
        lastOpenedRoad = openedRoad;

        return sb.toString();
    }

    public int getInterval() {
        return interval;
    }

    public CircularQueue getRoads() {
        return roads;
    }
}

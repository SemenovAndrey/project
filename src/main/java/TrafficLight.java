import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class TrafficLight {

    private final int id;
    private int queueSize = 0;
    private String state = "RED";
    private int timer = 0;
    private final Queue<Event> eventQueue = new ConcurrentLinkedQueue<>();
    private final List<TrafficLight> neighbors = new ArrayList<>();
    private int baseGreenTime;
    private int highPriorityFactor = 1; // коэффициент приоритета

    private static final int HIGH_QUEUE_THRESHOLD = 10;
    private static final int THRESHOLD_FACTOR = 5;

    public TrafficLight(int id, int baseGreenTime) {
        this.id = id;
        this.baseGreenTime = baseGreenTime;
    }

    public int getId() {
        return id;
    }

    public void addNeighbor(TrafficLight neighbor) {
        neighbors.add(neighbor);
    }

    public void updateQueue(int newCount) {
        this.queueSize = newCount;
        notifyNeighbors();
    }

    private void notifyNeighbors() {
        for (TrafficLight neighbor : neighbors) {
            neighbor.receiveEvent(new Event(this.id, this.queueSize, this.state));
        }
    }

    public void receiveEvent(Event event) {
        eventQueue.add(event);
    }

    public void processEvents() {
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            adjustTiming(event);
        }
    }

    private void adjustTiming(Event event) {
        if (event.getQueueSize() > HIGH_QUEUE_THRESHOLD) {
            this.timer = calculateExtendedGreenTime(event.getQueueSize()) * highPriorityFactor;
            this.state = "GREEN";
        } else {
            this.timer = baseGreenTime;
            this.state = "RED";
        }
    }

    private int calculateExtendedGreenTime(int queueLength) {
        return baseGreenTime + (queueLength / THRESHOLD_FACTOR);
    }

    public void runCycle() {
        System.out.println("Traffic Light " + id + " State: " + state + " for " + timer + " seconds. Queue Size: " + queueSize);
    }
}
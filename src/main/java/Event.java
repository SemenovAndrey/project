class Event {

    private final int senderId;
    private final int queueSize;
    private final String state;

    public Event(int senderId, int queueSize, String state) {
        this.senderId = senderId;
        this.queueSize = queueSize;
        this.state = state;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public String getState() {
        return state;
    }
}
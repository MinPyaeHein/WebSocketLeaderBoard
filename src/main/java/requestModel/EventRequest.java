package requestModel;

public class EventRequest {
    private int eventId;
    private String token;

    public EventRequest(int eventId, String token) {
        this.eventId = eventId;
        this.token = token;
    }

    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

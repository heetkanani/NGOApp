package patwa.aman.com.codeshashtra;

public class EventModel {

    String date,event_name,time,venue;

    public EventModel() {
    }

    public EventModel(String date, String event_name, String time, String venue) {
        this.date = date;
        this.event_name = event_name;
        this.time = time;
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}

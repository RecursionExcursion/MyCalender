package com.foofincworks.MyCalender.entity;

public class Event {

    private int id;

    //TODO Make date object?
    private String eventDate;

    private String eventLocation;
    private String startTime;
    private String endTime;
    private String eventName;
    private String eventDescription;

    public Event() {
    }

    public Event(int id,
                 String eventDate,
                 String eventLocation,
                 String startTime,
                 String endTime,
                 String eventName,
                 String eventDescription) {
        this.id = id;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

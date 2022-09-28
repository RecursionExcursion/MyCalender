package com.foofincworks.MyCalender.entity;

import java.util.List;

public class Event {

    private int id;

    //TODO Make date object?
    private String eventDate;

    private String eventLocation;
    private String startTime;
    private String endTime;
    private String eventName;
    private String eventDescription;
    private boolean approved;
    private List<RSVP> rsvpList;

    public Event() {
    }

    public Event(int id,
                 String eventDate,
                 String eventLocation,
                 String startTime,
                 String endTime,
                 String eventName,
                 String eventDescription,
                 boolean approved,
                 List<RSVP> rsvpList) {

        this.id = id;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.approved = approved;
        this.rsvpList = rsvpList;
    }

    //No id constructor for DB integration
    public Event(String eventDate,
                 String eventLocation,
                 String startTime,
                 String endTime,
                 String eventName,
                 String eventDescription,
                 boolean approved,
                 List<RSVP> rsvpList) {

        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.approved = approved;
        this.rsvpList = rsvpList;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<RSVP> getRsvpList() {
        return rsvpList;
    }

    public void setRsvpList(List<RSVP> rsvpList) {
        this.rsvpList = rsvpList;
    }
}

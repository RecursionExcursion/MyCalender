package com.foofincworks.MyCalender.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Event implements Comparable<Event> {

    private int id;
    private String eventDate;
    private String eventLocation;
    private String startTime;
    private String endTime;
    private String eventName;
    private String eventDescription;
    private boolean approved;
    private List<RSVP> rsvpList;

    public Event() {
        rsvpList = new ArrayList<>();
    }

    public Event(int id,
                 String eventDate,
                 String eventLocation,
                 String startTime,
                 String endTime,
                 String eventName,
                 String eventDescription,
                 boolean approved) {

        this.id = id;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.approved = approved;
        this.rsvpList = new ArrayList<>();
    }

    //No id constructor for DB integration
    public Event(String eventDate,
                 String eventLocation,
                 String startTime,
                 String endTime,
                 String eventName,
                 String eventDescription,
                 boolean approved) {

        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.approved = approved;
        this.rsvpList = new ArrayList<>();
    }


    @Override
    public int compareTo(Event o) {
        //Date Strings will be submitted as yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //Convert Strings to LocalDate Objects
        LocalDate thisDate = LocalDate.parse(this.eventDate, formatter);
        LocalDate comparedDate = LocalDate.parse(o.eventDate, formatter);

        //Compare Date Objects
        if (thisDate.isBefore(comparedDate)) return -1;
        else if (thisDate.isAfter(comparedDate)) return 1;
        else return 0;
    }


    //Getters and Setters

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

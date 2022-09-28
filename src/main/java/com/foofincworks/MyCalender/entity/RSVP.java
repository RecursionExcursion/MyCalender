package com.foofincworks.MyCalender.entity;

public class RSVP {

    private String firstName;
    private String lastName;
    private String email;

    public RSVP() {
    }

    //Jackson uses Getters and Setters to create POJO's from JSON
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

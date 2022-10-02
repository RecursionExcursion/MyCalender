package com.foofincworks.MyCalender.persistence.settings;

public class Settings {


    public boolean enableEmailNotifications = false;
    public String emailAddress;

    Settings() {
    }

    //Key is parameter's name
    void setParameter(String key, String param) {
        if (key.equals("EnableEmailNotifications")) this.enableEmailNotifications = Boolean.parseBoolean(param);
        if (key.equals("EmailAddress")) this.emailAddress = param;
    }

    String getStringOfParameter(String key){
        if (key.equals("EnableEmailNotifications")) return String.valueOf(enableEmailNotifications);
        if (key.equals("EmailAddress")) return emailAddress;
        return null;
    }
}

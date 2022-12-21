package com.foofincworks.MyCalender.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foofincworks.MyCalender.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    ObjectMapper mapper;
    File eventsJSON;
    List<Event> events;

    @Autowired
    public DataMapper() {
        events = new ArrayList<>();
        eventsJSON = new File("src/main/java/com/foofincworks/MyCalender/persistence/events.json");
    }

    public List<Event> getDataAsList() {
        return getDataFromJSON();
    }

    public void mapPOJOToJSON(List<Event> events) {
        try {
            mapper.writeValue(eventsJSON, events);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Event> getDataFromJSON() {
        try {
            mapper = new ObjectMapper();
            return mapper.readValue(eventsJSON, new TypeReference<List<Event>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.foofincworks.MyCalender.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.foofincworks.MyCalender.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DataMapper {

    ObjectMapper mapper;
    File eventsJSON;
    List<Event> events;

    @Autowired
    public DataMapper() {
        events = new ArrayList<>();
        eventsJSON = new File("src/main/java/com/foofincworks/MyCalender/persistence/events.json");
    }

     void convertCSVDataToJSON() {
        Pattern pattern = Pattern.compile(",");

        try (BufferedReader in = new BufferedReader(new FileReader("src/main/java/com/foofincworks/MyCalender/persistence/data.csv"))) {
            List<Event> tempEvents = in.lines()
                                       .skip(1)
                                       .map(line -> {
                                           String[] x = pattern.split(line);
                                           return new Event(Integer.parseInt(x[0]),
                                                            x[1], x[2], x[3], x[4], x[5], x[6]);
                                       }).toList();

            mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(eventsJSON, tempEvents);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     List<Event> getDataFromJSON() {
        try {
            mapper = new ObjectMapper();
//            Event event = mapper.readValue(eventsJSON, Event.class);
            List<Event> events = mapper.readValue(eventsJSON, new TypeReference<List<Event>>() {});
            return events;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getDataAsList(){
        convertCSVDataToJSON();
        return getDataFromJSON();
    }
}

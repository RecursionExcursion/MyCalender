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
//        convertCSVDataToJSON();
        return getDataFromJSON();
    }

    public void mapPOJOToJSON(List<Event> events) {
        try {
            mapper.writeValue(eventsJSON, events);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//TODO Clean up

//    void convertCSVDataToJSON() {
//        Pattern pattern = Pattern.compile(",");
//
//        try (BufferedReader in = new BufferedReader(new FileReader("src/main/java/com/foofincworks/MyCalender/persistence/data.csv"))) {
//            List<Event> tempEvents = in.lines()
//                                       .skip(1)
//                                       .map(line -> {
//                                           String[] x = pattern.split(line);
//                                           return new Event(Integer.parseInt(x[0]),
//                                                            x[1], x[2], x[3], x[4], x[5], x[6], Boolean.parseBoolean(x[7]));
//                                       }).toList();
//
//            mapper = new ObjectMapper();
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            mapper.writeValue(eventsJSON, tempEvents);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    List<Event> getDataFromJSON() {
        try {
            mapper = new ObjectMapper();
            List<Event> events = mapper.readValue(eventsJSON, new TypeReference<List<Event>>() {
            });
            return events;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

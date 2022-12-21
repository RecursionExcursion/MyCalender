package com.foofincworks.MyCalender.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.persistence.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class EventDao implements ApplicationDao<Event> {

    ObjectMapper mapper;
    private List<Event> events;
    private DataMapper dataMapper;

    @Autowired
    public EventDao() {
        mapper = new ObjectMapper();
        dataMapper = new DataMapper();
        events = dataMapper.getDataAsList();
    }

    @Override
    public Event get(long id) {
        for (Event e : events) if (e.getId() == id) return e;
        return null;
    }

    @Override
    public List<Event> getAll() {
        Collections.sort(events);
        return events;
    }

    //The methods below will be need to save the JSON when finished

    @Override
    public void save(Event event) {
        events.add(event);
        saveToJSON();
    }

    @Override
    public void update(int eventId, Event event) {
        int index = events.indexOf(get(eventId));
        events.set(index, event);
        saveToJSON();
    }

    @Override
    public void delete(Event event) {
        events.remove(event);
        saveToJSON();
    }

    private void saveToJSON() {
        dataMapper.mapPOJOToJSON(events);
    }
}

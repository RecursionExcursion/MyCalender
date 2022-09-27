package com.foofincworks.MyCalender.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.persistence.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        for (Event e : events) {
            if (e.getId() == id) {
                return e;
            }
        }

        return null;
    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public void save(Event event) {
        events.add(event);
        save();
    }

    @Override
    public void update(int eventId, Event event) {
        save();
    }

    @Override
    public void delete(Event event) {
        events.remove(event);
        save();
    }

    private void save() {
        dataMapper.mapPOJOToJSON(events);
    }
}

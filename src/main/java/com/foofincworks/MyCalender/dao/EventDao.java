package com.foofincworks.MyCalender.dao;

import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.persistence.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDao implements ApplicationDao<Event> {


    private List<Event> events;
    private DataMapper dataMapper;

    @Autowired
    public EventDao() {
        dataMapper = new DataMapper();
        events = dataMapper.getDataAsList();
    }

    @Override
    public Event get(long id) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public void update(Event s, String[] params) {

    }

    @Override
    public void delete(Event event) {

    }
}
